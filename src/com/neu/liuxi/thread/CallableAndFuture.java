package com.neu.liuxi.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
//http://blog.csdn.net/eson_15/article/details/51553606
public class CallableAndFuture {

	/*public static void main(String[] args) {
		ExecutorService threadPool = Executors.newSingleThreadExecutor();
		Future<String> future = threadPool.submit(new Callable<String>(){
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}
		});
		 System.out.println("等待结果：");
	        try {
	            System.out.println("拿到结果：" + future.get());
	        } catch (Exception e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	}*/
	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool);
		for(int i=1;i<=5;i++){
			final int seq =1;
			completionService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					   Thread.sleep(new Random().nextInt(5000));
                       return seq;
				}
				
			});
		}
		for(int i = 0; i < 5; i ++) { //执行完了后，再取出来
            try {
                System.out.print(completionService.take().get() + " ");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
	}
}
