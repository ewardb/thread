package com.roocon.thread.t5;

public class Singleton {
	
	// 私有化构造方法
	private Singleton () {}

	//饿汉式 单例模式。不存在非线程安全问题
	private static Singleton instance = new Singleton();
	
	public static Singleton getInstance() {
		return instance;
	}
	
	// 多线程的环境下
	// 必须有共享资源
	// 对资源进行非原子性操作
	
	
}
