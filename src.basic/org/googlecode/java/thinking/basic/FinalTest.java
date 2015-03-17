package org.googlecode.java.thinking.basic;

import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalTest {
	
	private static final Logger logger = LoggerFactory.getLogger(FinalTest.class);
	
	@Test
	public void test() {
		FinalData fd1 = new FinalData("fd1");
		
		//! ft1.valueOne++;
		
		fd1.v2.i++;
		logger.info("{}", fd1.v2.i);
		fd1.v1 = new Value(9);
		logger.info("{}", fd1.v1.i);
		//! fd1.v2 = new Value(21);
		//! fd1.VAL_3 = new Value(34);
		//! fd1.a = new int[3];
		for (int i=0; i<fd1.a.length; i++) {
			fd1.a[i]++;
			logger.info("a.{}: {}", i, fd1.a[i]);
		}
		
		FinalData fd2 = new FinalData("fd2");
		logger.info(fd1.toString());	
		logger.info(fd2.toString());
		
		logger.info(new BlankFinal().toString());	
		logger.info(new BlankFinal(47).toString());
		
		FinalArguments fa = new FinalArguments();
		fa.with(null);
		logger.info("{}", fd1.v1.i);
		fa.without(fd1.v1);
		//****不会影响v1的引用？
		logger.info("{}", fd1.v1.i);
		
		FinalClass fc = new FinalClass();
		fc.f();
		fc.i = 40;
		//! fc.j++;
	}
}

class FinalData {
	private static Random rand = new Random(47);
	private String id;
	public FinalData(String id) { this.id = id; }
	
	private final int valueOne = 9;
	
	private static final int VALUE_TWO = 99;
	
	public static final int VALUE_THREE = 39;
	
	private final int i4 = rand.nextInt(20);
	static final int INT_5 = rand.nextInt(20);
	
	public Value v1 = new Value(11);
	public final Value v2 = new Value(22);
	
	public static final Value VAL_3 = new Value(33);
	
	public final int[] a = { 1, 2, 3, 4, 5};
	
	public String toString() {
		return id + ": i4=" + i4 + ", INT_5=" + INT_5;
	}
}

//p142，空白Final，j和v必须在域的定义处或每个构造器中使用表达式对final赋值，所以final域在使用前必须得初始化。
class BlankFinal {
	private final int i = 0;
	private final int j;
	private final Value v;
	
	public BlankFinal() {
		j=1;
		v=new Value(1);
	}
	
	public BlankFinal(int x) {
		j=x;
		v=new Value(x);
	}
	
	public String toString() {
		return ": j=" + j + ", Value=" + v.i;
	}
}
//final参数，加final后只能读取，不能修改
class FinalArguments {
	void with(final Value v) {
		//! v = new Value();
	}
	void without(Value v) {
		v=new Value();
		v.spin();
	}
	//! void f(final int i) { i++; }
	int g(final int i) { return i+1; }
}
//final类，不允许继承。只有其中的方法默认加final，变量仍然需要显示final or not
//! class Further extends FinalClass { }
final class FinalClass {
	int i = 7;
	final int j = 1;
	void f() { }
}


class Value {
	int i;
	public Value() { i=1; }
	public Value(int i) { this.i = i; }
	public void spin() { }
}
