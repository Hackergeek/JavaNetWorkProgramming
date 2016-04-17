package SSL;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

public class SSLServer {
	static int port = 8226;
	static SSLServerSocket server;

	public SSLServer() {

	}

	/**
	 * 
	 * @param port
	 *            监听的端口号
	 * @return 返回一个SSLServerSocket
	 */
	private static SSLServerSocket getServerSocket(int port) {
		SSLServerSocket s = null;
		try {
			// 要使用的证书名
			String key = "SSLKey";
			// 证书密码
			char keyStorePass[] = "12345678".toCharArray();
			// 证书别称所使用的主要密码
			char keyPassword[] = "12345678".toCharArray();
			// 创建JKS密钥库
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(key), keyStorePass);
			//创建管理JKS密钥库的X.509密钥管理器
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(keyStore, keyPassword);
			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			sslContext.init(kmf.getKeyManagers(), null, null);
			//根据上面配置的SSL上下文来产生SSLServerSocketFactory，与通常的产生方法不同
			SSLServerSocketFactory factory = sslContext.getServerSocketFactory();
			s = (SSLServerSocket) factory.createServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		server = getServerSocket(port);
		System.out.println("在" + port + "端口等待连接...");
		while(true) {
			try {
				SSLSocket socket = (SSLSocket) server.accept();
				//将得到的socket交给CreateThread对象处理，主线程继续监听
				new CreateThread(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	static class CreateThread extends Thread {
		/**
		 * 
		 * @param socket
		 * 获得socket连接，初始化in和out对象
		 */
		public CreateThread(SSLSocket socket) {
			s = socket;
			try {
				in = new BufferedReader(new InputStreamReader(s.getInputStream(), "gb2312"));
				out = new PrintWriter(s.getOutputStream(), true);
				start();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		public void run() {
			try {
				String msg = in.readLine();
				System.out.println(msg);
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		static BufferedReader in;
		static PrintWriter out;
		static Socket s;
	}

}
