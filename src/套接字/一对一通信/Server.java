/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 套接字.一对一通信;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author geek
*/
public class Server {

   public static void main(String[] args) {
       try {
           System.out.println("等待连接");
           //创建服务器套接字，端口号5500必须与客户端一致
           ServerSocket serverSocket = new ServerSocket(5500);
           //侦听来自客户端的连接请求
           Socket connectToClient = serverSocket.accept();
           System.out.println("连接请求来自" + connectToClient.getInetAddress().getHostAddress());
           DataInputStream inFromClient = new DataInputStream(connectToClient.getInputStream());
           DataOutputStream outToClient = new DataOutputStream(connectToClient.getOutputStream());
           String str;
           double radius, area;
           boolean goon = true;
           while (goon) {
               //从socke中读取数据
               str = inFromClient.readUTF();
               if (!str.equals("bye")) {
                   radius = Double.parseDouble(str);
                   System.out.println("接收到的半径值为：" + radius);
                   area = radius * radius * Math.PI;
                   str = Double.toString(area);
                   //向socket中写数据
                   outToClient.writeUTF(str);
                   outToClient.flush();
                   System.out.println("圆面积" + str + "已经发送");
               } else {
                   goon = false;
                   outToClient.writeUTF("bye");
                   outToClient.flush();
               }
           }
           outToClient.close();
           inFromClient.close();
           connectToClient.close();
           serverSocket.close();
       } catch (IOException ex) {
           Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
}
