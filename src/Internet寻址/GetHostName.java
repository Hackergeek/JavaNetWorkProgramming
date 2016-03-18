package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostName {
	/*
	 * 2.3根据IP地址查找主机名
	 */
	public static void main(String[] args) {
		try {
			InetAddress inetadd = InetAddress.getByName(args[0]);
			System.out.println("主机名为： " + inetadd.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
