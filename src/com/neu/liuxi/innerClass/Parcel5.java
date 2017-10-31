package com.neu.liuxi.innerClass;

public class Parcel5 {

	public Destination destination(String s){
		class PDestination implements Destination{
			private String label;
			private PDestination(String whereTo){
				label = whereTo;
			}
			public String readLabel(){
				return label;
			}
		}
		return new PDestination(s);
	}
	//在方法的作用域创建一个完整的内部类，局部内部类
	public static void main(String[] args) {
		Parcel5 p = new Parcel5();
		Destination d = p.destination("Tasmania");
	}
}
