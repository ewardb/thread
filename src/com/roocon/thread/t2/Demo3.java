package com.roocon.thread.t2;

public class Demo3 {
	
	public static void main(String[] args) {

		/**
		 * 匿名内部类  看classA 和 interfaceB demo
		 */
		new Thread() {
			public void run() {
				System.out.println("thread start ..");
			};
		}.start();


		new Thread(){

		};
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("thread start ..");
			}
		}).start();
		
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("runnable");
			}
		}) {
			public void run() {
				System.out.println("sub") ;
			};
		}.start();


//		new Thread(() -> {}).start();
		
	}

}
