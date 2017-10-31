package com.neu.liuxi.innerClass;

public class Parcell {

	class Contents{
		private int i=11;
		public int value(){
			return i;
		}
	}
	class Destination{
		private String label;
		Destination(String whereTo){
			label = whereTo;
		}
		String readLabel(){return label;}
	}
	public Destination to(String s){
		return new Destination(s);
	}
	public Contents contents(){
		return new Contents();
	}
	public void ship(String dest){
		Contents c = new Contents();
		Destination d = new Destination(dest);
		System.out.println(d.readLabel());
	}
	public static void main(String[] args){
		Parcell p = new Parcell();
		p.ship("Tasmania");
		Parcell q = new Parcell();
		Parcell.Contents c = q.contents();
		Parcell.Destination d = q.to("Borneo");
		
		Parcell p3 = new Parcell();
		Parcell.Contents c1= p.new Contents();
		Parcell.Destination d1 = p.new Destination("Tasmania");
	}
}
