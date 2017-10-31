package com.neu.liuxi.innerClass;

class Parcel {
	private class PContents implements Contents{
		private int i=11;
		public int value(){
			return i;
		}
	}
	protected class PDestination implements Destination{
		private String label;
		private PDestination(String whereTo){
			label = whereTo;
		}
		public String readLabel(){
			return label;
		}
	}
	public Destination destiantion(String s){
		return new PDestination(s);
	}
	public Contents contents(){
		return new PContents();
	}
}
public class TestParcel{
	public static void main(String[] args) {
		Parcel p = new Parcel();
		Contents c = p.contents();
		Destination d = p.destiantion("Tasmania");
		
	}

}
