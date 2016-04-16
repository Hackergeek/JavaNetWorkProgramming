package 通过数字签名传递对象;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

public class Server implements Cloneable, Runnable {
	ServerSocket server = null;
	Socket clientSocket = null;
	ObjectInputStream ois = null;
	Thread thread = null;
	KeyPairGenerator generator = null;
	KeyPair keyPair = null;

	public static void main(String[] args) {

	}

	public void run() {

	}

}
