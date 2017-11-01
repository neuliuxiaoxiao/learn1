package com.neu.liuxi.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		//ExecutorService threadPool = Executors.newCachedThreadPool();
		for(int i =0;i<10;i++){
			final int task =1;
			threadPool.execute(new Runnable() {
				public void run() {
					for (int j = 1; j <= 5; j++) { 
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()
                                + " looping of " + j + " for task of " + task);
                    }
				}
			});
		}
		  System.out.println("all of 10 tasks have committed!");
	      threadPool.shutdown(); //执行完任务后关闭
	}
}
