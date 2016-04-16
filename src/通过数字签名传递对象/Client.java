package ͨ������ǩ�����ݶ���;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

public class Client {
	public static void main(String[] args) {
		Socket s = null;
		ObjectOutputStream os = null;
		try {
			s = new Socket("localhost", 4000);
			os = new ObjectOutputStream(s.getOutputStream());
			System.out.println("Generating keys......this may take a few minutes");
			//ʹ����ΪDSA���㷨����һ��������˽����Կ��
			KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
			//��ʼ����Կ�ԣ���Կ��˽Կ�ֱ�Ϊ256λ
			generator.initialize(512);
			//������Կ��
			KeyPair genKeyPair = generator.genKeyPair();
			System.out.println("Generating Signature...");
			//��ȡһ��ǩ������ʹ��DSA�㷨����ǩ��
			Signature signature = Signature.getInstance("SHA/DSA");
			//��ȡ��Կ
			PublicKey publicKey = genKeyPair.getPublic();
			//��ȡ˽Կ
			PrivateKey privateKey = genKeyPair.getPrivate();
			//��PrivateKey��ʵ����Ϊ����
			//ʹ��Signature���initSign��������ǩ������ĳ�ʼ��
			signature.initSign(privateKey);
			FileInputStream fis = new FileInputStream("");
			byte arr[] = new byte[fis.available()];
			fis.read(arr);
			//����Signature���update�������������ṩ��ǩ������
			signature.update(arr);
			//����ǩ������
			SignedObject signedObject = new SignedObject(arr, signature.sign(), publicKey);
			os.writeObject(signedObject);
			fis.close();
			os.close();
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SignatureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
