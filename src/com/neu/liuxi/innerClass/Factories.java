package com.neu.liuxi.innerClass;

interface Service{
	void method1();
	void method2();
}
interface ServiceFactory{
	Service getService();
}
class Implementationl implements Service{
	private Implementationl(){}
	public void method1() {
		System.out.println("Implementationl method1");
	}
	public void method2() {
		System.out.println("Implementationl method2");
	}
	public static ServiceFactory factory = new ServiceFactory(){
		public Service getService(){
			return new Implementationl();
		}
	};
}
class Implementation2 implements Service{
	private Implementation2(){}
	public void method1() {
		System.out.println("Implementation2 method1");
	}
	public void method2() {
		System.out.println("Implementation2 method2");
	}
	public static ServiceFactory factory = new ServiceFactory(){
		public Service getService(){
			return new Implementation2();
		}
	};
}
public class Factories {
	public static void serviceConsumer(ServiceFactory fact){
		Service s = fact.getService();
		s.method1();
		s.method2();
	}
	public static void main(String[] args) {
		serviceConsumer(Implementationl.factory);
		serviceConsumer(Implementation2.factory);
	}
}
