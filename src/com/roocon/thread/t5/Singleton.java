package com.roocon.thread.t5;

public class Singleton {
	
	// ˽�л����췽��
	private Singleton () {}

	//����ʽ ����ģʽ�������ڷ��̰߳�ȫ����
	private static Singleton instance = new Singleton();
	
	public static Singleton getInstance() {
		return instance;
	}
	
	// ���̵߳Ļ�����
	// �����й�����Դ
	// ����Դ���з�ԭ���Բ���
	
	
}
