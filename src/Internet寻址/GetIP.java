package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {

	/*
	 * 2.2根据域名查找IP地址
	 * 无论InetAddress对象是使用哪种方式创建的，getHostAddress方法都不会访问DNS服务器
	 */
	public static void main(String[] args) {
		try {
			InetAddress ad = InetAddress.getByName(args[0]);
			System.out.println("IP地址为：" + ad.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
