package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	/*
	 * 2.2根据域名查找IP地址
	 * getAddress方法和getHostAddress方法唯一区别是getHostAddress方法返回的是字符串形式的IP地址，
	 * 而getAddress方法返回的是byte数组形式的IP地址
	 */
	public static void main(String[] args) {
		String host = "localhost";
		if(args.length == 1) {
			host = args[0];
		}
		try {
			InetAddress ad = InetAddress.getByName(host);
			System.out.println("Canonical Host Name = " + ad.getCanonicalHostName());
			System.out.println("Host Name = " + ad.getHostName());
			System.out.println("Host Address = " + ad.getHostAddress());
			System.out.println("Is LoopBack Address = " + ad.isLoopbackAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
