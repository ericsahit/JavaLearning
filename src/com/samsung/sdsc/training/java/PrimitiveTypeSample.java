package com.samsung.sdsc.training.java;

import java.util.Arrays;

public class PrimitiveTypeSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char c = 'c';
		Character c1 = null;
		Character c2 = c;
		System.out.println(c);
		System.out.println(c1);// null
		System.out.println(c2);

		boolean b1 = true;
		boolean b2 = false;
		Boolean bl = true;
		Boolean bl1 = new Boolean(b1);
		Boolean bl2 = new Boolean("true");
		Boolean bl3 = new Boolean("True");
		Boolean bl4 = new Boolean("sldif");
		System.out.println(b1);
		System.out.println(b2);
		System.out.println(bl);
		System.out.println(bl1);
		System.out.println(bl2);// true
		System.out.println(bl3);// true
		System.out.println(bl4);// false

		byte b = 23;// -128~127
		Byte bt = b;
		Byte bt1 = new Byte((byte) 2);
		Byte bt2 = new Byte("45");// NumberFormatException
		// Byte bt3 = new Byte("345");//NumberFormatException
		System.out.println(b);
		System.out.println(bt);
		System.out.println(bt1);
		System.out.println(bt2);

		int i = 65;
		int j = 03453;// 8进制
		int k = 0xf23fae3b;// 16进制
		Integer ig = 100;
		Integer ig1 = 100;
		Integer ig2 = new Integer(100);
		Integer ig3 = 200;
		Integer ig4 = 200;
		System.out.println(i);
		System.out.println(j);
		System.out.println(k);
		System.out.println(ig == ig1);// true
		System.out.println(ig == ig2);// false
		System.out.println(ig3 == ig4);// false

		float f = -2.390329f;
		Float fl = 32.930f;
		System.out.println(f);
		System.out.println(fl);

		double d = -239.34093921;
		Double dl = new Double(23.34534);
		System.out.println(d);
		System.out.println(dl);

		int[] array = new int[5];
		int[] array1 = { 3, 1223, 23, 123, 43, 668, 0, 890 };
		System.out.println(Arrays.toString(array));
		System.out.println(array1);
	}
}
