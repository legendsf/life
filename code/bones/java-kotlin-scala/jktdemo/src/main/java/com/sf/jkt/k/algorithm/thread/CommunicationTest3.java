package com.sf.jkt.k.algorithm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
 
 
 
/**
 * 用Lock和Condition，实现主线程执行5次，子线程再执行10次，孙线程执行15次，如此反复5次
 * 
 * 设计思想：
 * 业务类Business中，设计3个方法，每个方法代表主、子、孙
 * 主方法中执行5次
 * 子方法中执行10次
 * 孙方法中执行15次
 * 
 * 用lock锁住各自方法，然后用runFlag来判断是否自己执行，如果不是，则await，如果走完，则把runFlag设为下一个要执行的对象
 * 然后用condition来唤醒下一线程
 *
 */
public class CommunicationTest3 {
	
	
	public static void main(String[] args) {
		
		final Business2 business = new Business2();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					business.sub2(i);
				}
			}
		}){}.start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					business.sub3(i);
				}
			}
		}){}.start();
		
		for (int i = 0; i < 5; i++) {
			business.sub(i);
		}
	}
}
 
class Business2 {
	private int runFlag = 1;
	private Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();
	
	public void sub(int i) {
		lock.lock();
		try {
			if (runFlag != 1) {
				try {
					c1.await(); // 如果不是sub1执行时，让出CPU
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 5; j++) {
				System.out.println("sub1-" + i + "下的" + j + "次");
			}
			runFlag = 2; // 执行完后，让sub执行
			c2.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void sub2(int i) {
		lock.lock();
		try {
			if (runFlag != 2) {
				try {
					c2.await(); // 如果不是sub2该执行时，让出CPU
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 10; j++) {
				System.out.println("sub2-" + i + "下的" + j + "次");
			}
			runFlag = 3; // 执行完后，让sub执行
			c3.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void sub3(int i) {
		lock.lock();
		try {
			if (runFlag != 3) {
				try {
					c3.await(); // 如果不是sub3该执行时，让出CPU
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			for (int j = 0; j < 15; j++) {
				System.out.println("sub3-" + i + "下的" + j + "次");
			}
			runFlag = 1; // 执行完后，让sub1执行
			c1.signal();
		} finally {
			lock.unlock();
		}
	}
}
