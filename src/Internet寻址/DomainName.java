package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * 2.3����IP��ַ����������
 * getCanonicalHostName�õ�����������
 * getHostName�õ�������������
 * ���ʹ������������InetAddress����getHostName���õ�������������������InetAddress���������
 * ���ʹ��IP��ַ������InetAddress����getHostName�����͵���getCanonicalHostName���������������õ��Ķ���������
 */
public class DomainName {

	public static void outHostName(InetAddress address, String s) {
		System.out.println("ͨ��" + s + "����InetAddress����");
		System.out.println("������" + address.getCanonicalHostName());
		System.out.println("��������" + address.getHostName());
		System.out.println();
	}
	
	public static void main(String[] args) throws UnknownHostException {
		outHostName(InetAddress.getLocalHost(), "getLocalHost����");
		outHostName(InetAddress.getByName("www.ibm.com"), "www.ibm.com");
		outHostName(InetAddress.getByName("www.126.com"), "www.126.com");
		outHostName(InetAddress.getByName("www.ycit.cn"), "www.ycit.cn");
		outHostName(InetAddress.getByName("202.108.9.77"), "202.108.9.77");
		outHostName(InetAddress.getByName("211.100.26.121"), "211.100.26.121");
		outHostName(InetAddress.getByName("222.188.0.30"), "222.188.0.30");
	}

}
