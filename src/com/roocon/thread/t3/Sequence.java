package com.roocon.thread.t3;

public class Sequence {
	
	private int value;

	/**
	 * sync  锁对象，锁字节码， 锁代码块
	 * sync 是内置锁， 他是互斥的
	 * 内置锁是由JVM实现的，jvm能基于synchronized锁做一些优化，比如增加锁的粒度(锁粗化)、锁消除。
	 * 内置锁
	 * 互斥锁
	 */



	
	/**
	 * synchronized 放在普通方法上，内置锁就是当前类的实例
	 * @return
	 */
	public synchronized int getNext() {
		return value ++;
	}
	
	/**
	 * 修饰静态方法，内置锁是当前的Class字节码对象
	 * Sequence.class
	 * @return
	 */
	public static synchronized int getPrevious() {
//		return value --;
		return 0;
	}
	
	public int xx () {
		
		// monitorenter
		/**
		 * 修饰代码块，
		 *
		 * sync(this)
		 * sync(sequence.class)
		 * sync(obj)
		 */
		synchronized (Sequence.class) {
			
			if(value > 0) {
				return value;
			} else {
				return -1;
			}
			
		}
		// monitorexit
		
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
//		while(true) {
//			System.out.println(s.getNext());
//		}
		
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
	/**
	 *
	 * 多线程环境
	 * 多个线程共享一个资源
	 * 对资源进行非原子操作
	 *
	 */

}
