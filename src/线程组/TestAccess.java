/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package �߳���;

/**
 *��ν�߳��飬���ǽ��߳̽��з��飬�������Ͳ���
 *ÿ���̶߳�������Ψһһ���߳��飬����߳������̴߳���ʱָ�������̵߳��������������ڶ����ܸ���
 *���߳����ڴ���ʱû��ָ�������߳��飬����߳���Ĭ������ϵͳ�߳���
 * @author geek
 */
public class TestAccess {
    public static void main(String[] args) {
        ThreadGroup
                x = new ThreadGroup("x"),
                y = new ThreadGroup(x, "y"),
                z = new ThreadGroup(y, "z");
        Thread 
        	one = new TestThread1(x, "one"),
        	two = new TestThread2(z, "two");
    }
}

class TestThread1 extends Thread {
	private int i;
	public TestThread1(ThreadGroup g, String name) {
		super(g, name);
	}
	
	void f() {
		i++;
		System.out.println(getName() + " f( )");
	}
}

class TestThread2 extends TestThread1 {
	public TestThread2(ThreadGroup g, String name) {
		super(g, name);
		start();
	}
	
	public void run() {
		ThreadGroup g = getThreadGroup().getParent().getParent();
		g.list();
		Thread[] gAll = new Thread[g.activeCount()];
		System.out.println(g.activeCount());
		g.enumerate(gAll);
		for(int i = 0; i < gAll.length; i++) {
			gAll[i].setPriority(Thread.MIN_PRIORITY);
			((TestThread1) gAll[i]).f();
		}
		g.list();
	}
}
