package RMI系统;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * （2）实现远程接口及远程方法（继承UnicastRemoteObject）
 * 扩展了UnicastRemoteObject类，并实现远程接口HelloInterface
 * @author skyward
 *
 */
public class Hello extends UnicastRemoteObject implements HelloInterface {
	private String message;
	/**
	 * 必须定义构造方法，即使是默认构造方法，也必须把它明确地写出来，因为它必须抛出RemoteException异常
	 * @throws RemoteException
	 */
	public Hello(String msg) throws RemoteException {
		message = msg;
	}

	/**
	 * 远程接口方法的实现
	 */
	@Override
	public String say() throws RemoteException {
		System.out.println("Called by HelloClient");
		return message;
	}

}
