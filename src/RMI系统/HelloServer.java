package RMI系统;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * （3）启动RMI注册服务，并注册远程对象
 * @author skyward
 *
 */
public class HelloServer {
	/**
	 * 启动RMI注册服务并进行对象注册
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//启动RMI注册服务，指定端口为1099（1099为默认端口）
			//也可以通过命令$java_home/bin/rmiregistry 1099启动
			//这里使用这种方式可以避免打开一个DOS窗口
			//而且用命令rmiregistry启动注册服务还必须事先用RMIC生成一个Stub类为它所用
			LocateRegistry.createRegistry(1099);
			//创建远程对象的一个或多个实例，下面是hello对象
			//可以用不同的名字注册不同的实例
			HelloInterface hello = new Hello("Hello, world!");
			
			//把hello注册到RMI注册服务器上，命名为Hello
			Naming.rebind("Hello", hello);
			
			//如果要把hello实例注册到另一台启动了RMI注册服务的机器上
			//Naming.rebind("//192.168.1.105:1099/Hello", hello);
			
			System.out.println("Hello Server is ready.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
