package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress4 {

	/*
	 * 2.1创建InetAddress对象
	 * 通过getByAddress方法来创建InetAddress对象
	 * getByAddress方法并不使用host在DNS上查找IP地址，这个host只是一个用于表示addr的别名、
	 */
	public static void main(String[] args) {
		byte ip[] = new byte[] {
				(byte) 141, (byte) 146, (byte)8, (byte)66
		};
		try {
			InetAddress address1 = InetAddress.getByAddress(ip);
			InetAddress address2 = InetAddress.getByAddress("Oracle官方网站", ip);
			System.out.println(address1);
			System.out.println(address2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
