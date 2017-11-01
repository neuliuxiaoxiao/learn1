package com.neu.liuxi.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
//http://blog.csdn.net/eson_15/article/details/51523842
public class TraditionalTimer {
	static  int count = 0; //记录爆炸的次数
	public static void main(String[] args) {
		
		//testTimer();
		//testMyTimer();
		testTwoTimer();
	}
	/*void	schedule(TimerTask task, long delay)	安排在指定延迟后执行指定的任务。
	void	schedule(TimerTask task, long delay, long period)	安排指定的任务从指定的延迟后开始进行重复的固定延迟执行。
	void	schedule(TimerTask task, Date time)	安排在指定的时间执行指定的任务
	void	schedule(TimerTask task, Date firstTime, long period)	安排指定的任务在指定的时间开始进行重复的固定延迟执行。
	*/
	
	public static void testTimer(){
		new Timer().schedule(new TimerTask() {
			public void run() {
				   System.out.println("--boom--");//爆炸
			}
		}, 2000,3000);
		 while(true) {
	            System.out.println(new Date().getSeconds());
	            try {
	                Thread.sleep(1000);
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	public static void testMyTimer(){
		 new Timer().schedule(new MyTimerTask(), 2000);//外面开启定时器
		 while(true) {//打印秒钟，一秒输出一次
	            System.out.println(new Date().getSeconds());
	            try {
	                Thread.sleep(1000);
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	public static void testTwoTimer(){
		 new Timer().schedule(new MyTimerTaskB(), 4000);//A和B随便开一个

	        while(true) {//打印秒钟，一秒输出一次
	            System.out.println(new Date().getSeconds());
	            try {
	                Thread.sleep(1000);
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	        }
	}
	
}
//自定义一个定时器任务
class MyTimerTask extends TimerTask {           
  /*public void run() {
      System.out.println("--boom--");
      //任务执行完再装一个定时器，扔进自定义的定时器任务
      new Timer().schedule(new MyTimerTask(), 3000);
  }*/
  public void run() {
	 
	  TraditionalTimer.count = (TraditionalTimer.count + 1) % 2; //结果只有0和1
      System.out.println("--boom--");
      new Timer().schedule(new MyTimerTask(), 2000+2000*TraditionalTimer.count);//根据count结果设定新的定时时间
  }
}
//自定义两个定时器任务类，继承TimerTask即可
class MyTimerTaskA extends TimerTask {

  @Override
  public void run() {
      System.out.println("--boomA--");
      new Timer().schedule(new MyTimerTaskB(), 4000);
  }           
}
class MyTimerTaskB extends TimerTask {

  @Override
  public void run() {
      System.out.println("--boomB--");
      new Timer().schedule(new MyTimerTaskA(), 2000);
  }   
}
