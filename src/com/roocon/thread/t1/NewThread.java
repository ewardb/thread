package com.roocon.thread.t1;

public class NewThread implements Runnable {

	@Override
	public synchronized void run() {
		while(true) {
			try {
				//�ȴ�״̬  ����ķ��� ����Thread�ķ���
				//����wait �� notify �ķ��� �����м�����
				//wait/notify�����ĵ��ñ��봦�ڸö��������Monitor���У�Ҳ�����ڵ�����Щ����ʱ������Ҫ��øö�����������������IllegalMonitorStateException�쳣��
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("�Զ�����߳�ִ����....");
		}
	}
	//  ��ʼ��״̬������״̬�� ���У���ʱ�ȴ�(sleeep)�� �ȴ��� ������ ֹͣ
	// ��ʱ�ȴ����Զ����ؼ���ִ��  ���ǵȴ�����Ҫ����
	//��������״̬��Ψһǰ���� �ڵȴ���ȡ��ȡͬ������ javaע��˵�ĺ����ף� ֻ�������������ʹ�߳̽�������״̬�� һ���ǵȴ�����sync�飬 ������ �ڵ���wait���������½���sync��򷽷���

	//

	/**
	 * 1 :
	 * 2:
	 * 3: Lock ���������ʵ�ֲ������߳̽�������״̬ Lock�ײ���õ���LockSupport��park()������ʹ�߳̽�����ǵȴ�״̬��
	 *	4����ǰ���̵߳�״̬ת��ͼ��֪��������wait()�������̻߳����WAITING(�ȴ�״̬)��������notify()�󣬲�û��������ִ�У����ǽ���ȴ���ȡ�����������С�
	 * https://blog.csdn.net/wthfeng/article/details/78762343
	 * @param args
	 */
	public static void main(String[] args) {
		
		NewThread n = new NewThread();

		Object o = new Object();

		// ��ʼ��״̬
		Thread thread = new Thread(n); // �����߳�,��ָ���߳�����

		//�߳̾���״̬
		thread.start(); // �����߳�
		
		while(true) {
			synchronized (n) {
				System.out.println("���߳�ִ����...");
				try {
					//��ʱ�ȴ�״̬
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				n.notifyAll();
			}
			
		}
		
	}




}
