package com.roocon.thread.t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子更新基本类型
 * 原子更新数组
 * 原子更新抽象类型
 * 原子更新字段
 */
public class Sequence {


	/**
	 * https://www.cnblogs.com/javalyy/p/8882172.html
	 * CAS    原子类 是怎么保证 安全的呢？ cas compare and swaw 乐观锁的实现方式
	 *
	 * 比较和交换（Conmpare And Swap）是用于实现多线程同步的原子指令
	 * cas保证了原子性， 多线程访问的时候， 因为 要比较  当前值 与 预期值的关系，
	 * 这个当前值是用volitale修饰的， 线程共享变量
	 * 当 有变化是， 所有线程都会知道， 所以保证了线程安全性！！！
	 *
	 *
	 *Unsafe中对CAS的实现是C++写的，
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * cas 虽然可以提高并发效率， 但是会引入ABA问题、
	 * 如线程1从内存X中取出A，这时候另一个线程2也从内存X中取出A，
	 * 并且线程2进行了一些操作将内存X中的值变成了B，
	 * 然后线程2又将内存X中的数据变成A，这时候线程1进行CAS操作发现内存X中仍然是A，
	 * 然后线程1操作成功。虽然线程1的CAS操作成功，但是整个过程就是有问题的。
	 * 比如链表的头在变化了两次后恢复了原值，但是不代表链表就没有变化
	 *
	 *
	 * 所以JAVA中提供了：
	 * 		AtomicStampedReference/AtomicMarkableReference来处理会发生ABA问题的场景，
	 * 		主要是在对象中额外再增加一个标记来标识对象是否有过变更。
	 */
	private AtomicInteger value  = new AtomicInteger(0);
	
	private int [] s = {2,1,4,6};
	
	AtomicIntegerArray a = new AtomicIntegerArray(s);
	
	
	AtomicReference<User> user = new AtomicReference<>();
	
	AtomicIntegerFieldUpdater<User> old =  AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
	
	/**
	 * @return
	 */
	public  int getNext() {
		
		User user = new User();
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));



		a.getAndIncrement(2);
		a.getAndAdd(2, 10);
		return value.getAndIncrement();
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
//				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//
	}

}
