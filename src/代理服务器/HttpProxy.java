package 代理服务器;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * 一个代理服务器的基本设计： （1）等待来自客户（Web浏览器）的请求 （2）启动一个新的线程，已处理客户连接请求
 * （3）读取浏览器请求的第一行（该行内容包含了请求的目标URL） （4）分析请求的第一行内容，得到目标服务器的名字和端口
 * （5）打开一个通向目标服务器（或下一个代理服务器）的Socket （6）把请求的第一行发送到输出Socket （7）把请求的剩余部分发送到输出Socket
 * （8）把目标Web服务器返回的数据发送给发出请求的浏览器
 * 
 * @author skyward
 *
 */
public class HttpProxy extends Thread {
	// 在放弃之前尝试连接远程主机的次数
	static public int CONNECT_RETRIES = 5;
	// 在两次连接尝试之间的暂停时间
	static public int CONNECT_PAUSE = 5;
	// 等待Socket输入的等待时间
	static public int TIME_OUT = 50;
	// Socket输入缓冲的大小
	static public int BUFSIZ = 1024;
	// 是否要求代理服务器在日志中记录所有已传输的数据（true表示“是”）
	static public boolean logging = false;
	// 一个OutputStream对象，默认日志例程将向该OutputStream对象输出日志信息
	static public OutputStream log_C = null; // 浏览器输出流
	static public OutputStream log_S = null; // Web主机输出流
	// 传入数据用的Socket
	protected Socket socket;
	// 上级代理服务器，可选
	static private String parent = null;
	static private int parentPort = -1;

	// 用来把一个代理服务器链接到另一个代理服务器（需要指定另一个代理服务器的名称和端口
	static public void setParentProxy(String name, int port) {
		parent = name;
		parentPort = port;
	}

	// 在给定Socket上创建一个代理线程
	public HttpProxy(Socket s) {
		socket = s;
		start();
	}

	public void writeLog(int c, boolean browser) throws IOException {
		if (browser) {
			log_C.write(c);
		} else {
			log_S.write(c);
		}
	}

	public void writeLog(byte[] bytes, int offset, int len, boolean browser)
			throws IOException {
		for (int i = 0; i < len; i++) {
			writeLog((int) bytes[offset + i], browser);
		}
	}

	// 默认情况下，日志信息输出到标准输出设备，派生类可以覆盖它
	public String processHostName(String url, String host, int port,
			Socket socket) {
		DateFormat cal = DateFormat.getDateTimeInstance();
		System.out.println(cal.format(new Date()) + " - " + url + " "
				+ socket.getInetAddress() + "<BR>");
		return host;
	}

	// 执行操作的线程
	public void run() {
		String line;
		String host;
		int port = 80;
		Socket outbound = null;
		try {
			socket.setSoTimeout(TIME_OUT);
			InputStream is = socket.getInputStream();
			OutputStream os = null;
			try {
				// 获取请求行的内容
				line = "";
				host = "";
				int state = 0;
				boolean space;
				while (true) {
					int c = is.read();
					if (c == -1)
						break;
					if (logging)
						writeLog(c, true);
					space = Character.isWhitespace((char) c);
					switch (state) {
					case 0:
						if (space) {
							continue;
						}
						state = 1;
						break;
					case 1:
						if (space) {
							state = 2;
							continue;
						}
						line = line + (char) c;
						break;
					case 2:
						if (space) {
							continue;
						}
						state = 3;
						break;
					case 3:
						if (space) {
							state = 4;
							// 只分析主机名称部分
							String host0 = host;
							int n;
							n = host.indexOf("//");
							if (n != -1)
								host = host.substring(n + 2);
							n = host.indexOf('/');
							if (n != -1)
								host = host.substring(0, n);
							// 分析可能存在的端口号
							n = host.indexOf(":");
							if (n != -1) {
								port = Integer.parseInt(host.substring(n + 1));
								host = host.substring(0, n);
							}
							host = processHostName(host0, host, port, socket);
							if (parent != null) {
								host = parent;
								port = parentPort;
							}
							int retry = CONNECT_RETRIES;
							while (retry-- != 0) {
								try {
									outbound = new Socket(host, port);
									break;
								} catch (Exception e) {
									// 连接失败，等待
									Thread.sleep(CONNECT_PAUSE);
								}
							}
							if (outbound == null)
								break;
							outbound.setSoTimeout(TIME_OUT);
							os.write(line.getBytes());
							os.write(' ');
							os.write(host0.getBytes());
							os.write(' ');
							pipe(is, outbound.getInputStream(), os,
									outbound.getOutputStream());
							break;
						}
						host = host + (char) c;
						break;
					default:
						break;
					}
				}
			} catch (Exception e) {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
				outbound.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void pipe(InputStream cis, InputStream sis, OutputStream cos,
			OutputStream sos) {
		try {
			int length;
			byte[] bytes = new byte[BUFSIZ];
			while (true) {
				try {
					if ((length = cis.read(bytes)) > 0) {
						sos.write(bytes, 0, length);
						if (logging)
							writeLog(bytes, 0, length, true);
					} else if (length < 0) {
						break;
					}
				} catch (Exception e) {
					System.out.println("Request Exception");
				}
				try {
					if ((length = sis.read(bytes)) > 0) {
						cos.write(bytes, 0, length);
						if (logging)
							writeLog(bytes, 0, length, false);
					} else if (length < 0) {
						break;
					}
				} catch (Exception e) {
					System.out.println("Response Exception");
				}
			}
		} catch (Exception e) {
			System.out.println("Pipe异常" + e);
		}
	}

	public static void startProxy(int port, Class clobj) {
		ServerSocket server;
		try {
			server = new ServerSocket(port);
			while (true) {
				Class[] sarg = new Class[1];
				Object[] arg = new Object[1];
				sarg[0] = Socket.class;
				try {
					Constructor cons = clobj.getDeclaredConstructor(sarg);
					arg[0] = server.accept();
					cons.newInstance(arg);
				} catch (Exception e) {
					Socket eSocket = (Socket) arg[0];
					try {
						eSocket.close();
					} catch (Exception e2) {
					}
				}
			}
		} catch (Exception e) {
		}
	}

	public static void main(String[] args) {
		System.out.println("在端口808启动代理服务器");
		HttpProxy.log_C = System.out;
		HttpProxy.log_S = System.out;
		HttpProxy.logging = false;
		HttpProxy.startProxy(808, HttpProxy.class);
	}

}
