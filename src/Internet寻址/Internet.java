package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Internet {
	/*
	 * 2.3根据IP地址查找主机名
	 * InetAddress可以根据给定的IP来查找对应的主机名，但要注意的是，它只能获取局域网内的主机名
	 */
	public static void main(String[] args) {
		try {
			InetAddress inetadd = InetAddress.getLocalHost();
			System.out.println("hostname=" + inetadd.getHostName());
			System.out.println(inetadd.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
