package com.neu.liuxi;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * <p>Title:测试线程锁
 * wait()方法会立即释放当前锁，并进入等待状态，等待到相应的notify并重新获得锁过后才能继续执行；
 * notify()不会立刻立刻释放锁，必须要等notify()所在线程执行完synchronized块中的所有代码才会释放
 * </p>
 * @author liuxi
 * @date 2017年10月16日
 */
public class ThreadLock {
	public static void main(String[] args) {
		List<Object> list = new LinkedList<Object>();
	    Thread r = new Thread(new ReadList(list));
	    Thread w = new Thread(new WriteList(list));
	    r.start();
	    w.start();
	}
}
class ReadList implements Runnable{

    private List<Object> list;

    public ReadList(List<Object> list){ this.list = list; }

    public void run(){
        System.out.println("ReadList begin at "+System.currentTimeMillis());
        synchronized (list){
            try {
                Thread.sleep(1000);
                System.out.println("list.wait() begin at "+System.currentTimeMillis());
                list.wait();
                System.out.println("list.wait() end at "+System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ReadList end at "+System.currentTimeMillis());

    }
}

class WriteList implements Runnable{

    private List<Object> list;

    public WriteList(List<Object> list){ this.list = list; }

    public void run(){
        System.out.println("WriteList begin at "+System.currentTimeMillis());
        synchronized (list){
            System.out.println("get lock at "+System.currentTimeMillis());
            list.notify();
            System.out.println("list.notify() at "+System.currentTimeMillis());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("get out of block at "+System.currentTimeMillis());
        }
        System.out.println("WriteList end at "+System.currentTimeMillis());

    }
}