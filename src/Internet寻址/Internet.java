package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Internet {
	/*
	 * 2.3����IP��ַ����������
	 * InetAddress���Ը��ݸ�����IP�����Ҷ�Ӧ������������Ҫע����ǣ���ֻ�ܻ�ȡ�������ڵ�������
	 */
	public static void main(String[] args) {
		try {
			InetAddress inetadd = InetAddress.getLocalHost();
			System.out.println("hostname=" + inetadd.getHostName());
			System.out.println(inetadd.toString());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
	}
}
