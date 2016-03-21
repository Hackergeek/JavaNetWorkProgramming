/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  ÿª§œﬂ≥Ã;

import static java.lang.Thread.yield;

/**
 *
 * @author geek
 */
public class DaemonSpawn extends Thread{
     public DaemonSpawn(int i) {
            System.out.println("DaemonSpawn " + i + " started");
            start();
        }
        
        public void run() {
            while(true) {
                yield();
            }
        }
}
