/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 套接字.一对多通信;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class ServerThread extends Thread {
    private Socket connectToClient;
    private DataInputStream inFromClient;
    private DataOutputStream outToClient;

    //鍦ㄦ瀯閫犳柟娉曚腑涓烘瘡涓鎺ュ瓧杩炴帴杈撳叆/杈撳嚭娴�
    public ServerThread(Socket socket) throws IOException {
        super();
        connectToClient = socket;
        inFromClient = new DataInputStream(connectToClient.getInputStream());
        outToClient = new DataOutputStream(connectToClient.getOutputStream());
        start();
    }
    
    public void run() {
        try {
            String str;
            double radius, area;
            boolean goon = true;
            while (goon) {
                //浠巗ocke涓鍙栨暟鎹�
                str = inFromClient.readUTF();
                if (!str.equals("bye")) {
                    radius = Double.parseDouble(str);
                    System.out.println("鎺ユ敹鍒扮殑鍗婂緞鍊间负锛�" + radius);
                    area = radius * radius * Math.PI;
                    str = Double.toString(area);
                    //鍚憇ocket涓啓鏁版嵁
                    outToClient.writeUTF(str);
                    outToClient.flush();
                    System.out.println("鍦嗛潰绉�" + str + "宸茬粡鍙戦��");
                } else {
                    goon = false;
                    outToClient.writeUTF("bye");
                    outToClient.flush();
                }
            }
            outToClient.close();
            inFromClient.close();
            connectToClient.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
