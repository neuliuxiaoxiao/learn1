package com.neu.liuxi;

/**
 * 
 * <p>Title:传值与传引用</p>
 * @author liuxi
 * @date 2017年10月16日
 */
public class ReferencePkValue2 {
	public static void main(String[] args) { 
        ReferencePkValue2 t = new ReferencePkValue2(); 
        int a=99; 
        t.test1(a);
        System.out.println(a);
        MyObj obj=new MyObj(); 
        t.test2(obj);
        System.out.println(obj.b);
        t.test3(obj);
        System.out.println(obj.b);
    } 
    public void test1(int a){
    	a++;
    	System.out.println(a);} 
    public void test2(MyObj obj){ 
    	obj = new MyObj(); 
    	obj.b=100;
    	System.out.println(obj.b);}
    public void test3(MyObj obj){ 
        obj.b=100;
        System.out.println(obj.b);
    }
}
class MyObj{public int b=99;}
