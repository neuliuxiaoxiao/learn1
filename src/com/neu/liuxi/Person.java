package com.neu.liuxi;

import java.io.Serializable;

public class Person implements Serializable{
	private static final long serialVersionUID = 1L;
	public String name;
	private int id;
	public Person(){
		
	}
	public  Person(String name,int id){
		this.name=name;
		this.id=id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
    public static void staticMethod(String devName) {
        System.out.printf("Hi %s, I'm a static method\n", devName);
    }
    public void publicMethod() {
        System.out.println("I'm a public method");
    }
    @SuppressWarnings("unused")
	private void privateMethod() {
        System.out.println("I'm a private method");
    }
}
