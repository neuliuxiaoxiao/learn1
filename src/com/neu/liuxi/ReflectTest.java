package com.neu.liuxi;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectTest {

	public static void main(String[] args) throws Exception,NoSuchFieldException, SecurityException {
		//第一种方法
		try {
			Class<?> person = Class.forName("com.neu.liuxi.Person");
			System.out.println(person);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//第二种
		Class<?> person2 = Person.class;
		System.out.println(person2);
		//第三种方法
        Person person = new Person(null, 0);  
        Class<?> class3 = person.getClass();
        System.out.println(class3);
        
        //获取所有变量
        Field[] fields = person2.getDeclaredFields();
        System.out.println("-------"+person2.getField("name"));
        //Field[] fields = person2.getFields();
        for(Field field:fields){
        	System.out.println(Modifier.toString(field.getModifiers())+" "+field.getType().getSimpleName()+" "+field.getName());
        }
        //获取所有方法
        Method[] methods = person2.getDeclaredMethods();
        for (Method method : methods) {
        	System.out.println("Method="+Modifier.toString(method.getModifiers())+" "+method.getReturnType().getSimpleName()+" "+method.getName());
        }
        
        //构造函数
		Constructor<?>[] constructors = person2.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
        	System.out.println("Constructor="+Modifier.toString(constructor.getModifiers())+" "+constructor.getName());
        }
        
        //使用invoke调用方法
        person2.getDeclaredMethod("staticMethod",String.class).invoke(null,"jack");
        Method method = person2.getDeclaredMethod("privateMethod");
        //AccessibleObject 类是 Field、Method 和 Constructor 对象的基类。
        //它提供了将反射的对象标记为在使用时取消默认 Java 语言访问控制检查的能力。
        method.setAccessible(true);
        method.invoke(new Person());
	}
}
