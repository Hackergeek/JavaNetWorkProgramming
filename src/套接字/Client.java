package Ì×½Ó×Ö;

import java.io.IOException;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 5500);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
