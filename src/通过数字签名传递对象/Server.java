package 通过数字签名传递对象;

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
	
	//开启服务器
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
			//创建签名对象
			Signature sig = Signature.getInstance("SHA/DSA");
			//用公有密钥初始化签名对象
			sig.initVerify(object.pub);
			//使用update方法提供签名的数据给要验证的签名
			sig.update(object.b);
			//验证签名并报告结果
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
