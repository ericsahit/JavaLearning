package org.googlecode.java.thinking.basic;

import org.googlecode.java.thinking.basic.Enum.Spiciness;
import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ClassInitializationTest {
	private static final Logger logger = LoggerFactory.getLogger(ClassInitializationTest.class);
	
	private static int x1=foo();
	
	@Test
	public void test() {
		/*
		 * 类文件加载：实体的创建或者对于static成员的访问都可能引起加载，加载时候初始化静态变量
		 * 1.加载：继承的初始化顺序，(跟)基类静态变量-->子类的静态变量
		 *   导出类的static初始化可能会依赖于基类成员能否被正确初始化
		 * 2.对象的数据成员的自动(指定)初始化：会被设置为指定表达式或默认值(没有指定值0和null)。
		 * 3.基类构造器会被调用(相同的过程：设默认值，基类构造器，实例变量，其余部分)：
		 * 	 Insect.i initializedInsect 
		 *   Constructor:i=9,j=0
		 * 4.实例变量按其次序被初始化：Beetle.k initialized
		 * 5.构造器的其余部分被执行(构造器初始化等操作)：Beetle Constructor:k=47
		 *   构造器初始化无法阻止自动初始化的运行
		 * 2.父类Constructor(优先变量初始化)-->子类Constructor(优先变量初始化)
		*/
		Print.print("new beetle begin");
		Beetle b = new Beetle();
	}
	
	static int foo() {
		Print.print("static i initialized");
		return 24;
	}
}

class Insect {
	private int i = printInit("Insect.i initialized");
	protected int j;
	Insect() {
		Print.print("Insect Constructor:i="+i+",j="+j);
		j=39;
	}
	private static int x1=printInit("static Insect.x1 initialized");
	static int printInit(String s) {
		Print.print(s);
		return 47;
	}
	void foo() {
		Print.print("foo");
	}
}

class Beetle extends Insect {
	private int k = printInit("Beetle.k initialized");
	public Beetle() {
		Print.print("Beetle Constructor:k="+k);
		Print.print("j="+j);
	}
	private static int x2=printInit("static Beetle.x2 initialized");
}
