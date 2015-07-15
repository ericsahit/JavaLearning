package me.ericsahit.java.execise;

import org.junit.Test;

public class BinaryFindTest {
	
	@Test
	public void test() {
		
	}
	
	/**
	 * 二分查找
	 * 可以改进很多地方
	 * 一定要注意终止条件，还有很多二分变种的终止条件
	 */
	public int binaryFind(int[] arr, int k) {
		
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int start = 0;
		int end = arr.length - 1;
		
		while (start <= end) {
			int mid = (start + end) >> 1;
			int midVal = arr[mid];
			if (k > midVal) {
				start = mid + 1;
			} else if (k < midVal) {
				end = mid - 1;
			} else {
				return mid;
			}
		}
		
		return -1;
		
	}
	
	
}
