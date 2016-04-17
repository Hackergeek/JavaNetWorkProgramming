package RMIϵͳ;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * ��4���ͻ��˲���Զ�̶��󣬲�����Զ�̷���
 * @author skyward
 *
 */
public class HelloClient {

	/**
	 * ����Զ�̶��󲢵���Զ�̷���
	 */
	public static void main(String[] args) {
		try {
			HelloInterface hello = (HelloInterface) Naming.lookup("Hello");
			//���Ҫ����һ̨������RMIע�����Ļ����ϲ���helloʵ��
			//HelloInterface hello = (HelloInterface)Naming.lookup("//192.168.1.105:1099/Hello");
			
			//����Զ�̷���
			System.out.println(hello.say());
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
