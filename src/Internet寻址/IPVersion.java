package Internet儖峽;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class IPVersion {

	/*
	 * A		0。。。。。。。
	 * B		10。。。。。。
	 * C		110。。。。。
	 * D		1110。。。。
	 * E		11110。。。
	 */
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
		    String s = scanner.next();
			InetAddress inetadd = InetAddress.getLocalHost();
			byte[] address = inetadd.getAddress();
			if (address.length == 4) {
				System.out.println("The IP version is IPv4");
				int firstbyte = address[0];
				if (firstbyte < 0) {
					firstbyte += 256;
				}
				if ((firstbyte & 0x80) == 0) {
					System.out.println("The IP class is A");
				} else if ((firstbyte & 0xC0) == 0x80) {
					System.out.println("The IP class is B");
				} else if ((firstbyte & 0xE0) == 0xC0) {
					System.out.println("The IP class is C");
				} else if((firstbyte & 0xF0) == 0xE0) {
					System.out.println("The IP class is D");
				} else if((firstbyte & 0xF8) == 0xF0) {
					System.out.println("The IP class is E");
				}
			} else if(address.length == 16) {
				System.out.println("The IP version is IPv6");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
