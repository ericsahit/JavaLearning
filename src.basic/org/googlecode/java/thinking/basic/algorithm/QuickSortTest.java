package org.googlecode.java.thinking.basic.algorithm;

import java.util.Arrays;

public class QuickSortTest {
	
	public void test() {
		
		int[] arr = new int[]{4, 6, 3, 89, 54, 454, 242, 1, 3, 98, 56};
		Arrays.sort(arr);
	}
	
	private int partition(int[] arr, int start, int end) {
		int partition = (start+end)/2;
		
		int pivot = arr[partition];
		
		int i = start;
		int j = end;
		boolean first=true;
		while (i < j) {
			while (arr[j] >= pivot) {
				j--;
			}
			if (first) 
				arr[partition] = arr[j];
			else
				arr[i] = arr[j];
				
			while (arr[i] <= pivot) {
				i++;
			}
			arr[j] = arr[i];
		}
		
		return partition;
	}
}
