package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostName {
	/*
	 * 2.3����IP��ַ����������
	 */
	public static void main(String[] args) {
		try {
			InetAddress inetadd = InetAddress.getByName(args[0]);
			System.out.println("������Ϊ�� " + inetadd.getHostName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
