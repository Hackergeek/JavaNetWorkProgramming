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
public class MCClient {
    public static void main(String[] args) {
        try {
            MulticastSocket s = new MulticastSocket(10000);
            InetAddress group = InetAddress.getByName("231.0.0.1");
            s.joinGroup(group);
            for(int i = 0; i < 10; i++) {
                byte[] buffer = new byte[256];
                DatagramPacket dgp = new DatagramPacket(buffer, buffer.length);
                s.receive(dgp);
                byte[] buffer2 = new byte[dgp.getLength()];
                System.arraycopy(dgp.getData(), 0, buffer2, 0, dgp.getLength());
                System.out.println(new String(buffer2));
            }
            s.leaveGroup(group);
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(MCClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
