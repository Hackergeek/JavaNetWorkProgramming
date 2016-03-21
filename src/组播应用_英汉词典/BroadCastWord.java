/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 组播应用_英汉词典;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author geek
 */
public class BroadCastWord extends Frame implements ActionListener {
    
    int port;
    InetAddress group = null;
    MulticastSocket socket = null;
    Timer time = null;
    FileDialog open = null;
    Button select, startBroadCast, stopBroadCast;
    File file = null;
    String fileDir = null, fileName = null;
    FileReader in = null;
    BufferedReader br = null;
    int token = 0;
    TextArea showPlaying, showPlayed;
    
    public BroadCastWord() {
        super("单词广播系统");
        select = new Button("选择要广播的文件");
        startBroadCast = new Button("开始广播");
        stopBroadCast = new Button("停止广播");
        startBroadCast.addActionListener(this);
        startBroadCast.setEnabled(false);
        select.addActionListener(this);
        stopBroadCast.addActionListener(this);
        stopBroadCast.setEnabled(false);
        time = new Timer(2000, this);
        open = new FileDialog(this, "选择要广播的文件", FileDialog.LOAD);
        showPlaying = new TextArea(10, 10);
        showPlaying.setForeground(Color.blue);
        showPlayed = new TextArea(10, 10);
        Panel north = new Panel();
        north.add(select);
        north.add(startBroadCast);
        north.add(stopBroadCast);
        add(north, BorderLayout.NORTH);
        Panel center = new Panel();
        center.setLayout(new GridLayout(1, 2));
        center.add(showPlaying);
        center.add(showPlayed);
        add(center, BorderLayout.CENTER);
        validate();
        port = 5000;
        try {
            group = InetAddress.getByName("239.255.0.0");
            socket = new MulticastSocket(port);
            socket.setTimeToLive(1);
            socket.joinGroup(group);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBounds(100, 50, 360, 380);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
            
        });
    }
    
    public static void main(String[] args) {
        new BroadCastWord();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == select) {
            showPlayed.setText(null);
            open.setVisible(true);
            fileName = open.getFile();
            fileDir = open.getDirectory();
            file = new File(fileDir, fileName);
            try {
                in = new FileReader(file);
                br = new BufferedReader(in);
                startBroadCast.setEnabled(true);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(BroadCastWord.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (e.getSource() == startBroadCast) {
            time.start();
            startBroadCast.setEnabled(false);
            stopBroadCast.setEnabled(true);
        } else if (e.getSource() == time) {
            String s = null;
            try {
                if (token == -1) {
                    file = new File(fileDir, fileName);
                    in = new FileReader(file);
                    br = new BufferedReader(in);
                }
                s = br.readLine();
                if (s != null) {
                    token = 0;
                    showPlaying.setText("正在广播的内容：\n" + s);
                    showPlayed.append(s + "\n");
                    byte buf[] = s.getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length, group, port);
                    socket.send(packet);
                } else {
                    token = -1;
                }
            } catch (Exception ex) {
                
            }
            
        } else if (e.getSource()
                == stopBroadCast) {
            time.stop();
            stopBroadCast.setEnabled(false);
            startBroadCast.setEnabled(true);
        }
    }
    
}
