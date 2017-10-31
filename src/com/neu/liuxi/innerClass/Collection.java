package com.neu.liuxi.innerClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Collection {

	public static void main(String[] args) {
		List<Integer> a = new ArrayList<Integer>();
		a.add(1);
		a.add(2);
		a.add(3);
		Iterator<Integer> xx = a.iterator();
		while(xx.hasNext()){
			Integer x = xx.next();
			System.out.println(x);
		}
		Collections.addAll(c, elements)
	/*	List<Integer> b = a.subList(0, 1);
		b.remove(0);
		System.out.println(a);*/
	}
}
