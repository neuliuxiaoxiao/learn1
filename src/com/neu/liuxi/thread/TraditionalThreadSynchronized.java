package com.neu.liuxi.thread;

/*同步代码块的锁是任意对象。只要不同的线程都执行同一个同步代码块的时候，这个锁随便设。
同步函数的锁是固定的this。当需要和同步函数中的逻辑实行同步的时候，代码块中的锁必须为this。
静态同步函数的锁是该函数所属类的字节码文件对象。该对象可以用this.getClass()方法获取，也可以使用 当前类名.class 表示。

*/
//http://blog.csdn.net/eson_15/article/details/51525105
public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		 new TraditionalThreadSynchronized().init();
	}
	private void init(){
		  final Outputer outputer = new Outputer();
		  new Thread(new Runnable() {
			public void run() {
				 while(true) {
	                    try {
	                        Thread.sleep(5);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    outputer.output1("duoxiancheng");
	                }
			}
		}).start();
		  new Thread(new Runnable() {
			public void run() {
				 while(true) {
	                    try {
	                        Thread.sleep(5);
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                    outputer.output3("liuxi");
	                }
			}
		}).start();
	}
    static class Outputer {
    	private String token = ""; //定义一个锁 output1
        //自定义一个字符串打印方法，一个个字符的打印
        public void output1(String name) {
        	synchronized(Outputer.class){//任何一个对象都可以作为参数，但是该对象对于两个线程来说是同一个才行
            int len = name.length();
            for(int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");}     
        }      
        public synchronized void output2(String name) {

            int len = name.length();
            for(int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");     
        }   
        public static synchronized void output3(String name) {

            int len = name.length();
            for(int i = 0; i < len; i++) {
                System.out.print(name.charAt(i));
            }
            System.out.println("");     
            }   
    }
}
