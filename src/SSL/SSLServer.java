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
	 *            �����Ķ˿ں�
	 * @return ����һ��SSLServerSocket
	 */
	private static SSLServerSocket getServerSocket(int port) {
		SSLServerSocket s = null;
		try {
			// Ҫʹ�õ�֤����
			String key = "SSLKey";
			// ֤������
			char keyStorePass[] = "12345678".toCharArray();
			// ֤������ʹ�õ���Ҫ����
			char keyPassword[] = "12345678".toCharArray();
			// ����JKS��Կ��
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream(key), keyStorePass);
			//��������JKS��Կ���X.509��Կ������
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(keyStore, keyPassword);
			SSLContext sslContext = SSLContext.getInstance("SSLv3");
			sslContext.init(kmf.getKeyManagers(), null, null);
			//�����������õ�SSL������������SSLServerSocketFactory����ͨ���Ĳ���������ͬ
			SSLServerSocketFactory factory = sslContext.getServerSocketFactory();
			s = (SSLServerSocket) factory.createServerSocket(port);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static void main(String[] args) {
		server = getServerSocket(port);
		System.out.println("��" + port + "�˿ڵȴ�����...");
		while(true) {
			try {
				SSLSocket socket = (SSLSocket) server.accept();
				//���õ���socket����CreateThread���������̼߳�������
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
		 * ���socket���ӣ���ʼ��in��out����
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
