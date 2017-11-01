package com.neu.liuxi.thread;
//http://blog.csdn.net/eson_15/article/details/51527606
public class DeadLock {

    public static void main(String[] args) {
        Business business = new Business();
        //开启一个线程执行Business类中的functionA方法
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    business.functionA();
                }
            }
        }).start();

        //开启另一个线程执行Business类中的functionB方法
        new Thread(new Runnable() {

            @Override
            public void run() {
                while(true) {
                    business.functionB();
                }
            }
        }).start();
    }

}

class Business { //定义两个锁，两个方法
    //定义两个锁
    public static final Object lock_a = new Object();
    public static final Object lock_b = new Object();   

    public void functionA() {
        synchronized(lock_a) {
            System.out.println("---ThreadA---lock_a---");
            synchronized(lock_b) {
                System.out.println("---ThreadA---lock_b---");
            }
        }
    }

    public void functionB() {
        synchronized(lock_b) {
            System.out.println("---ThreadB---lock_b---");
            synchronized(lock_a) {
                System.out.println("---ThreadB---lock_a---");
            }
        }
    }
}