package ���������;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class ProxyTest {
	Proxy proxy;
	URL url;
	URLConnection connection;
	//������ͨ�����������
	Scanner scanner;
	PrintStream printStream;
	//�����Ǵ���������ĵ�ַ�Ͷ˿�
	//����ʵ����Ч�Ĵ���������ĵ�ַ�Ͷ˿�
	String proxyAddress = "202.128.23.31";
	int proxyPort;
	//����������ͼ�򿪵���վ��ַ
	String urlStr = "http://www.abc.com";
	public void init() {
		try {
			url = new URL(urlStr);
			
			//����һ���������������
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyAddress, proxyPort));
			//ʹ��ָ���Ĵ��������������
			connection = url.openConnection(proxy);
			//���ó�ʱʱ��
			connection.setConnectTimeout(5000);
			scanner = new Scanner(connection.getInputStream());
			//��ʼ�������
			printStream = new PrintStream("Index.htm");
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				//�ڿ���̨�����ҳ��Դ����
				System.out.println(line);
				//����ҳ��Դ���������ָ�������
				printStream.println(line);
			}
		} catch (Exception e) {
		} finally {
			if(printStream != null) {
				printStream.close();
			}
		}
	}

	public static void main(String[] args) {
		new ProxyTest().init();
	}

}
