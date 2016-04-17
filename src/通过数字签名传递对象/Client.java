package 通过数字签名传递对象;

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
			// 使用名为DSA的算法生成一个公共和私有密钥对
			KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
			// 初始化密钥对，公钥、私钥分别为256位
			generator.initialize(512);
			// 生成密钥对
			KeyPair genKeyPair = generator.genKeyPair();
			System.out.println("Generating Signature...");
			// 获取一个签名对象，使用DSA算法产生签名
			Signature signature = Signature.getInstance("SHA/DSA");
			// 获取公钥
			PublicKey publicKey = genKeyPair.getPublic();
			// 获取私钥
			PrivateKey privateKey = genKeyPair.getPrivate();
			// 用PrivateKey的实例作为参数
			// 使用Signature类的initSign方法进行签名对象的初始化
			signature.initSign(privateKey);
			FileInputStream fis = new FileInputStream("C:/temp.txt");
			byte arr[] = new byte[fis.available()];
			fis.read(arr);
			// 调用Signature类的update方法，把数组提供给签名对象
			signature.update(arr);
			// 产生签名对象
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
