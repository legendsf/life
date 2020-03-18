package com.sf.jkt.k.algorithm.thread;

import java.util.concurrent.TimeUnit;
 
public class PrintOneTwo {
	static int state = 1;
	static Object obj = new Object();
 
	static Thread One = new Thread(new Runnable() {
 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!One.isInterrupted()) {
				synchronized (obj) {
					if (state % 3 == 1) {
//						try {
//							TimeUnit.MILLISECONDS.sleep(200);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							break;
//						}
						System.out.print(1);
						state++;
					}
				}
			}
		}
 
	});
	static Thread Two = new Thread(new Runnable() {
 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Two.isInterrupted()) {
				synchronized (obj) {
					if (state % 3 == 2) {
//						try {
//							TimeUnit.MILLISECONDS.sleep(200);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							break;
//						}
						System.out.print(2);
						state++;
					}
				}
			}
		}
 
	});
	static Thread Thr = new Thread(new Runnable() {
 
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (!Thr.isInterrupted()) {
				synchronized (obj) {
					if (state % 3 == 0) {
//						try {
//							TimeUnit.MILLISECONDS.sleep(200);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//							break;
//						}
						System.out.print(3);
						state++;
					}
				}
			}
		}
 
	});
 
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Thr.start();
		Two.start();
		One.start();
		TimeUnit.MILLISECONDS.sleep(10000);
		One.interrupt();
		Two.interrupt();
		Thr.interrupt();
	}
 
}
