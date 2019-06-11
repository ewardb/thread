package com.roocon.thread.t9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sequence {


	/**
	 * Lock需要显示地获取和释放锁，繁琐能让代码更灵活
	 * Synchronized不需要显示地获取和释放锁，简单
	 *
	 *使用Lock可以方便的实现公平性
	 *
	 * 非阻塞的获取锁   tryLock
	 * 能被中断的获取锁
	 * 超时获取锁	超时 就会返回了
	 *
	 */
	private int value;
	Lock lock = new ReentrantLock();
	Lock l1 = new ReentrantLock();
	
	/**
	 * @return
	 */
	public  int getNext() {
		lock.lock();
		int a = value ++;
		lock.unlock();
		return a;
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable( ) {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}

}
