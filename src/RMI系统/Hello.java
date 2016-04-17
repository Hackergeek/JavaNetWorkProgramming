package RMIϵͳ;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
/**
 * ��2��ʵ��Զ�̽ӿڼ�Զ�̷������̳�UnicastRemoteObject��
 * ��չ��UnicastRemoteObject�࣬��ʵ��Զ�̽ӿ�HelloInterface
 * @author skyward
 *
 */
public class Hello extends UnicastRemoteObject implements HelloInterface {
	private String message;
	/**
	 * ���붨�幹�췽������ʹ��Ĭ�Ϲ��췽����Ҳ���������ȷ��д��������Ϊ�������׳�RemoteException�쳣
	 * @throws RemoteException
	 */
	public Hello(String msg) throws RemoteException {
		message = msg;
	}

	/**
	 * Զ�̽ӿڷ�����ʵ��
	 */
	@Override
	public String say() throws RemoteException {
		System.out.println("Called by HelloClient");
		return message;
	}

}
