package com.neu.liuxi.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
//http://blog.csdn.net/eson_15/article/details/51553597
public class ThreadPool1 {
	 public static void main(String[] args) {
	       /* Executors.newScheduledThreadPool(3).schedule(new Runnable() {

	            @Override
	            public void run() {
	                System.out.println(Thread.currentThread().getName() + " bombing");
	            }
	        }, 2, TimeUnit.SECONDS);     */   
		 /*ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(3);
		 for(int i=0;i<5;i++){
			 threadPool.schedule(new Runnable() {
				public void run() {
					 try {
	                        Thread.sleep(1000);
	                    } catch (InterruptedException e) {
	                        // TODO Auto-generated catch block
	                        e.printStackTrace();
	                    }
	                    System.out.println(Thread.currentThread().getName() + " bombing");
				}
			}, 2, TimeUnit.SECONDS);
		 }*/
		  Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {

	            @Override
	            public void run() {
	                System.out.println(Thread.currentThread().getName() + " bombing");
	            }
	        }, 5, 2, TimeUnit.SECONDS);   
	 }
}
