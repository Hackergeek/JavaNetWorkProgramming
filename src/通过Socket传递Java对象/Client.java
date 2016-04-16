package ͨ��Socket����Java����;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import �������л�.Employee;

public class Client {
	public static void main(String[] args) {
		try {
			Employee joe = new Employee(150, "Joe");
			System.out.println("employeeNumber= " + joe.getEmployeeNumber());
			System.out.println("employeeName= " + joe.getEmployeeName());
			Socket socket = new Socket("127.0.0.1", 44444);
//			System.out.println("���ӳɹ�");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					socket.getOutputStream());
			System.out.println("����������ɹ�");
			ObjectInputStream objectInputStream = new ObjectInputStream(
					socket.getInputStream());
			
			
//			System.out.println("��ʼд��");
			objectOutputStream.writeObject(joe);
//			System.out.println("д��ɹ�");
			joe = (Employee) objectInputStream.readObject();
			System.out.println("employeeNumber= " + joe.getEmployeeNumber());
			System.out.println("employeeName= " + joe.getEmployeeName());
			objectInputStream.close();
			objectOutputStream.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
