package SSL;

import java.io.PrintWriter;
import java.net.Socket;
import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

public class SSLClient {
	static int port = 8226;

	public static void main(String[] args) {
		try {
			System.out.println(System.getProperty("java.home"));
			SocketFactory factory = SSLSocketFactory.getDefault();
			Socket socket = factory.createSocket("localhost", port);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.write("安全的说你好");
			out.close();
			socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
