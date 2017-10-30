package com.neu.liuxi;

/**
 * 
 * <p>Title:类加载测试</p>
 * @author liuxi
 * @date 2017年10月16日
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException
	{
		 	Class<?> clazz = Class.forName("com.neu.liuxi.Person");
		    ClassLoader classLoader = clazz.getClassLoader();
		    System.out.println("ClassLoader is "+classLoader.getClass().getSimpleName());
		    while(classLoader.getParent()!=null){
		    	classLoader = classLoader.getParent();
		    	System.out.println("Parent ClassLoader is "+classLoader.getClass().getSimpleName());
		    }
	}
}
