package org.googlecode.java.thinking.basic.innerclasses;

import org.googlecode.java.thinking.basic.util.Print;

public class InnerClassTest {
	
	static int a;
	int b;
	
	
	public static void main(String[] args) {
		InnerClassTest outerClass = new InnerClassTest();
		
		// ****必须通过外部类的对象new一个非静态的内部类  
		InnerClassTest.DyInnerClass inner = outerClass.new DyInnerClass();
		
		// 而Static inner class不需要
		InnerClassTest.StaticInnerClass staticInner = new InnerClassTest.StaticInnerClass();
		
		
	}
	
	public static void outerClassStaticFunction() {
		System.out.println("outer class static function");
	}
	
	private class DyInnerClass {
		// 不能有静态成员
		//static int staticVarible;
		int dynamicVarible;
		
		public dyInnerClass() {
			//都可以访问，意味着一个内部类对象，都有一个外部类对象的引用
			Print.print("visit outer class static varible:"+a);
			Print.print("visit outer class static varible:"+b);
			
			outerClassStaticFunction();
		}
	}
	
	private static class StaticInnerClass {
		// 静态内部类可以有静态成员，而非静态内部类则不能有静态成员。  
		static int staticVarible;
		int dynamicVarible;
		
		public StaticInnerClass() {
			Print.print("visit outer class static varible:"+a);
			 // 静态内部类不能够访问外部类的非静态成员 
			//Print.print("visit outer class static varible:"+b);
		}
		
		public void testVisiOuterStaticFunction() {
			//可以访问外部类的静态方法，不能访问实例方法
			outerClassStaticFunction();
		}
		
		public static void staticInnerClassStaticFunc() {
			Print.print("staticInnerClassStaticFunc");
		}
	}
	
}
