package com.neu.liuxi.collection;

import java.util.HashMap;
import java.util.Map;

public  class LinkedListDemo<E> {
	  // 记录链表的长度  
	  transient int size = 0;
	  //链表开始结束节点
	  transient Node<E> first;
	  transient Node<E> last;
	  
	  public LinkedListDemo() {  
	        init();  
	  }  
	  //初始化一个只有开始节点和结束节点的空链表 
	  private void init() {  
	        // 将首位节点链接起来  
		  first = new Node<E>(null, null, null);  
		  last = new Node<E>(first,null , null);  
          first.next = last;  
          size = 0;  
	  }  
	  static class Node<E> {
	        E item;
	        Node<E> next;
	        Node<E> prev;
	        Node(Node<E> prev, E element, Node<E> next) {
	            this.item = element;
	            this.next = next;
	            this.prev = prev;
	        }
	   }
	  private void linkFirst(E e) {
	        final Node<E> f = first;
	        final Node<E> newNode = new Node<>(null, e, f);
	        first = newNode;
	        if (f == null)
	            last = newNode;
	        else
	            f.prev = newNode;
	        size++;
	    }
	  private void linkLast(E e) {
	        final Node<E> l = last;
	        final Node<E> newNode = new Node<>(l, e, null);
	        last = newNode;
	        if (l == null)
	            first = newNode;
	        else
	            l.next = newNode;
	        size++;
	    }
	  public int size() {
	        return size;
	  }
	  public static void main(String[] args) {
		  Map<String,Object> x = new HashMap<String,Object>();
		  x.put("A","A");
		  LinkedListDemo<Integer> xx = new LinkedListDemo();
		  xx.linkFirst(1);
	}
}
