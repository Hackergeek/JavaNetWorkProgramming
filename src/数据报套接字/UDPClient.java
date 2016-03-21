/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 数据报套接字;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class UDPClient {
    public static void main(String[] args) {
        try {
            DatagramSocket sendSocket = new DatagramSocket(3456);
            String s = "hello! This is the client";
            byte[] databyte = new byte[100];
            databyte = s.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(databyte, s.length(), InetAddress.getByName("127.0.0.1"), 5000);
            sendSocket.send(sendPacket);
            System.out.println("send the data: " + s);
        } catch (SocketException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UDPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
