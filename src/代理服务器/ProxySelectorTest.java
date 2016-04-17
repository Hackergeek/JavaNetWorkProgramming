package 代理服务器;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import java.util.Scanner;

public class ProxySelectorTest {

	// 测试本地JVM的网络默认配置
	public void setLocalProxy() {
		Properties properties = System.getProperties();
		// 设置HTTP访问要使用的代理服务器的地址
		properties.setProperty("http.proxyHost", "10.10.0.96");
		// 设置HTTP访问要使用的代理服务器的端口
		properties.setProperty("http.proxyPort", "8080");
		// 设置HTTP访问不需要通过代理服务器访问的主机
		// 可以使用*通配符，多个地址用“|”分隔
		properties.setProperty("http.nonProxyHosts", "localhost|10.20.*");
		// 设置安全HTTP访问使用的代理服务器地址与端口
		// 它没有https.nonProxyHosts属性，它按照http.nonProxyHosts中设置的规则访问
		properties.setProperty("https.proxyHost", "192.168.0.96");
		properties.setProperty("https.proxyPort", "443");
		// 设置FTP访问的代理服务器的主机、端口及不需要使用代理服务器的主机
		properties.setProperty("ftp.proxyHost", "10.10.0.96");
		properties.setProperty("ftp.proxyPort", "2121");
		properties.setProperty("ftp.nonProxyHosts", "localhost|10.10.*");
		// 设置socks代理服务器的地址与端口
		properties.setProperty("socks.proxyHost", "10.10.0.96");
		properties.setProperty("socks.proxyPort", "1080");
	}

	// 清除proxy设置
	public void removeLocalProxy() {
		Properties properties = System.getProperties();
		// 清除HTTP访问的代理服务器设置
		properties.remove("http.proxyHost");
		properties.remove("http.proxyPort");
		properties.remove("http.nonProxyHosts");
		// 清除HTTPS访问的代理服务器设置
		properties.remove("https.proxyHost");
		properties.remove("https.proxyPort");
		// 清除FTP访问的代理服务器设置
		properties.remove("ftp.proxyHost");
		properties.remove("ftp.proxyPort");
		properties.remove("ftp.nonProxyHosts");
		// 清除SOCKS访问的代理服务器设置
		properties.remove("socks.proxyHost");
		properties.remove("socks.proxyPort");
	}

	// 测试HTTP访问
	public void showHttpProxy() {
		try {
			URL url = new URL("http://www.ycit.cn");
			// 直接打开连接，但系统会调用刚设置的HTTP代理服务器
			URLConnection connection = url.openConnection();
			Scanner scanner = new Scanner(connection.getInputStream());
			//读取远程主机的内容
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
