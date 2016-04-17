package ͨ������ǩ�����ݶ���;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Signature;

public class Server implements Cloneable, Runnable {
	ServerSocket server = null;
	Socket clientSocket = null;
	ObjectInputStream ois = null;
	Thread thread = null;

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.startServer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//����������
	public synchronized void startServer() throws IOException {
		if(thread == null) {
			server = new ServerSocket(4000);
			thread = new Thread(this);
			thread.start();
		}
	}

	public void run() {
		Socket client = null;
		if(server != null) {
			while(true) {
				try {
					client = server.accept();
					Server newServer =(Server) clone();
					newServer.server =null;
					newServer.clientSocket = client;
					newServer.thread = new Thread(newServer);
					newServer.thread.start();
					System.out.println("Server Started");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
			}
		} else {
			perform(clientSocket);
		}
	}
	
	public void perform(Socket client) {
		try {
			ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
			SignedObject object = (SignedObject) ois.readObject();
			//����ǩ������
			Signature sig = Signature.getInstance("SHA/DSA");
			//�ù�����Կ��ʼ��ǩ������
			sig.initVerify(object.pub);
			//ʹ��update�����ṩǩ�������ݸ�Ҫ��֤��ǩ��
			sig.update(object.b);
			//��֤ǩ����������
			boolean valid = sig.verify(object.sig);
			if(valid) {
				System.out.println("Signature is valid"); 
			} else {
				System.out.println("Signature is not valid......");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				ois.close();
				clientSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} 
	}

}
