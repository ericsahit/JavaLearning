package com.samsung.sdsc.training.java;

import java.util.Arrays;

public class StringSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char data[] = { 'c', 'd', 'e' };
		String str = new String(data);
		System.out.println(str);

		String cde = "cde";
		System.out.println("abc" + cde);
		System.out.println(cde == str);// false
		System.out.println(cde.equals(str));// true

		String c = "abc".substring(2, 3);
		String d = cde.substring(1, 2);
		System.out.println(c);
		System.out.println(d);

		String longStr = cde.concat(c).concat(d);
		System.out.println(longStr);
		System.out.println(c);
		System.out.println(d);

		System.out.println(longStr.startsWith("cd"));
		System.out.println(longStr.endsWith("cd"));

		System.out.println(c.equals("C"));
		System.out.println(c.equalsIgnoreCase("C"));
		
		System.out.println("   as  d f  ".trim());//as  d f
		System.out.println("aa,bb,cc,dd,ee".split(","));
		System.out.println(Arrays.toString("aa,bb,cc,dd,ee".split(",")));
		
		System.out.println("haihua is clever, many girls like haihua.".replaceFirst("haihua", "段宁"));
		System.out.println("haihua is clever, many girls like haihua.".replace("haihua", "段宁"));//same as replaceAll
		System.out.println("haihua is clever, many girls like haihua.".replaceAll("haihua", "段宁"));
		
//		StringBuffer sb = new StringBuffer();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10; i++) {
			sb.append("," + i);
		}
		System.out.println(sb);
	}
}
