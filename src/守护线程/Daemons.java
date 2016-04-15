/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  ÿª§œﬂ≥Ã;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek
 */
public class Daemons {
	public static void main(String[] args) {
		Thread d = new Daemon();
		System.out.println("d.isDaemon() = " + d.isDaemon());
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		System.out.println("Waiting for CR");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			Logger.getLogger(Daemons.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}
}
