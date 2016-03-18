package Internet寻址;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MyInetAddress3 {

	/*
	 * 2.1创建InetAddress对象————通过getAllByName方法得到远程主机所有的InetAddress对象
	 * 使用getAllByName方法可以从DNS上得到域名对应的所有IP。这个方法返回一个InetAddress类型的数组
	 * 
	 * 与getByName方法一样，getAllByName也不会验证IP地址是否存在
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
