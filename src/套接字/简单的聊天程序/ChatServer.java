/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 套接字.简单的聊天程序;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author geek
 */
public class ChatServer implements ActionListener, Runnable{
    JTextArea showArea;
    JTextField msgText;
    JFrame mainJFrame;
    JButton sentBtn;
    JScrollPane JSPane;
    JPanel pane;
    Container con;
    Thread thread = null;
    ServerSocket serverSocket;
    Socket connectToClient;
    DataInputStream inFromClient;
    DataOutputStream outToClient;
    
    public ChatServer() {
        mainJFrame = new JFrame("聊天——服务器端");
        con = mainJFrame.getContentPane();
        showArea = new JTextArea();
    }
    public static void main(String[] args) {
        new ChatServer();
    }
    
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
