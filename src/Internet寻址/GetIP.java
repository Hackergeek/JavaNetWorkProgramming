package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetIP {

	/*
	 * 2.2������������IP��ַ
	 * ����InetAddress������ʹ�����ַ�ʽ�����ģ�getHostAddress�������������DNS������
	 */
	public static void main(String[] args) {
		try {
			InetAddress ad = InetAddress.getByName(args[0]);
			System.out.println("IP��ַΪ��" + ad.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
