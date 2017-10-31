package com.neu.liuxi.innerClass;

public class DotThis {

	void f(){
		System.out.println("DotThis.f()");
	}
	public class Inner{
		//如果你需要生成对外部类对象的引用，可以使用外部类的名字后面紧跟圆点和this
		public DotThis outer(){
			return DotThis.this;
		}
	}
	public Inner inner(){
		return new Inner();
	}
	public static void main(String[] args) {
		DotThis dt = new DotThis();
		DotThis.Inner dti = dt.inner();
		dti.outer().f();
	}
}
