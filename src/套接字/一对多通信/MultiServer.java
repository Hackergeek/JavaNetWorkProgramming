/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package �׽���.һ�Զ�ͨ��;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class MultiServer {
    public static void main(String[] args) {
        try {
            System.out.println("�ȴ�����");
            ServerSocket serverSocket = new ServerSocket(5500);
            Socket connectToClient = null;
            while(true) {
                //�ȴ��ͻ��˵�����
                connectToClient = serverSocket.accept();
                new ServerThread(connectToClient);
            }
        } catch (IOException ex) {
            Logger.getLogger(MultiServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
