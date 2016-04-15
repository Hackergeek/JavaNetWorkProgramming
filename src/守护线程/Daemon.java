/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package 守护线程;

/**
 * 守护线程一般被用于在后台为其他线程提供服务 当一个应用程序的所有非守护线程终止运行时，即使仍然有守护线程在运行，应用程序也将终止
 * 
 * @author geek
 */
public class Daemon extends Thread {
	private static final int SIZE = 10;
	private Thread[] t = new Thread[SIZE];

	public Daemon() {
		// 将线程设置为守护线程
		setDaemon(true);
		start();
	}

	public void run() {
		for (int i = 0; i < SIZE; i++) {
			t[i] = new DaemonSpawn(i);
		}
		for (int i = 0; i < SIZE; i++) {
			System.out.println("t[" + i + "].isDaemon()" + t[i].isDaemon());
		}
		while (true) {
			yield();
		}
	}
}
