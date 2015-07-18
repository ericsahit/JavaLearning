package me.ericsahit.java.execise;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class BinaryFindTest {
	
	@Test
	public void test() {
		Assert.assertEquals(binaryFind(new int[] {1, 2, 3, 4, 5}, 3), 2);
		Assert.assertEquals(binaryFind(new int[] {1, 2, 3, 4, 5, 6}, 3), 2);
		
		Assert.assertEquals(binaryFind(new int[] {1, 2, 3, 4, 5, 6}, -1), -1);
		
		Assert.assertEquals(binaryFindFirst(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 3), 2);
		Assert.assertEquals(binaryFindFirst(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 4), 6);
		Assert.assertEquals(binaryFindFirst(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 10), -1 );
		
		Assert.assertEquals(binaryFindLast(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 3), 5);
		
		Assert.assertEquals(binaryFindFirst2(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 3), 2);
		Assert.assertEquals(binaryFindFirst2(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 4), 6);
		Assert.assertEquals(binaryFindFirst2(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 10), -1 );
		
		Assert.assertEquals(binaryFindLast2(new int[] {1, 2, 3, 3, 3, 3, 4, 5, 6}, 3), 5);
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
	
	/**
	 * 二分查找第一个K
	 * 第一种思路，通过一个特殊判断，来简化每次循环的复杂性
	 */
	public int binaryFindFirst(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int start = 0;
		int end = arr.length - 1;
		int mid, midVal;
		
		while (start <= end) {
			
			mid = start + ((end - start) >> 1);
			midVal = arr[mid];
			
			if (k == midVal) {//通过一个判断，简化了后继的循环
				if (mid > 0 && arr[mid-1] != k || mid == 0) {
					return mid;
				} else {
					end = mid - 1;
				}
			} else if (k > midVal) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return -1;
	}
	
	
	/**
	 * 二分查找最后一个K的位置 
	 */
	public int binaryFindLast(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int start = 0;
		int end = arr.length - 1;
		int mid, midVal;
		
		while (start <= end) {
			
			mid = start + ((end - start) >> 1);
			midVal = arr[mid];
			
			if (k == midVal) {
				if (mid < arr.length - 1 && arr[mid+1] != k || mid == arr.length - 1) {
					return mid;
				} else {
					start = mid + 1;
				}
			} else if (k > midVal) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		
		return -1;
	}
	
	/**
	 * 寻找第一个k的位置，第二种思路，利用循环不变性
	 * left肯定第一个
	 */
	public int binaryFindFirst2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int start = 0;
		int end = arr.length - 1;
		int mid, midVal;
		
		while (start < end) {
			
			mid = start + ((end - start) >> 1);
			midVal = arr[mid];
			
			if (k > midVal) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (arr[start] == k)
			return start;
		
		return -1;
	}
	
	/**
	 * 寻找最后一个k的位置，第二种思路，利用循环不变性
	 * 当left=right时，right肯定是最后一个k的下标
	 */
	public int binaryFindLast2(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		int start = 0;
		int end = arr.length - 1;
		int mid, midVal;
		
		while (start < end) {
			mid = start + ((end - start) >> 1);
			midVal = arr[mid];
			
//			if (k < midVal) {
//				end = mid - 1;
//			} else {//这里会有问题，因为小数是舍弃的，所以两个数的时候mid会死循环
//				start = mid; 
//			}
			
			if (k < midVal) {
				end = mid - 1;
			} else if (k > midVal) {
				start = mid + 1; 
			} else {
				if (start == mid)
					if (arr[end] == k)
						return end;
					else
						return start;
				else
					start = mid;
			}
		}
			
		if (arr[end] == k)
			return end;
		
		return -1;
	}
	
	
}
