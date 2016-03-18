package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress2 {

	/*
	 * 2.1创建InetAddress对象———— 通过getByName方法得到远程主机的InetAddress对象
	 *  getByName方法是InetAddress类最常用的方法，它可以通过指定域名从DNS中得到相应的IP地址
	 *  
	 *  对于本机来说，除了可以使用本机名或localhost外，还可以在hosts文件中对本机做“IP/域名”映射（在Windows操作系统下）。
	 *  这个文件在C:\WINDOWS\system32\drivers\etc中。
	 *  在进行域名解析过程中，先遍历hosts文件中的域名，若域名不匹配，再通过访问DNS服务器查询域名所对应的IP
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			return;
		}
		String host = args[0];
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
