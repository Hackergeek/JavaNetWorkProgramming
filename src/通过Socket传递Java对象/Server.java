package ͨ��Socket����Java����;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import �������л�.Employee;

public class Server {
	public static void main(String[] args) {
		Employee employee = null;
		try {
			ServerSocket server = new ServerSocket(44444);
			System.out.println("Server Waiting");
			Socket pipe = server.accept();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(pipe.getOutputStream());
			System.out.println("����������ɹ�");
			ObjectInputStream objectInputStream = new ObjectInputStream(pipe.getInputStream());
			
			
			
			employee = (Employee) objectInputStream.readObject();
			employee.setEmployeeNumber(256);
			employee.setEmployeeName("John");
			objectOutputStream.writeObject(employee);
			objectInputStream.close();
			objectOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
