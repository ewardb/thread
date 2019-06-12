package com.roocon.thread.ta5;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.imageio.spi.IIOServiceProvider;



public class Demo {

	private Map<String, Object> map = new HashMap<>();

//	不支持锁升级。。。。。。。。。。。。。。。
	//锁升级： 读锁升级为写锁。获取写锁，在释放读锁   因为在读锁没有释放时，是拿不到写锁的
	private ReadWriteLock rwl = new ReentrantReadWriteLock();

	private Lock r = rwl.readLock();
	private Lock w = rwl.writeLock();

	private volatile boolean isUpdate;

	public void readWrite() {
		r.lock(); // 为了保证isUpdate能够拿到最新的值
		if (isUpdate) {
			r.unlock();
			w.lock();
			map.put("xxx", "xxx");
			r.lock();
			//体会这里  ！！！！！锁降级 ！！！！
			// 写降为读 在写锁没有释放时，获取读锁，然后释放写锁
			w.unlock();
		}

		Object obj = map.get("xxx");

		System.out.println(obj);
		r.unlock();

	}

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


/**
 * 认识的“*锁”
 * 偏向锁
 * 轻量级锁
 * 重量级锁
 * 重入锁
 * 自旋锁
 * 共享锁
 * 独占锁
 * 排他锁
 * 读写锁
 * 公平锁
 * 非公平锁
 * 死锁
 * 活锁
 */
