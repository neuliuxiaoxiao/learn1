package com.neu.liuxi;

import java.lang.reflect.*;

public class Main {
    static void customer(ProxyInterface1 pi){
        pi.say();
    }
    public static void main(String[] args){
        RealObject1 real = new RealObject1();
        //当运行时（动态）才创建代理类
        ProxyInterface1 proxy = (ProxyInterface1)Proxy.newProxyInstance(real.getClass().getClassLoader(), real.getClass().getInterfaces(), new ProxyObject1(real));
        customer(proxy);
    }
}


interface ProxyInterface1{
    void say();
}

//被代理类
class RealObject1 implements ProxyInterface1{
    public void say(){
        System.out.println("i'm talking");
    }
}

//代理类，实现InvocationHandler 接口
class ProxyObject1 implements InvocationHandler{
    private Object proxied = null;
    public ProxyObject1(){
        
    }
    public ProxyObject1(Object proxied){
        this.proxied  = proxied;
    }
    public Object invoke(Object arg0, Method arg1, Object[] arg2) throws Throwable {
        System.out.println("hello");
        return arg1.invoke(proxied, arg2);
    };
}