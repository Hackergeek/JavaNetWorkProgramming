package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress2 {

	/*
	 * 2.1����InetAddress���󡪡����� ͨ��getByName�����õ�Զ��������InetAddress����
	 *  getByName������InetAddress����õķ�����������ͨ��ָ��������DNS�еõ���Ӧ��IP��ַ
	 *  
	 *  ���ڱ�����˵�����˿���ʹ�ñ�������localhost�⣬��������hosts�ļ��жԱ�������IP/������ӳ�䣨��Windows����ϵͳ�£���
	 *  ����ļ���C:\WINDOWS\system32\drivers\etc�С�
	 *  �ڽ����������������У��ȱ���hosts�ļ��е���������������ƥ�䣬��ͨ������DNS��������ѯ��������Ӧ��IP
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			return;
		}
		String host = args[0];
		InetAddress address;
		try {
			address = InetAddress.getByName(host);
			System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
