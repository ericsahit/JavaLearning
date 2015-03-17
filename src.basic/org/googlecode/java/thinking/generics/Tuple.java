package org.googlecode.java.thinking.generics;

import org.googlecode.java.thinking.basic.FinalTest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tuple {
	
	private static final Logger logger = LoggerFactory.getLogger(FinalTest.class);
	
	static TwoTuple<String, Integer> f() {
		return new TwoTuple<String, Integer>("hi", 47);
	}
	
	static ThreeTuple<Amphibian, String, Integer> g() {
		return new ThreeTuple<Amphibian, String, Integer>(new Amphibian(), "hi", 47);
	}
	
	@Test
	public void test() {
		//用泛型实现的元组
		logger.info("{}", f());
		logger.info("{}", g());
		
		
	}
}

class TwoTuple<A, B> {
	public final A first;
	public final B second;
	public TwoTuple(A a, B b) {
		first=a;
		second=b;
	}
	@Override
	public String toString() {
		return "contents:"+first+", "+second+"";
	}
}

class ThreeTuple<A, B, C> extends TwoTuple<A, B> {
	public final C third;
	public ThreeTuple(A a, B b, C c) {
		super(a, b);
		third=c;
	}
	public String toString() {
		return super.toString()+", "+third;
	}
}
class Amphibian { }
