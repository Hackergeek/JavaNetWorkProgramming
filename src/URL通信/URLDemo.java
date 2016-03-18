/**
 * 
 */
package URL通信;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * 3.5URLConnection类的应用
 * @author skyward
 *
 */
public class URLDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		System.out.println("starting....");
		int c;
		URL url = new URL("http://www.abc.com");
		URLConnection urlConnection = url.openConnection();
		System.out.println("The date is :" + new Date(urlConnection.getDate()));
		System.out.println("content_type :" + urlConnection.getContentType());
		InputStream in = urlConnection.getInputStream();
		while((c = in.read()) != -1) {
			System.out.print((char) c);
		}
	}

}
