package RMI系统;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * （4）客户端查找远程对象，并调用远程方法
 * @author skyward
 *
 */
public class HelloClient {

	/**
	 * 查找远程对象并调用远程方法
	 */
	public static void main(String[] args) {
		try {
			HelloInterface hello = (HelloInterface) Naming.lookup("Hello");
			//如果要从另一台启动了RMI注册服务的机器上查找hello实例
			//HelloInterface hello = (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");
			
			//调用远程方法
			System.out.println(hello.say());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
