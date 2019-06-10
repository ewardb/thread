package com.roocon.thread.t2;



//�����̵߳ķ�ʽ��
/**
 * extend thread
 * runnable
 * �����ڲ����ʵ�ַ�ʽ
 * ������ֵ���߳�
 * ��ʱ��   TImer quartz
 * �̳߳�
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
			System.out.println(getName() + "�߳�ִ���� .. ");
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
		 * eg l���������߳�    ֧�����߳�
		 * ���߳� over  ��� �߳�Ҳover
		 */
//		d1.setDaemon(true);
//		d2.setDaemon(true);


		//ģ����ʾdaemon
		d1.start();
		d2.start();
//		try{
//			Thread.sleep(100);
//		}catch (Exception e){}
		//�����߳�ok�ˣ� �������߳�Ҳ��over

		/**
		 * �߳��ж� ��ò�Ҫʹ��stop
		 * ����ʹ��interrupt�� stop���жϷ�ʽ�����ͷű���������Դ��
		 * interrpt�� �߳� ʹ��interrupted ���ж��߳��Ƿ��жϡ�����������һЩ����
		 */
//		d1.stop();
		//https://www.cnblogs.com/skywang12345/p/3479949.html
		d1.interrupt();
	}
	
}
