package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress4 {

	/*
	 * 2.1����InetAddress����
	 * ͨ��getByAddress����������InetAddress����
	 * getByAddress��������ʹ��host��DNS�ϲ���IP��ַ�����hostֻ��һ�����ڱ�ʾaddr�ı�����
	 */
	public static void main(String[] args) {
		byte ip[] = new byte[] {
				(byte) 141, (byte) 146, (byte)8, (byte)66
		};
		try {
			InetAddress address1 = InetAddress.getByAddress(ip);
			InetAddress address2 = InetAddress.getByAddress("Oracle�ٷ���վ", ip);
			System.out.println(address1);
			System.out.println(address2);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
