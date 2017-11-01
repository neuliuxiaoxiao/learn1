package com.neu.liuxi.thread;
//http://blog.csdn.net/eson_15/article/details/51546302
public class MultiThreadShareData1 {
	public static void main(String[] args) {
		ShareData1 task = new ShareData1();
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					task.add();
				}
			}).start();
		}
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					task.weight();
				}
			}).start();
		}
	}
}

class ShareData1{
	private int data =0;
	public synchronized void add(){
		 System.out.println(Thread.currentThread().getName() + ": before : " + data);
        data++;
        System.out.println(Thread.currentThread().getName() + ": after : " + data);
	}
	public synchronized void weight(){
		 System.out.println(Thread.currentThread().getName() + ": before : " + data);
        data--;
        System.out.println(Thread.currentThread().getName() + ": after : " + data);
	}
}