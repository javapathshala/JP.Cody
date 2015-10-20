/**
 * 
 */
package com.jp.core.threads;

/**
 * @author dimit.chadha
 * 
 */
public class ThreadJoin implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ThreadJoin tj = new ThreadJoin();

		Thread t1 = new Thread(tj, "1");
		Thread t2 = new Thread(tj, "2");
		Thread t3 = new Thread(tj, "3");

		t1.start();
		t2.start();
		t3.start();
		t2.join();

	}

	public void run() {
		System.out.println(Thread.currentThread().getName() + " is Started");
		try {
			for (int x = 1; x <= 5; x++) {
				System.out.println("This is Thread No : "
						+ Thread.currentThread().getName() + " :: Count :: "
						+ x);
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()
						+ " is Completed");

				
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
