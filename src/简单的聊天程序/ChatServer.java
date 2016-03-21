/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 简单的聊天程序;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class ChatServer implements ActionListener, Runnable {

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
        showArea.setEditable(false);
        showArea.setLineWrap(true);
        JSPane = new JScrollPane(showArea);
        msgText = new JTextField();
        msgText.setColumns(30);
        msgText.addActionListener(this);
        sentBtn = new JButton("发送");
        sentBtn.addActionListener(this);
        pane = new JPanel();
        pane.setLayout(new FlowLayout());
        pane.add(msgText);
        pane.add(sentBtn);
        con.add(JSPane, BorderLayout.CENTER);
        con.add(pane, BorderLayout.SOUTH);
        mainJFrame.setSize(500, 400);
        mainJFrame.setVisible(true);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            serverSocket = new ServerSocket(5500);
            showArea.append("正在等待对话请求\n");
            connectToClient = serverSocket.accept();
            inFromClient = new DataInputStream(connectToClient.getInputStream());
            outToClient = new DataOutputStream(connectToClient.getOutputStream());
            thread = new Thread(this);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
        } catch (IOException e) {
            showArea.append("对不起,不能创建服务器\n");
            msgText.setEditable(false);
            sentBtn.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        new ChatServer();
    }

    public void actionPerformed(ActionEvent e) {
        String s = msgText.getText();
        if (s.length() > 0) {
            try {
                outToClient.writeUTF(s);
                outToClient.flush();
                showArea.append("我说： " + msgText.getText() + "\n");
                msgText.setText(null);
            } catch (IOException exception) {
                showArea.append("你的消息： “" + msgText.getText() + "” 未能发送出去");
            }
        }
    }

    //本线程负责将客户机传来的信息显示在对话区域
    public void run() {
        try {
            while (true) {
                showArea.append("对方说：" + inFromClient.readUTF() + "\n");
                Thread.sleep(1000);
            }
            }catch(IOException exception) {
            }catch(InterruptedException ie) {
            }
    }

}
