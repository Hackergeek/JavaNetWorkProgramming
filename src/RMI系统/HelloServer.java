package RMIϵͳ;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * ��3������RMIע����񣬲�ע��Զ�̶���
 * @author skyward
 *
 */
public class HelloServer {
	/**
	 * ����RMIע����񲢽��ж���ע��
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			//����RMIע�����ָ���˿�Ϊ1099��1099ΪĬ�϶˿ڣ�
			//Ҳ����ͨ������$java_home/bin/rmiregistry 1099����
			//����ʹ�����ַ�ʽ���Ա����һ��DOS����
			//����������rmiregistry����ע����񻹱���������RMIC����һ��Stub��Ϊ������
			LocateRegistry.createRegistry(1099);
			//����Զ�̶����һ������ʵ����������hello����
			//�����ò�ͬ������ע�᲻ͬ��ʵ��
			HelloInterface hello = new Hello("Hello, world!");
			
			//��helloע�ᵽRMIע��������ϣ�����ΪHello
			Naming.rebind("Hello", hello);
			
			//���Ҫ��helloʵ��ע�ᵽ��һ̨������RMIע�����Ļ�����
			//Naming.rebind("//192.168.1.105:1099/Hello", hello);
			
			System.out.println("Hello Server is ready.");
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
