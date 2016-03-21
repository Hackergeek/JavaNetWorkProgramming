/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 数据报套接字;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author geek
 */
public class UDPServer {
    
	public static void main(String[] args) {
        try {
            DatagramSocket receiveSocket = new DatagramSocket(5000);
            byte buf[] = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(buf, buf.length);
            System.out.println("starting to receive packet");
            while(true) {
                receiveSocket.receive(receivePacket);
                String name = receivePacket.getAddress().toString();
                System.out.println("\n来自主机：" + name + "\n端口号：" + receivePacket.getPort());
                String s = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("the received data: " + s);
            }
        } catch (IOException ex) {
            System.out.println("网络通信出现错误,问题在" + ex.toString());
        }
    }
}
