package com.roocon.thread.t3;

public class Sequence {
	
	private int value;

	/**
	 * sync  ���������ֽ��룬 �������
	 * sync ���������� ���ǻ����
	 * ����������JVMʵ�ֵģ�jvm�ܻ���synchronized����һЩ�Ż�������������������(���ֻ�)����������
	 * ������
	 * ������
	 */



	
	/**
	 * synchronized ������ͨ�����ϣ����������ǵ�ǰ���ʵ��
	 * @return
	 */
	public synchronized int getNext() {
		return value ++;
	}
	
	/**
	 * ���ξ�̬�������������ǵ�ǰ��Class�ֽ������
	 * Sequence.class
	 * @return
	 */
	public static synchronized int getPrevious() {
//		return value --;
		return 0;
	}
	
	public int xx () {
		
		// monitorenter
		/**
		 * ���δ���飬
		 *
		 * sync(this)
		 * sync(sequence.class)
		 * sync(obj)
		 */
		synchronized (Sequence.class) {
			
			if(value > 0) {
				return value;
			} else {
				return -1;
			}
			
		}
		// monitorexit
		
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
//		while(true) {
//			System.out.println(s.getNext());
//		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
	}
	/**
	 *
	 * ���̻߳���
	 * ����̹߳���һ����Դ
	 * ����Դ���з�ԭ�Ӳ���
	 *
	 */

}
