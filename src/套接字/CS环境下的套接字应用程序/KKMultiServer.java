/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package �׽���.CS�����µ��׽���Ӧ�ó���;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class KKMultiServer {
    
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = null;
            boolean listening = true;
            try {
                serverSocket = new ServerSocket(4444);
            } catch (IOException ex) {
                Logger.getLogger(KKMultiServer.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(-1);
            }
            while(listening) {
                new KKMultiServerThread(serverSocket.accept()).start();
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(KKMultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
