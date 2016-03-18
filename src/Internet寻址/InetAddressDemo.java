package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	/*
	 * 2.2������������IP��ַ
	 * getAddress������getHostAddress����Ψһ������getHostAddress�������ص����ַ�����ʽ��IP��ַ��
	 * ��getAddress�������ص���byte������ʽ��IP��ַ
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
