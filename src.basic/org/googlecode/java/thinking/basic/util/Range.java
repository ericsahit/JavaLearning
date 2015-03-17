package org.googlecode.java.thinking.basic.util;

public class Range {
	
	public static int[] range(int n) {
		int[] result = new int[n];
		for (int i = 0; i < n; i++) {
			result[i] = i;
		}
		return result;
	}
	
	
}
