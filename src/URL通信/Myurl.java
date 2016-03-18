/**
 * 
 */
package URL通信;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 3.3URL类的应用
 * @author skyward
 *
 */
public class Myurl {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			URL url = new URL("http://www.ycit.edu.cn/chn/index.htm");
			System.out.println("the Protocol: " + url.getProtocol());
			System.out.println("the hostname: " + url.getHost());
			System.out.println("the port: " + url.getPort());
			System.out.println("the file: " + url.getFile());
			System.out.println(url.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
