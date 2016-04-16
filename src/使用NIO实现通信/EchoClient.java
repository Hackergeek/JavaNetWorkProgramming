package ʹ��NIOʵ��ͨ��;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Iterator;
import java.util.Scanner;


public class EchoClient {
	static InetSocketAddress ip = new InetSocketAddress("localhost", 8888);
	static CharsetEncoder encoder = Charset.forName("GB2312").newEncoder();

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			SocketChannel client = SocketChannel.open();
			client.configureBlocking(false);
			Selector selector = Selector.open();
			client.register(selector, SelectionKey.OP_CONNECT);
			client.connect(ip);
			ByteBuffer buffer = ByteBuffer.allocate(32);
			FOR: for(;;) {
				selector.select();
				Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
				while (iterator.hasNext()) {
					SelectionKey selectionKey = (SelectionKey) iterator.next();
					iterator.remove();
					if(selectionKey.isConnectable()) {
						SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
						if(socketChannel.isConnectionPending()) {
							socketChannel.finishConnect();
						}
						socketChannel.write(encoder.encode(CharBuffer.wrap("���")));					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
