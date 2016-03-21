/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 数据报套接字;

import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author geek
 */
public class UDPScan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        for(int port = 1024; port <= 65535; port++) {
            try {
                DatagramSocket server = new DatagramSocket(port);
                server.close();
            } catch (SocketException ex) {
                System.out.println("there is a server in port" + port);
            }
        }
    }
    
}
