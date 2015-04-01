package org.googlecode.java.thinking.basic.algorithm;

import java.util.Arrays;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class QuickSortTest {
	
	@Test
	public void test() {
		
		int[] arr = new int[]{4, 6, 3, 89, 54, 454, 242, 1, 3, 98, 56};
		//Arrays.sort(arr);
		qsort(arr, 0, arr.length-1);
		Print.print(Arrays.toString(arr));
	}
	
	private void qsort(int[] arr, int start, int end) {
		if (start > end) {
			int partition = partition(arr, start, end);
			qsort(arr, start, partition-1);
			qsort(arr, partition+1, end);
		}
		
	}
	
	////优化，可以选择开始，结束，和中间三者中间的数值作为partition
	private int partition(int[] arr, int start, int end) {
		
		int pivotPos = (start+end)/2;
		
		int pivot = arr[pivotPos];
		
		arr[start] = pivot;
		
		int i = start;
		int j = end;
		while (i < j) {//边界如何选择？
			while (j > i && arr[j] >= pivot) {
				j--;
			}
			if (arr[j] >= pivot)
				arr[i] = arr[j];
			
			while (i < j && arr[i] <= pivot) {
				i++;
			}
			if (arr[i] <= pivot)
			arr[j] = arr[i];
		}
		arr[i] = pivot;
		
		return i;
	}
}
