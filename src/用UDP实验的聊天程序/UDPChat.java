/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 用UDP实验的聊天程序;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author geek
 */
public class UDPChat implements Runnable, ActionListener {
    
    JTextArea showArea;
    JLabel lbl1, lbl2, lbl3;
    JTextField msgText, sendPortText, receivePortText, IPAddressText;
    JFrame mainJFrame;
    JButton sendBtn, startBtn;
    JScrollPane JSPane;
    JPanel pane1, pane2;
    Container con;
    Thread thread = null;
    DatagramPacket sendPacket, receivePacket;
    DatagramSocket sendSocket, receiveSocket;
    private InetAddress sendIP;
    private int sendPort, receivePort;
    private byte inBuf[], outBuf[];
    public static final int BUFSIZE = 1024;
    
    public UDPChat() {
        mainJFrame = new JFrame("聊天——UDP协议");
        con = mainJFrame.getContentPane();
        showArea = new JTextArea();
        showArea.setEditable(false);
        showArea.setLineWrap(true);
        lbl1 = new JLabel("接收端口号：");
        lbl2 = new JLabel("发送端口号：");
        lbl3 = new JLabel("对方的地址：");
        sendPortText = new JTextField();
        sendPortText.setColumns(5);
        receivePortText = new JTextField();
        receivePortText.setColumns(5);
        IPAddressText = new JTextField();
        IPAddressText.setColumns(8);
        startBtn = new JButton("开始");
        startBtn.addActionListener(this);
        pane1 = new JPanel();
        pane1.setLayout(new FlowLayout());
        pane1.add(lbl1);
        pane1.add(receivePortText);
        pane1.add(lbl2);
        pane1.add(sendPortText);
        pane1.add(lbl3);
        pane1.add(IPAddressText);
        pane1.add(startBtn);
        JSPane = new JScrollPane(showArea);
        msgText = new JTextField();
        msgText.setColumns(40);
        msgText.setEditable(false);
        msgText.addActionListener(this);
        sendBtn = new JButton("发送");
        sendBtn.setEnabled(false);
        sendBtn.addActionListener(this);
        pane2 = new JPanel();
        pane2.setLayout(new FlowLayout());
        pane2.add(msgText);
        pane2.add(sendBtn);
        con.add(pane1, BorderLayout.NORTH);
        con.add(JSPane, BorderLayout.CENTER);
        con.add(pane2, BorderLayout.SOUTH);
        mainJFrame.setSize(600, 400);
        mainJFrame.setVisible(true);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        new UDPChat();
    }
    
    public void run() {
        String msgstr;
        while (true) {
            try {
                receiveSocket.receive(receivePacket);
                msgstr = new String(receivePacket.getData(), 0, receivePacket.getLength());
                showArea.append("对方说： " + msgstr + "\n");
            } catch (IOException ex) {
                showArea.append("接收数据出错\n");
            }
            
        }
    }
    
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == startBtn) {
                inBuf = new byte[BUFSIZE];
                sendPort = Integer.parseInt(sendPortText.getText());
                sendIP = InetAddress.getByName(IPAddressText.getText());
                sendSocket = new DatagramSocket();
                receivePort = Integer.parseInt(receivePortText.getText());
                receivePacket = new DatagramPacket(inBuf, BUFSIZE);
                receiveSocket = new DatagramSocket(receivePort);
                thread = new Thread(this);
                thread.setPriority(Thread.MIN_PRIORITY);
                thread.start();
                startBtn.setEnabled(false);
                sendBtn.setEnabled(true);
                msgText.setEditable(true);
            } else {
                outBuf = msgText.getText().getBytes();
                sendPacket = new DatagramPacket(outBuf, outBuf.length, sendIP, sendPort);
                sendSocket.send(sendPacket);
                showArea.append("我说： " + msgText.getText() + "\n");
                msgText.setText(null);
            }
        } catch (UnknownHostException ukhe) {
            showArea.append("无法连接到指定地址\n");
        } catch (SocketException se) {
            showArea.append("无法打开指定端口\n");
        } catch (IOException ie) {
            showArea.append("发送数据失败\n");
        }
        
    }
}
