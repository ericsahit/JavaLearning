package me.ericsahit.java.execise;

import junit.framework.Assert;

import org.junit.Test;

public class BinarySearchEcercise {
	
	@Test
	public void test() {
		int[] arr = new int[] {1,2,3,3,3,3,4,5};
		//寻找第一个k和最后一个k
		Assert.assertEquals(4, findRepeatKInSortedArray(arr, 3));
		Assert.assertEquals(1, findRepeatKInSortedArray(arr, 4));
		//寻找刚好比k大的值
		Assert.assertEquals(5, findJustSmall(arr, 0, arr.length, 4));
		Assert.assertEquals(-1, findJustSmall(arr, 0, arr.length, 1));
		Assert.assertEquals(1, findJustSmall(arr, 0, arr.length, 3));
		Assert.assertEquals(6, findJustSmall(arr, 0, arr.length, 5));
	}
	
	public int findRepeatKInSortedArray(int[] arr, int k) {
		if (arr == null || arr.length == 0) {
			return 0;
		}
		
		int firstK = binarySearchFirst(arr, 0, arr.length-1, k);
		int lastK = 0;
		if (firstK == -1)
			return 0;
		else 
			lastK = binarySearchLast(arr, 0, arr.length-1, k);
		
		return lastK-firstK+1;
	}
	
	public int binarySearchFirst(int[] arr, int start, int end, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int orgStart = start;
		int orgEnd = end;
		
		while (start <= end) {
			int mid = start + (end - start) / 2;
			int midVal = arr[mid];
			if (k > midVal) {
				start = mid + 1;
			} else if (k < midVal) {
				end = mid - 1;
			} else {
				if ((mid > orgStart && arr[mid-1] != k) || mid == orgStart) {
					return mid;
				} else 
					end = mid - 1;
			}
		}
		
		return -1;
	}
	
	public int binarySearchLast(int[] arr, int start, int end, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int orgStart = start;
		int orgEnd = end;
		
		while (start <= end) {
			int mid = start + (end - start) /2 ;
			int midVal = arr[mid];
			if (k > midVal) {
				start = mid + 1;
			} else if (k < midVal) {
				end = mid - 1;
			} else {
				if ((mid < orgEnd && arr[mid+1] != k) || mid == orgEnd) {
					return mid;
				} else 
					start = mid + 1;
			}
		}
		
		return -1;
	}
	
	public int findJustSmall(int[] arr, int start, int end, int k) {
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int orgStart = start;
		int orgEnd = end;
		
		while (start <= end) {
			int mid = start + (end - start) /2 ;
			int midVal = arr[mid];
			
			if (k <= midVal) {
				end = mid - 1;
			} else {
				if ((mid < orgEnd && arr[mid+1] >= k ) || mid == orgEnd)
					return mid;
				else
					start = mid + 1;
			}
		}
		return -1;
	}
	
}
