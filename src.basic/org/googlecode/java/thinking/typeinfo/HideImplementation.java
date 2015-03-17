package org.googlecode.java.thinking.typeinfo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class HideImplementation {
	
	/**
	 * P347通过使用: 
	 * 1.包使用权限
	 * 2.私有内部类
	 * 3.匿名类
	 * 隐藏类型信息，禁止客户端向下转型
	 * 但是通过反射可以调用实际类型的所有方法，包括private方法！
	 */
	static void callHiddenMethod(Object o, String methodName) throws Exception {
		Method method=o.getClass().getDeclaredMethod(methodName);
		method.setAccessible(true);
		method.invoke(o);
	}
	
	@Test
	public void testModifyPrivateFields() throws Exception {
		WithPrivateFinalField pf=new WithPrivateFinalField();
		Print.print(pf);
		//可以修改private字段
		Field f=pf.getClass().getDeclaredField("i");
		f.setAccessible(true);
		f.setInt(pf, 47);
		//final域在遭遇修改时是安全的。运行时系统会在不抛出异常的情况下接受任何修改尝试，但是实际上不会发生任何修改。
		f=pf.getClass().getDeclaredField("s");
		f.setAccessible(true);
		f.set(pf, "No, you're not!");
		f=pf.getClass().getDeclaredField("s2");
		f.setAccessible(true);
		f.set(pf, "No, you're not!");
		Print.print(pf);
		//调用private方法
		Method method=pf.getClass().getDeclaredMethod("privateMethod");
		method.setAccessible(true);
		method.invoke(pf);
	}
}

class WithPrivateFinalField {
	private int i=1;
	private final String s="I'm totally safe";
	private String s2="Am i safe?";
	public String toString() {
		return "i="+i+", "+s+", "+s2;
	}
	private void privateMethod() {
		Print.print("call private method!");
	}
}



