package ʹ��NIOʵ��ͨ��;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class EchoServer {
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private int port = 8888;

	public EchoServer() {
		try {
			// ����һ��Selector����
			selector = Selector.open();
			// ����һ��ServerSocketChannel����
			serverSocketChannel = ServerSocketChannel.open();
			// ʹ����ͬһ�����Ϲر��˷��������򣬽������������÷���������ʱ������˳���󶨵���ͬ�Ķ˿�
			serverSocketChannel.socket().setReuseAddress(true);
			// ʹServerSocketChannel�����ڷ�����ģʽ
			serverSocketChannel.configureBlocking(false);
			// �ѷ�����������һ�����ض˿ڰ�
			serverSocketChannel.socket().bind(new InetSocketAddress(port));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void receive(SelectionKey key) throws IOException {
		//�����SelectionKey�����ĸ���
		ByteBuffer buffer = (ByteBuffer) key.attachment();
		//��ȡ��SelectionKey������SocketChannel
		SocketChannel socketChannel = (SocketChannel) key.channel();
		//����һ��ByteBuffer�����ڴ�Ŷ���������
		ByteBuffer readBuffer = ByteBuffer.allocate(32);
		socketChannel.read(readBuffer);
		readBuffer.flip();
		//��buffer�ļ�����Ϊ����
		buffer.limit(buffer.capacity());
		//��readBuffer�����ݿ�����buffer��
		//�ٶ�buffer�������㹻�󣬲�����ֻ���������쳣
		buffer.put(readBuffer);
	}

	public void service() throws IOException {
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		while (selector.select() > 0) {
			// ���Selector��selected-keys����
			Set<SelectionKey> readKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = readKeys.iterator();
			while (iterator.hasNext()) {
				SelectionKey key = null;
				try {
					// ȡ��һ��SelectionKey
					key = iterator.next();
					// ��SelectionKey��Selector��selected-key������ɾ��
					iterator.remove();
					if (key.isAcceptable()) {
						// ����������Ӿ����¼�
						// ��ȡ��SelectionKey������ServerSocketChannel
						ServerSocketChannel ssc = (ServerSocketChannel) key
								.channel();
						// �����ͻ������ӵ�SocketChannel
						SocketChannel socketChannel = ssc.accept();
						System.out.println("���յ��ͻ����ӣ����ԣ�"
								+ socketChannel.socket().getInetAddress() + ":"
								+ socketChannel.socket().getPort());
						// ��SocketChannel����Ϊ������ģʽ
						socketChannel.configureBlocking(false);
						// ����һ�����ڴ���û������������ݻ�����
						ByteBuffer buffer = ByteBuffer.allocate(1024);
						// SocketChannel��Selectorע���������д�����¼�,����һ��buffer����
						socketChannel.register(selector, SelectionKey.OP_READ
								| SelectionKey.OP_WRITE, buffer);
					}
					if (key.isReadable()) {
						// ����������¼�
						receive(key);
					}
					if (key.isWritable()) {
						// ����д�����¼�
					}
				} catch (Exception e) {
					if (key != null) {
						// ʹ�����SelectionKeyʧЧ
						// ʹ��Selector���ټ�����SelectionKey����Ȥ���¼�
						key.cancel();
						// �ر������SelectionKey������SocketChannel
						key.channel().close();
					}
				}

			}
		}

	}

	public static void main(String[] args) {

	}

}
