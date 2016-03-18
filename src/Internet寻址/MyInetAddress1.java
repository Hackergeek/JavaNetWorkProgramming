package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress1 {
	/* 
	 * 2.1创建InetAddress对象————通过getLocalHost方法得到本机的InetAddress对象
	 * InetAddress类是Java用于描述IP地址的类
	 * 要想创建InetAddress对象，必须依靠它的四个静态方法。
	 * InetAddress可以通过getLocalHost方法得到本机的InetAddress对象，
	 * 也可以通过getByName、getAllByName和getByAddress得到远程主机的InetAddress对象
	 * 当本机绑定了多个IP时，getLocalHost只返回第一个IP。如果想返回本机全部的IP，可以使用getAllByName方法
	 * 
	 */

	public static void main(String[] args) {
		InetAddress localHost;
		try {
			localHost = InetAddress.getLocalHost();
			System.out.println(localHost);
			//当查找不到本地主机的地址时，触发一个UnknownHostException异常
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}
