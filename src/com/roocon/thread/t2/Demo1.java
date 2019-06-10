package com.roocon.thread.t2;



//创建线程的方式：
/**
 * extend thread
 * runnable
 * 匿名内部类的实现方式
 * 带返回值的线程
 * 定时器   TImer quartz
 * 线程池
 */



public class Demo1 extends Thread {
	
	public Demo1(String name) {
		super(name);
	}

//	public Demo1(ThreadGroup threadGroup, String name){
//		super(threadGroup, name);
//	}
//
	@Override
	public void run() {
		while(!interrupted()) {
			System.out.println(getName() + "线程执行了 .. ");
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {


//		ThreadGroup threadGroup = new ThreadGroup("first");
//		Demo1 demo1 = new Demo1(threadGroup, "zu-xiance");
//		demo1.start();

		Demo1 d1 = new Demo1("first-thread");
		Demo1 d2 = new Demo1("second-thread");

		/**
		 * eg l垃圾回收线程    支持性线程
		 * 主线程 over  这个 线程也over
		 */
//		d1.setDaemon(true);
//		d2.setDaemon(true);


		//模拟演示daemon
		d1.start();
		d2.start();
//		try{
//			Thread.sleep(100);
//		}catch (Exception e){}
		//当主线程ok了， 这两个线程也会over

		/**
		 * 线程中断 最好不要使用stop
		 * 而是使用interrupt， stop的中断方式不会释放被锁定的资源，
		 * interrpt后， 线程 使用interrupted 来判断线程是否中断。。。可以做一些处理
		 */
//		d1.stop();
		//https://www.cnblogs.com/skywang12345/p/3479949.html
		d1.interrupt();
	}
	
}
