package ���������;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

public class ProxySelectorTest {

	// ���Ա���JVM������Ĭ������
	public void setLocalProxy() {
		Properties properties = System.getProperties();
		// ����HTTP����Ҫʹ�õĴ���������ĵ�ַ
		properties.setProperty("http.proxyHost", "10.10.0.96");
		// ����HTTP����Ҫʹ�õĴ���������Ķ˿�
		properties.setProperty("http.proxyPort", "8080");
		// ����HTTP���ʲ���Ҫͨ��������������ʵ�����
		// ����ʹ��*ͨ����������ַ�á�|���ָ�
		properties.setProperty("http.nonProxyHosts", "localhost|10.20.*");
		// ���ð�ȫHTTP����ʹ�õĴ����������ַ��˿�
		// ��û��https.nonProxyHosts���ԣ�������http.nonProxyHosts�����õĹ������
		properties.setProperty("https.proxyHost", "192.168.0.96");
		properties.setProperty("https.proxyPort", "443");
		// ����FTP���ʵĴ�����������������˿ڼ�����Ҫʹ�ô��������������
		properties.setProperty("ftp.proxyHost", "10.10.0.96");
		properties.setProperty("ftp.proxyPort", "2121");
		properties.setProperty("ftp.nonProxyHosts", "localhost|10.10.*");
		// ����socks����������ĵ�ַ��˿�
		properties.setProperty("socks.proxyHost", "10.10.0.96");
		properties.setProperty("socks.proxyPort", "1080");
	}

	// ���proxy����
	public void removeLocalProxy() {
		Properties properties = System.getProperties();
		// ���HTTP���ʵĴ������������
		properties.remove("http.proxyHost");
		properties.remove("http.proxyPort");
		properties.remove("http.nonProxyHosts");
		// ���HTTPS���ʵĴ������������
		properties.remove("https.proxyHost");
		properties.remove("https.proxyPort");
		// ���FTP���ʵĴ������������
		properties.remove("ftp.proxyHost");
		properties.remove("ftp.proxyPort");
		properties.remove("ftp.nonProxyHosts");
		// ���SOCKS���ʵĴ������������
		properties.remove("socks.proxyHost");
		properties.remove("socks.proxyPort");
	}

	// ����HTTP����
	public void showHttpProxy() {
		try {
			URL url = new URL("http://www.ycit.cn");
			// ֱ�Ӵ����ӣ���ϵͳ����ø����õ�HTTP���������
			URLConnection connection = url.openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			//��ȡԶ������������
			while(scanner.hasNextLine()) {
				System.out.println(scanner.nextLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		ProxySelectorTest proxySelectorTest = new ProxySelectorTest();
		proxySelectorTest.setLocalProxy();
		proxySelectorTest.showHttpProxy();
		proxySelectorTest.removeLocalProxy();
	}

}
