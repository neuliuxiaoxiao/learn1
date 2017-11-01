package com.neu.liuxi.thread;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ThreadScopeShareData2 {

	 //定义一个ThreadLocal
    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for(int i = 0; i < 2; i ++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName() + " has put a data: " + data);
                  //这里直接用User去调用getThreadLocal这个静态方法获取本线程范围内的一个User对象
                    //这里就优雅多了，我完全不用关心如何去拿该线程中的对象，如何把对象放到threadLocal中
                    //我只要拿就行，而且拿出来的肯定就是当前线程中的对象，原因看下面User类中的设计
                    User.getThreadInstance().setName("name" + data);
                    User.getThreadInstance().setAge(data);
                    new TestA().getData();
                    new TestB().getData();
                }
            }).start();
        }
    }

    static class TestA {
        public void getData() {
        	  User user = User.getThreadInstance();
              System.out.println("A get data from " + Thread.currentThread().getName() + ": " 
                      + user.getName() + "," + user.getAge());}
    }

    static class TestB {
        public void getData() {
        	  User user = User.getThreadInstance();
              System.out.println("B get data from " + Thread.currentThread().getName() + ": " 
                      + user.getName() + "," + user.getAge());}
    }

}
class User {

    private User() {}

    private static ThreadLocal<User> threadLocal = new ThreadLocal<User>();

    //注意，这不是单例，每个线程都可以new，所以不用synchronized，
    //但是每个threadLocal中是单例的，因为有了的话就不会再new了
    public static /*synchronized*/ User getThreadInstance() {
        User instance = threadLocal.get(); //先从当前threadLocal中拿
        if(instance == null) {
            instance = new User();
            threadLocal.set(instance);//如果没有就新new一个放到threadLocal中
        }
        return instance; //向外返回该User
    }

    private String name;
    private int age;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
}
