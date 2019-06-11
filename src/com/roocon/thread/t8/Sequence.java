package com.roocon.thread.t8;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ԭ�Ӹ��»�������
 * ԭ�Ӹ�������
 * ԭ�Ӹ��³�������
 * ԭ�Ӹ����ֶ�
 */
public class Sequence {


	/**
	 * https://www.cnblogs.com/javalyy/p/8882172.html
	 * CAS    ԭ���� ����ô��֤ ��ȫ���أ� cas compare and swaw �ֹ�����ʵ�ַ�ʽ
	 *
	 * �ȽϺͽ�����Conmpare And Swap��������ʵ�ֶ��߳�ͬ����ԭ��ָ��
	 * cas��֤��ԭ���ԣ� ���̷߳��ʵ�ʱ�� ��Ϊ Ҫ�Ƚ�  ��ǰֵ �� Ԥ��ֵ�Ĺ�ϵ��
	 * �����ǰֵ����volitale���εģ� �̹߳������
	 * �� �б仯�ǣ� �����̶߳���֪���� ���Ա�֤���̰߳�ȫ�ԣ�����
	 *
	 *
	 *Unsafe�ж�CAS��ʵ����C++д�ģ�
	 *
	 *
	 *
	 *
	 *
	 *
	 *
	 * cas ��Ȼ������߲���Ч�ʣ� ���ǻ�����ABA���⡢
	 * ���߳�1���ڴ�X��ȡ��A����ʱ����һ���߳�2Ҳ���ڴ�X��ȡ��A��
	 * �����߳�2������һЩ�������ڴ�X�е�ֵ�����B��
	 * Ȼ���߳�2�ֽ��ڴ�X�е����ݱ��A����ʱ���߳�1����CAS���������ڴ�X����Ȼ��A��
	 * Ȼ���߳�1�����ɹ�����Ȼ�߳�1��CAS�����ɹ��������������̾���������ġ�
	 * ���������ͷ�ڱ仯�����κ�ָ���ԭֵ�����ǲ����������û�б仯
	 *
	 *
	 * ����JAVA���ṩ�ˣ�
	 * 		AtomicStampedReference/AtomicMarkableReference������ᷢ��ABA����ĳ�����
	 * 		��Ҫ���ڶ����ж���������һ���������ʶ�����Ƿ��й������
	 */
	private AtomicInteger value  = new AtomicInteger(0);
	
	private int [] s = {2,1,4,6};
	
	AtomicIntegerArray a = new AtomicIntegerArray(s);
	
	
	AtomicReference<User> user = new AtomicReference<>();
	
	AtomicIntegerFieldUpdater<User> old =  AtomicIntegerFieldUpdater.newUpdater(User.class, "old");
	
	/**
	 * @return
	 */
	public  int getNext() {
		
		User user = new User();
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));
		System.out.println(old.getAndIncrement(user));



		a.getAndIncrement(2);
		a.getAndAdd(2, 10);
		return value.getAndIncrement();
	}
	
	public static void main(String[] args) {
		
		Sequence s = new Sequence();
		
		new Thread(new Runnable() {

			@Override
			public void run() {
//				while(true) {
					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
//				}
			}
		}).start();
		
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				while(true) {
//					System.out.println(Thread.currentThread().getName() + " " + s.getNext());
//					try {
//						Thread.sleep(100);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//				}
//			}
//		}).start();
//
	}

}
