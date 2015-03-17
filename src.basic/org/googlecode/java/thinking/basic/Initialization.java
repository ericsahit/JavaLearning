package org.googlecode.java.thinking.basic;

import java.util.Arrays;
import java.util.Random;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.googlecode.java.thinking.basic.util.Print.*;

public class Initialization {
	
	private static final Logger logger = LoggerFactory.getLogger(Enum.class);

	/**
	 * 数组的初始化
	 */
	@Test
	public void arrayInt() {
		int[] a1 = {1, 2, 3, 4, 5};
		int a2[];
		a2 = a1;
		Integer[] a3 = {
				new Integer(1),
				new Integer(2),
				3 //AutoBoxing
		};
		logger.info(Arrays.toString(a3));
		
		Random rand = new Random(47);
		a2 = new int[rand.nextInt(20)];
		logger.info("length of array = " + a2.length);
		logger.info(Arrays.toString(a2));
		
		// 可变参数列表 参数可以为空
		printArray(47, 3.14F, 11.11);
		printArray("one", "two", "three");
		printArray((Object[])new Integer[]{ 1, 2, 3, 4 });
		printArray(new Initialization(), new Initialization());
		printArray();
		
		f('a');
		f();
		logger.info("Character[] type: " + new Character[0].getClass());
	}
	
	private void printArray(Object...args) {
		for (Object obj : args) {
			logger.info("{}", obj);
		}
	}
	
	private void f(char...characters) {
		logger.info("{} length {}", characters.getClass(), characters.length);
	}
	
	public void array() {
		
	}
	

}
