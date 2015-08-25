package me.ericsahit.java.execise;

import junit.framework.Assert;

import org.junit.Test;

public class ArrayExercise {
	
	@Test
	public void test() {
		Assert.assertEquals(2, findRepeatedInteger(new int[] {2,3,1,0,2,5,3}));
		Assert.assertEquals(3, findRepeatedInteger(new int[] {2,4,1,0,3,5,3}));
		Assert.assertEquals(-1, findRepeatedInteger(new int[] {2,4,1,0,6,5,3}));
	}
	
	/**
	 * offer51: 找出数组中重复的数字
	 * 数组中数字范围为0-n-1，有重复的数字，而且不知道重复多少次，找出一个重复的数字
	 * {2,3,1,0,2,5,3} 找出3
	 * 思路，把每个元素都放到自己的位置上
	 */
	public int findRepeatedInteger(int[] arr) {
		if (arr == null) {
			return -1;
		}
		
		for (int i = 0; i < arr.length; i++) {
			while (arr[i] != i) {
				if (arr[arr[i]] == arr[i]) {
					return arr[i];
				} else {
					int tmp = arr[arr[i]];
					arr[arr[i]] = arr[i];
					arr[i] = tmp;
				}
			}
		}
		return -1;
	}
	
}
