package com.roocon.thread.t2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

//    Interger 就是返回类型
public class Demo4 implements Callable<Integer> {
	
	
	public static void main(String[] args) throws Exception {
		Demo4 d = new Demo4();
		
		FutureTask<Integer> task = new FutureTask<>(d);
		
		Thread t = new Thread(task);
		
		t.start();
		
		System.out.println("我先干点别的。。。");
		
		Integer result = task.get();
		System.out.println("线程执行的结果为：" + result);
	}

	@Override
	public Integer call() throws Exception {
		System.out.println("正在进行紧张的计算....");
		Thread.sleep(3000);
		return 1;
	}

}
