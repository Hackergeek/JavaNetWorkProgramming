package RMIϵͳ;

import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 * ����RMIϵͳ�Ĳ��裺
 * ��1������Զ�̽ӿڼ�����Զ�̷�����HelloInterface)
 * ��2��ʵ��Զ�̽ӿڼ�Զ�̷�����Hello��
 * ��3������RMIע����񣬲�ע��Զ�̶���HelloServer��
 * ��4���ͻ��˲���Զ�̶��󣬲�����Զ�̷�����HelloClient��
 * ��5����������HelloServer�����пͻ���HelloClient���е���
 * @author skyward
 * 
 * ��1������Զ�̽ӿڼ�����Զ�̷���
 * Զ�̽ӿڱ�����չ�ӿ�java.rmi.Remote
 *
 */
public interface HelloInterface extends Remote{
	/**
	 * @return Զ�̽ӿڷ��������׳� java.rmi.RemoteException
	 * @throws RemoteException
	 */
	public String say() throws RemoteException;

}
