package com.neu.liuxi.thread;
//http://blog.csdn.net/eson_15/article/details/51530778
public class TraditionalThreadCommunication {

	public static void main(String[] args) {
		 Business1 bussiness = new Business1(); //new一个线程任务处理类
	        //开启一个子线程
	        new Thread(new Runnable() {

	            @Override
	            public void run() {
	                for(int i = 1; i <= 50; i ++) {
	                    bussiness.sub(i);
	                }

	            }
	        }).start();

	        //main方法即主线程
	        for(int i = 1; i <= 50; i ++) {
	            bussiness.main(i);
	        }
	    }
}
class Business1 {
	private boolean bShouldSub = true;
    public synchronized void sub(int i) {
    	 while(!bShouldSub) { //如果不轮到自己执行，就睡
             try {
                 this.wait(); //调用wait()方法的对象必须和synchronized锁对象一致，这里synchronized在方法上，所以用this
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
        for(int j = 1;j <= 10; j ++) {
            System.out.println("sub thread sequence of " + j + ", loop of " + i);
        }   
        bShouldSub = false; //改变标记
        this.notify(); //唤醒正在等待的主线程
    }

    public synchronized void main(int i) {
    	while(bShouldSub) { //如果不轮到自己执行，就睡
            try {
                this.wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        for(int j = 1;j <= 5; j ++) {
            System.out.println("main thread sequence of " + j + ", loop of " + i);
        }
        bShouldSub = true; //改变标记
        this.notify(); //唤醒正在等待的子线程
    }
}
