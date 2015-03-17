package org.googlecode.java.thinking.basic.innerclasses;
import static org.googlecode.java.thinking.basic.util.Print.*;

abstract class Base {
	public Base(int i) {
		info("Base int constructor, i = " + i);
	}
	public Base() {
		info("Base default constructor");
	}
	
	
	public abstract void f();
}

public class AnonymousConstructor {
	public static Base getBase(int i) { //i不要求是final，因为没有在匿名类内部中直接被使用；如果用到，就一定加final
		return new Base(i) { //内部匿名类，继承自Base
			{ info("Inside instance initializer"); } //类似于构造器的效果，每一次初始化类都会调用
			public void f() {
				info("In anonymous f()");
			}
		};
	}
	
	public static Base getBase2(final String dest) { //如果用到，就一定加final
		return new Base() {
			private String label = dest;
			@SuppressWarnings("unused")
			public String readLabel() { return label; }
			public void f() {
				info("In anonymous f() with parameter, label: " + label);
			}
		};
	}
	
	public static Base getBase3(final String dest, final float price) {
		return new Base() {
			private int cost;
			{
				cost = Math.round(price);
				if (cost > 100)
					info("Over budget!");
			}
			public void f() {
				info("In anonymous f() with parameter, cost: " + cost);
			}
		};
	}
	
	public void test() {
		Base base = getBase(47);
		base.f();
		
		base = getBase2("Tasmania");
		base.f();
		
		getBase3("Tasmania", 101.395f).f();
	}
}
