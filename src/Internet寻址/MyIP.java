package Internet寻址;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyIP {

	/*
	 * 2.4Inet4Address类和Inet6Address类
	 * Inet4Address类和Inet6Address类的区别是：Inet6Address类比Inet4Address类
	 * 多了一个isIPv4CompatibleAddress方法，这个方法用来判断一个IPv6地址是否和IPv4地址兼容
	 * 和IPv4兼容IPv6地址除了最后4字节有值外，其他的字节都是，如::ABCD:FAFA和：：192.168.18.10都是和IPv4兼容的IPv6地址
	 */
	public static void main(String[] args) throws UnknownHostException {
		if(args.length == 0) {
			return ;
		}
		InetAddress address = InetAddress.getByName(args[0]);
		System.out.println("IP: " + address.getHostAddress());
		switch (address.getAddress().length) {
		case 4:
			System.out.println("根据byte数组长度判断这个IP地址是IPv4地址！");
			break;
		case 16:
			System.out.println("根据byte数组长度判断这个IP地址是IPv6地址！");
		default:
			break;
		}
		if(address instanceof Inet4Address) {
			System.out.println("使用instanceof判断这个IP地址是IPv4地址！");
		} else if (address instanceof Inet6Address) {
			System.out.println("使用instanceof判断这个IP地址是IPv6地址！");
		}
	}

}
