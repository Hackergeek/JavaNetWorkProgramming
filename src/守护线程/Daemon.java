/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  ÿª§œﬂ≥Ã;

/**
 *
 * @author geek
 */
public class Daemon extends Thread {
    private static final int SIZE = 10;
        private Thread[] t = new Thread[SIZE];
        public Daemon() {
            setDaemon(true);
            start();
        }
        
        public void run() {
            for(int i = 0; i < SIZE; i++) {
                t[i] = new DaemonSpawn(i);
            }
            for(int i = 0; i < SIZE; i++) {
                System.out.println("t[" + i + "].isDaemon()" + t[i].isDaemon());
            }
            while(true) {
                yield();
            }
        }
}
