/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package �׽���.CS�����µ��׽���Ӧ�ó���;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class KKMultiServerThread extends Thread {
    private Socket socket = null;
    public KKMultiServerThread(Socket socket) {
        super("KKMultiServerThread");
        this.socket = socket;
    }
    
    public void run() {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine, outputLine;
            KnockKnockProtocol kkp = new KnockKnockProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);
            while((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                System.out.println(inputLine);
                out.println(outputLine);
                if(outputLine.equals("Bye")) {
                    break;
                }
            }
            out.close();
            in.close();
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(KKMultiServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
