package InternetѰַ;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress3 {

	/*
	 * 2.1����InetAddress���󡪡�����ͨ��getAllByName�����õ�Զ���������е�InetAddress����
	 * ʹ��getAllByName�������Դ�DNS�ϵõ�������Ӧ������IP�������������һ��InetAddress���͵�����
	 * 
	 * ��getByName����һ����getAllByNameҲ������֤IP��ַ�Ƿ����
	 */
	public static void main(String[] args) {
		if(args.length == 0) {
			return ;
		}
		String host = args[0];
		InetAddress[] addresses;
		try {
			addresses = InetAddress.getAllByName(host);
			for(InetAddress address : addresses) 
				System.out.println(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
