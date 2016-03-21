/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 组播套接字CS程序;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class MulticastServerThread extends QuoteServerThread {
    private long FIVE_SECONDS = 5000;
    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }
    public void run() {
        while(moreQuotes) {
            try {
                byte[] buf = new byte[256];
                String dString = null;
                if(in == null) {
                    dString = new Date().toString();
                } else {
                    dString = getNextQuote();
                }
                buf = dString.getBytes();
                InetAddress group = InetAddress.getByName("236.122.133.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
                socket.send(packet);
                sleep((long) (Math.random() * FIVE_SECONDS));
            } catch (UnknownHostException ex) {
                Logger.getLogger(MulticastServerThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                moreQuotes = false;
            } catch (InterruptedException ex) {
                Logger.getLogger(MulticastServerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        socket.close();
    }
}
