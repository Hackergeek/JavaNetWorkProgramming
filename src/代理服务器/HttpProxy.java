package ���������;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.Date;

/**
 * һ������������Ļ�����ƣ� ��1���ȴ����Կͻ���Web������������� ��2������һ���µ��̣߳��Ѵ���ͻ���������
 * ��3����ȡ���������ĵ�һ�У��������ݰ����������Ŀ��URL�� ��4����������ĵ�һ�����ݣ��õ�Ŀ������������ֺͶ˿�
 * ��5����һ��ͨ��Ŀ�������������һ���������������Socket ��6��������ĵ�һ�з��͵����Socket ��7���������ʣ�ಿ�ַ��͵����Socket
 * ��8����Ŀ��Web���������ص����ݷ��͸���������������
 * 
 * @author skyward
 *
 */
public class HttpProxy extends Thread {
	// �ڷ���֮ǰ��������Զ�������Ĵ���
	static public int CONNECT_RETRIES = 5;
	// ���������ӳ���֮�����ͣʱ��
	static public int CONNECT_PAUSE = 5;
	// �ȴ�Socket����ĵȴ�ʱ��
	static public int TIME_OUT = 50;
	// Socket���뻺��Ĵ�С
	static public int BUFSIZ = 1024;
	// �Ƿ�Ҫ��������������־�м�¼�����Ѵ�������ݣ�true��ʾ���ǡ���
	static public boolean logging = false;
	// һ��OutputStream����Ĭ����־���̽����OutputStream���������־��Ϣ
	static public OutputStream log_C = null; // ����������
	static public OutputStream log_S = null; // Web���������
	// ���������õ�Socket
	protected Socket socket;
	// �ϼ��������������ѡ
	static private String parent = null;
	static private int parentPort = -1;

	// ������һ��������������ӵ���һ���������������Ҫָ����һ����������������ƺͶ˿�
	static public void setParentProxy(String name, int port) {
		parent = name;
		parentPort = port;
	}

	// �ڸ���Socket�ϴ���һ�������߳�
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

	// Ĭ������£���־��Ϣ�������׼����豸����������Ը�����
	public String processHostName(String url, String host, int port,
			Socket socket) {
		DateFormat cal = DateFormat.getDateTimeInstance();
		System.out.println(cal.format(new Date()) + " - " + url + " "
				+ socket.getInetAddress() + "<BR>");
		return host;
	}

	// ִ�в������߳�
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
				// ��ȡ�����е�����
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
							// ֻ�����������Ʋ���
							String host0 = host;
							int n;
							n = host.indexOf("//");
							if (n != -1)
								host = host.substring(n + 2);
							n = host.indexOf('/');
							if (n != -1)
								host = host.substring(0, n);
							// �������ܴ��ڵĶ˿ں�
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
									// ����ʧ�ܣ��ȴ�
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
			System.out.println("Pipe�쳣" + e);
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
		System.out.println("�ڶ˿�808�������������");
		HttpProxy.log_C = System.out;
		HttpProxy.log_S = System.out;
		HttpProxy.logging = false;
		HttpProxy.startProxy(808, HttpProxy.class);
	}

}
