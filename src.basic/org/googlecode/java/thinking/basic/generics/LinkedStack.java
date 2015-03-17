package org.googlecode.java.thinking.basic.generics;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LinkedStack {
	
	@Test
	public void test() {
		Stack<String> lss=new Stack<String>();
		for (String s:"Phasers on stun!".split(" "))
			lss.push(s);
		String s;
		while ((s=lss.pop())!=null)
			Print.print(s);
	}
}

class Stack<T> {
	private static class Node<U> {
		U item;
		Node<U> next;
		Node() { item = null; next = null; }
		Node(U item, Node<U> next) {
			this.item=item;
			this.next=next;
		}
		boolean end() { return item==null && next==null; }
	}
	//可以去掉Node的类型参数，内部类可以访问其外部类的类型参数。不过需要去掉static关键字
	private class Node2 {
		T item;
		Node2 next;
		Node2() { item = null; next = null; }
		Node2(T item, Node2 next) {
			this.item=item;
			this.next=next;
		}
		boolean end() { return item==null && next==null; }
	}
	
	private Node<T> top=new Node<T>();
	public void push(T item) {
		top=new Node<T>(item, top);
	}
	public T pop() {
		T result=top.item;
		if (!top.end())
			top=top.next;
		return result;
	}
}
