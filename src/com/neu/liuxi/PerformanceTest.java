package com.neu.liuxi;

import java.lang.reflect.Method;
//对比静态调用与类反射调用方法的效率
//上述方法有缓存方法 
public class PerformanceTest {

    public static void main(String[] args) throws Exception {     
        int testTime = 10000000;     
        PerformanceTest test = new PerformanceTest();     
        String msg = "this is test message";     
        long bTime = System.currentTimeMillis();     
        for(int i=0; i<testTime; i++) {     
            test.takeAction(msg);     
        }     
        long eTime = System.currentTimeMillis();     
        System.out.println(eTime - bTime);     
             
        Method method = test.getClass().getMethod("takeAction", String.class);     
             
        bTime = System.currentTimeMillis();     
        for(int i=0; i<testTime; i++) {     
            method.invoke(test, msg);     
        }     
        eTime = System.currentTimeMillis();     
        System.out.println(eTime - bTime);     
             
    
    }     
         
    public int takeAction(String msg) {     
        return (msg.length() * (int)(System.currentTimeMillis() % 100000));     
    }
   /* public static void main(String[] args) throws Exception {     
        int testTime = 10000000;     
        PerformanceTest test = new PerformanceTest();     
        String msg = "this is test message";     
        long bTime = System.currentTimeMillis();     
        for(int i=0; i<testTime; i++) {     
            test.takeAction(msg);     
        }     
        long eTime = System.currentTimeMillis();     
        System.out.println(eTime - bTime);     
             
        bTime = System.currentTimeMillis();     
        for(int i=0; i<testTime; i++) {     
            Method method = test.getClass().getMethod("takeAction", String.class);     
            method.invoke(test, msg);     
        }     
        eTime = System.currentTimeMillis();     
        System.out.println(eTime - bTime);     
             
    
    }    */ 
    
}
