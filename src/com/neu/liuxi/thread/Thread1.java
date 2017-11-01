package com.neu.liuxi.thread;
//http://blog.csdn.net/eson_15/article/details/51465316
public class Thread1 {

	public static void main(String[] args) {
		
	}
	public static void thread1(){
		Thread thread = new Thread(){
			public void run(){
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Thread"+Thread.currentThread().getName());
			}
		};
		thread.start();
	}
	public static void thread2(){
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Runnable"+Thread.currentThread().getName());
			}
		});
		thread.start();
	}
	public static void thread3(){
		new Thread(new Runnable() {
			public void run() {
				try {
					Thread.sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("Runnable"+Thread.currentThread().getName());
			}
		}){
			public void run() {
				try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread:" + Thread.currentThread().getName());
			}
		}.start();
	}
}
