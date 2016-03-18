package InternetѰַ;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyIP {

	/*
	 * 2.4Inet4Address���Inet6Address��
	 * Inet4Address���Inet6Address��������ǣ�Inet6Address���Inet4Address��
	 * ����һ��isIPv4CompatibleAddress������������������ж�һ��IPv6��ַ�Ƿ��IPv4��ַ����
	 * ��IPv4����IPv6��ַ�������4�ֽ���ֵ�⣬�������ֽڶ��ǣ���::ABCD:FAFA�ͣ���192.168.18.10���Ǻ�IPv4���ݵ�IPv6��ַ
	 */
	public static void main(String[] args) throws UnknownHostException {
		if(args.length == 0) {
			return ;
		}
		InetAddress address = InetAddress.getByName(args[0]);
		System.out.println("IP: " + address.getHostAddress());
		switch (address.getAddress().length) {
		case 4:
			System.out.println("����byte���鳤���ж����IP��ַ��IPv4��ַ��");
			break;
		case 16:
			System.out.println("����byte���鳤���ж����IP��ַ��IPv6��ַ��");
		default:
			break;
		}
		if(address instanceof Inet4Address) {
			System.out.println("ʹ��instanceof�ж����IP��ַ��IPv4��ַ��");
		} else if (address instanceof Inet6Address) {
			System.out.println("ʹ��instanceof�ж����IP��ַ��IPv6��ַ��");
		}
	}

}
