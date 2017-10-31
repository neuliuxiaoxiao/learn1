package com.neu.liuxi.innerClass;

public class DotNew {

	public class Inner{}
	public static void main(String[] args) {
		DotNew dn = new DotNew();
		//如果你可能想要告知某些其他对象，去创建其某个内部类的对象，在new表达式中提供对其他外部类对象的引用。
		DotNew.Inner dni = dn.new Inner();
	}
}
