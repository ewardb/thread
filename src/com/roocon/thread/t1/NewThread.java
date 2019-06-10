package com.roocon.thread.t1;

public class NewThread implements Runnable {

	@Override
	public synchronized void run() {
		while(true) {
			try {
				//等待状态  对象的方法 不是Thread的方法
				//调用wait 和 notify 的方法 必须有监视器
				//wait/notify方法的调用必须处在该对象的锁（Monitor）中，也即，在调用这些方法时首先需要获得该对象的锁。否则会爬出IllegalMonitorStateException异常。
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("自定义的线程执行了....");
		}
	}
	//  初始化状态，就绪状态， 运行，超时等待(sleeep)， 等待， 阻塞， 停止
	// 超时等待会自动返回继续执行  但是等待则需要唤醒
	//进入阻塞状态的唯一前提是 在等待获取获取同步锁， java注释说的很明白， 只有两种情况可以使线程进入阻塞状态， 一就是等待进入sync块， 二就是 在调动wait方法后重新进入sync块或方法。

	//

	/**
	 * 1 :
	 * 2:
	 * 3: Lock 类对于锁的实现不会令线程进入阻塞状态 Lock底层调用的是LockSupport。park()方法。使线程进入的是等待状态。
	 *	4、由前面线程的状态转化图可知，当调用wait()方法后，线程会进入WAITING(等待状态)，后续被notify()后，并没有立即被执行，而是进入等待获取锁的阻塞队列。
	 * https://blog.csdn.net/wthfeng/article/details/78762343
	 * @param args
	 */
	public static void main(String[] args) {
		
		NewThread n = new NewThread();

		Object o = new Object();

		// 初始化状态
		Thread thread = new Thread(n); // 创建线程,并指定线程任务

		//线程就绪状态
		thread.start(); // 启动线程
		
		while(true) {
			synchronized (n) {
				System.out.println("主线程执行了...");
				try {
					//超时等待状态
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				n.notifyAll();
			}
			
		}
		
	}




}
