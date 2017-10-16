package com.neu.liuxi;

/**
 * 
 * <p>Title:类加载测试</p>
 * @author liuxi
 * @date 2017年10月16日
 */
public class ClassLoaderTest {
	public  void print(){
		System.out.println("Hello,I'm Hello");
	}

	public static void main(String[] args) throws ClassNotFoundException
	{
		 	Class<?> clazz = Class.forName("com.juvenxu.mvnbook.helloworld.HelloWorld");
		    ClassLoader classLoader = clazz.getClassLoader();
		    
		    System.out.printf("ClassLoader is %s", classLoader.getClass().getSimpleName());
		    while(classLoader.getParent()!=null){
		    	classLoader = classLoader.getParent();
		    	System.out.println("Parent is ="+classLoader.getClass().getSimpleName());
		    }
	}
}
