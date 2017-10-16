package com.neu.liuxi;
public class Person {

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
        System.out.printf("Hi %s, I'm a static method", devName);
    }
    public void publicMethod() {
        System.out.println("I'm a public method");
    }
    private void privateMethod() {
        System.out.println("I'm a private method");
    }
}
