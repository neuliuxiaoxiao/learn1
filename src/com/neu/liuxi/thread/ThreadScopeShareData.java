package com.neu.liuxi.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareData {
	 private static int data = 0;//公共的数据
	 //定义一个Map以键值对的方式存储每个线程和它对应的数据，即Thread：data
	 private static Map<Thread, Integer> threadData = Collections.synchronizedMap(new HashMap<Thread, Integer>());

	 public static void main(String[] args) {
		for(int i =1;i<3;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int  temp = new Random().nextInt();
					  System.out.println(Thread.currentThread().getName() + " has put a data: " + temp); //打印出来为了看效果
					  threadData.put(Thread.currentThread(), temp);
					  data = temp;
					  new TestA().getData();
	                  new TestB().getData();
				}
			}).start();
		}
	}
	 static class TestA {
	        public void getData() {
	          //  System.out.println("A get data from " + Thread.currentThread().getName() + ": " + data);//取出公共数据data
	        	  System.out.println("A get data from " + Thread.currentThread().getName() + ": " 
	                      + threadData.get(Thread.currentThread())); //取出各线程维护的那个副本
	        }
	    }

	    static class TestB {
	        public void getData() {
	           // System.out.println("B get data from " + Thread.currentThread().getName() + ": " + data);
	        	  System.out.println("B get data from " + Thread.currentThread().getName() + ": " 
	                      + threadData.get(Thread.currentThread())); //取出各线程维护的那个副本
	        }
	    }
}
