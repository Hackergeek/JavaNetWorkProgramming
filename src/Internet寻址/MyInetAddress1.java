package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress1 {
	/* 
	 * 2.1����InetAddress���󡪡�����ͨ��getLocalHost�����õ�������InetAddress����
	 * InetAddress����Java��������IP��ַ����
	 * Ҫ�봴��InetAddress���󣬱������������ĸ���̬������
	 * InetAddress����ͨ��getLocalHost�����õ�������InetAddress����
	 * Ҳ����ͨ��getByName��getAllByName��getByAddress�õ�Զ��������InetAddress����
	 * ���������˶��IPʱ��getLocalHostֻ���ص�һ��IP������뷵�ر���ȫ����IP������ʹ��getAllByName����
	 * 
	 */

	public static void main(String[] args) {
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			System.out.println(localHost);
			//�����Ҳ������������ĵ�ַʱ������һ��UnknownHostException�쳣
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
