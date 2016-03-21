/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 组播应用_英汉词典;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class Receive extends Frame implements Runnable, ActionListener {

    int port;
    InetAddress group = null;
    MulticastSocket socket = null;
    Button startRece, stopRece;
    Thread thread = null;
    TextArea showReceiving, showReceived;
    boolean stoped = false;

    public Receive() {
        super("瀹氭椂鎺ユ敹淇℃伅");
        thread = new Thread(this);
        startRece = new Button("寮�濮嬫帴鏀�");
        stopRece = new Button("鍋滄鎺ユ敹");
        startRece.addActionListener(this);
        stopRece.addActionListener(this);
        showReceiving = new TextArea(10, 10);
        showReceiving.setForeground(Color.blue);
        showReceived = new TextArea(10, 10);
        Panel north = new Panel();
        north.add(startRece);
        north.add(stopRece);
        add(north, BorderLayout.NORTH);
        Panel center = new Panel();
        center.setLayout(new GridLayout(1, 2));
        center.add(showReceiving);
        center.add(showReceived);
        add(center, BorderLayout.CENTER);
        validate();
        port = 5000;
        try {
            group = InetAddress.getByName("239.255.0.0");
            socket = new MulticastSocket(port);
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
        new Receive();
    }

    public void run() {
        while (true) {
            byte[] data = new byte[8192];
            DatagramPacket packet = null;
            packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                showReceiving.setText("姝ｅ湪鎺ユ敹鐨勫唴瀹癸細\n" + message);
                showReceived.append(message + "\n");
            } catch (IOException ex) {
                Logger.getLogger(Receive.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (stoped) {
                break;
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startRece) {
            startRece.setBackground(Color.blue);
            stopRece.setBackground(Color.gray);
            if (!(thread.isAlive())) {
                thread = new Thread(this);
            }
            thread.start();
            stoped = false;
        }
        if (e.getSource() == stopRece) {
            startRece.setBackground(Color.gray);
            stopRece.setBackground(Color.blue);
            thread.interrupt();
            stoped = true;
        }
    }

}
