package com.roocon.thread.ta3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;



/**
 * 读写锁：
 * 		读锁 共享锁
 * 		写锁 排它锁
 *
 *
 *
 * 	sync ReetrantLock 都是排他锁    在同一时刻， 只有一个线程能同时执行
 *    读写 互斥   写写互斥     写读互斥
 * 	共享锁， 多个线程可以同时进行
 */
public class Demo {

	private Map<String, Object> map = new HashMap<>();

	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	public Object get(String key) {
		r.lock();
		System.out.println(Thread.currentThread().getName() + " 读操作在执行..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return map.get(key);
		} finally {
			r.unlock();
			System.out.println(Thread.currentThread().getName() + " 读操执行完毕..");
		}
	}

	public void put(String key, Object value) {
		w.lock();
		System.out.println(Thread.currentThread().getName() + " 写操作在执行..");
		try {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			map.put(key, value);
		} finally {
			w.unlock();
			System.out.println(Thread.currentThread().getName() + " 写操作执行完毕..");
		}
	}

}
