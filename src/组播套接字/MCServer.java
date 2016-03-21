/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 组播套接字;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class MCServer {
    public static void main(String[] args) {
        try {
            System.out.println("Server starting...\n");
            MulticastSocket s = new MulticastSocket();
            InetAddress group = InetAddress.getByName("231.0.0.1");
            byte[] dummy = new byte[0];
            DatagramPacket dgp = new DatagramPacket(dummy, 0, group, 10000);
            for(int i = 0; i < 30000; i++) {
                byte[] buffer = ("Video line " + i).getBytes();
                dgp.setData(buffer);
                dgp.setLength(buffer.length);
                s.send(dgp);
            }
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MCServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
