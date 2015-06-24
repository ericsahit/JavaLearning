package me.ericsahit.java.execise;

import java.lang.reflect.Field;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class JavaExercise {
	
	@Test
	public void test() throws Exception {
		String str = "abc";
		Print.print(str);
		String str1 = "abc";
		//在创建一个String对象时，其实会创建两个对象，一个堆内存的String对象，一个常量池的value数组对象。value数组对象可能已存在。
		String str2 = new String("abc");
		
		//str==str1，因为都在内存常量池中
		Assert.assertTrue(str == str1);
		//str!=str2，因为str2是在堆内存中分配
		Assert.assertFalse(str == str2);
		
		Field abcField = String.class.getDeclaredField("value");
		abcField.setAccessible(true);//通过反射，得到String内部的char[]
		char[] value = (char[])abcField.get(str);
		char[] value1 = (char[])abcField.get(str1);
		char[] value2 = (char[])abcField.get(str2);
		
		//数组的内容都一样，都是一个对象，在内存常量池中
		Assert.assertTrue(value == value1);
		Assert.assertTrue(value == value2);
		
		//当改动了value数组的时候，三个String对象的内容都会改动
		value[0] = 'b';
		Assert.assertTrue(str.equals("bbc"));
		Assert.assertTrue(str1.equals("bbc"));
		Assert.assertTrue(str2.equals("bbc"));
		
	}
}
