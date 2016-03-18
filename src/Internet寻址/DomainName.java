package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 2.3根据IP地址查找主机名
 * getCanonicalHostName得到的是主机名
 * getHostName得到的是主机别名
 * 如果使用域名来创建InetAddress对象，getHostName所得到的域名就是用来创建InetAddress对象的域名
 * 如果使用IP地址来创建InetAddress对象，getHostName方法就等于getCanonicalHostName方法，两个方法得到的都是主机名
 */
public class DomainName {

	public static void outHostName(InetAddress address, String s) {
		System.out.println("通过" + s + "创建InetAddress对象");
		System.out.println("主机名" + address.getCanonicalHostName());
		System.out.println("主机别名" + address.getHostName());
		System.out.println();
	}
	
	public static void main(String[] args) throws UnknownHostException {
		outHostName(InetAddress.getLocalHost(), "getLocalHost方法");
		outHostName(InetAddress.getByName("www.ibm.com"), "www.ibm.com");
		outHostName(InetAddress.getByName("www.126.com"), "www.126.com");
		outHostName(InetAddress.getByName("www.ycit.cn"), "www.ycit.cn");
		outHostName(InetAddress.getByName("202.108.9.77"), "202.108.9.77");
		outHostName(InetAddress.getByName("211.100.26.121"), "211.100.26.121");
		outHostName(InetAddress.getByName("222.188.0.30"), "222.188.0.30");
	}

}
