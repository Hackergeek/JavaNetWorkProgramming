/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 套接字.一对一通信;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*
* @author geek
*/
public class Client {

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       try {
           //连接到本机，端口号为5500
           Socket connectToServer = new Socket("localhost", 5500);
           //将数据输入流连接到socket上
           DataInputStream inFromServer = new DataInputStream(connectToServer.getInputStream());
           //将数据输出流连接到socket上
           DataOutputStream outToServer = new DataOutputStream(connectToServer.getOutputStream());
           System.out.println("输入半径数值发送到服务器，输入bye结束。");
           String outStr,inStr;
           boolean goon = true;
           BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
           //反复读取用户的数据并计算
           while(goon) {
               outStr = buf.readLine();
               outToServer.writeUTF(outStr);
               outToServer.flush();
               inStr = inFromServer.readUTF();
               if(!inStr.equals("bye")) {
                   System.out.println("从服务器返回的结果是" + inStr);
               } else {
                   goon = false;
               }
           }   
           inFromServer.close();
           outToServer.close();
           connectToServer.close();
       } catch (IOException ex) {
           Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
}
