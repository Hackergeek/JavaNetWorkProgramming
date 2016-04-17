package RMI系统;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * 创建RMI系统的步骤：
 * （1）创建远程接口及声明远程方法（HelloInterface)
 * （2）实现远程接口及远程方法（Hello）
 * （3）启动RMI注册服务，并注册远程对象（HelloServer）
 * （4）客户端查找远程对象，并调用远程方法（HelloClient）
 * （5）启动服务HelloServer并运行客户端HelloClient进行调用
 * @author skyward
 * 
 * （1）创建远程接口及声明远程方法
 * 远程接口必须扩展接口java.rmi.Remote
 *
 */
public interface HelloInterface extends Remote{
	/**
	 * @return 远程接口方法必须抛出 java.rmi.RemoteException
	 * @throws RemoteException
	 */
	public String say() throws RemoteException;

}
