package 通过数字签名传递对象;

import java.io.Serializable;
import java.security.PublicKey;

public class SignedObject implements Serializable {
	
	byte b[];
	byte sig[];
	PublicKey pub;
	
	public SignedObject(byte b[], byte[] sig, PublicKey pub) {
		this.b = b;
		this.sig = sig;
		this.pub = pub;
	}

}
