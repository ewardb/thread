package com.roocon.thread.t2;

import java.util.concurrent.*;

public class Demo6 {

	public static void main(String[] args)  throws Exception{

		/**
		 * �߳�ִ����� �Ὣ�̷߳Ż�   �ȴ���һ������
		 */
		ExecutorService executorService = Executors.newFixedThreadPool(10);

		/**
		 * �Ƚ����� �� ������  ���˻���
		 */
		ExecutorService threadPool = Executors.newCachedThreadPool();

		for (int i = 0; i < 10; i++) {
			threadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName());
				}
			});
		}

		threadPool.shutdown();


		/**
		 * �������Լ�����չ   �Ա���  submit �� execute������
		 */
		//https://www.cnblogs.com/wanqieddy/p/3853863.html
		ExecutorService executorService1 = Executors.newFixedThreadPool(5);
		Future<?> res = executorService1.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("dfdfd");
			}
		});
		Object o = res.get();
		System.out.println(o);



		//һ����  ʵ�� Callable
		ExecutorService executorService2 = Executors.newFixedThreadPool(2);
		Future<String> submit = executorService2.submit(new Callable<String>() {
			@Override
			public String call() {
				return "abc";
			}
		});
		System.out.println(submit.get());





		executorService.shutdown();
		executorService1.shutdown();
		executorService2.shutdown();
	}

}
