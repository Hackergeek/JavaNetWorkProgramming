package ͨ������ǩ�����ݶ���;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

public class Client {
	public static void main(String[] args) {
		Socket s = null;
		ObjectOutputStream os = null;
		try {
			s = new Socket("localhost", 4000);
			os = new ObjectOutputStream(s.getOutputStream());
			System.out
					.println("Generating keys......this may take a few minutes");
			// ʹ����ΪDSA���㷨����һ��������˽����Կ��
			KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
			// ��ʼ����Կ�ԣ���Կ��˽Կ�ֱ�Ϊ256λ
			generator.initialize(512);
			// ������Կ��
			KeyPair genKeyPair = generator.genKeyPair();
			System.out.println("Generating Signature...");
			// ��ȡһ��ǩ������ʹ��DSA�㷨����ǩ��
			Signature signature = Signature.getInstance("SHA/DSA");
			// ��ȡ��Կ
			PublicKey publicKey = genKeyPair.getPublic();
			// ��ȡ˽Կ
			PrivateKey privateKey = genKeyPair.getPrivate();
			// ��PrivateKey��ʵ����Ϊ����
			// ʹ��Signature���initSign��������ǩ������ĳ�ʼ��
			signature.initSign(privateKey);
			FileInputStream fis = new FileInputStream("C:/temp.txt");
			byte arr[] = new byte[fis.available()];
			fis.read(arr);
			// ����Signature���update�������������ṩ��ǩ������
			signature.update(arr);
			// ����ǩ������
			SignedObject signedObject = new SignedObject(arr, signature.sign(),
					publicKey);
			os.writeObject(signedObject);
			fis.close();
			os.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
