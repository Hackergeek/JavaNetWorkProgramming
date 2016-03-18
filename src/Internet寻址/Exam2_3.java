package Internet—∞÷∑;

import java.net.InetAddress;
import java.net.UnknownHostException;

//œ∞Ã‚2-3
public class Exam2_3 {

	public static void main(String[] args) {
		InetAddress host = null;
		try {
			host = InetAddress.getLocalHost();
			System.out.println(host.getHostAddress());
			System.out.println(host.getHostName());
			System.out.println(host.getClass());
			System.out.println(InetAddress.getByName("www.ycit.edu.cn"));
			System.out.println(InetAddress.getByName("localhost"));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
