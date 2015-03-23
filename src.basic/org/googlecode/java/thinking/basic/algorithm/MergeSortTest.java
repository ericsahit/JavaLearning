package org.googlecode.java.thinking.basic.algorithm;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class MergeSortTest {
	
	@Test
	public void testMergeSort() {
		
		int[] arr = new int[] {4,6,2,54,8,1,3,7,9,10,5};
		
		int[] result = new int[arr.length];
		
		mergeSort2(arr, 0, arr.length-1, result);
		
		for (int i: result) {
			Print.printnb(i+" ");
		}
	}

	public void mergeSort2(int[] arr, int first, int last, int[] result) {
		
		if (first < last) {
			int mid = (first + last) /2;
			Print.info("sort:"+first+" "+mid+" "+last);
			
			mergeSort2(arr, first, mid, result);
			mergeSort2(arr, mid+1, last, result);
			mergeSortSub2(arr, first, mid, last, result);
		}
	}
	
	public void mergeSortSub2(int[] arr, int first, int mid, int last, int[] result) {
		
		int i=first;
		int j=mid+1;
		int k=first;
		
		while (i<=mid && j<=last) {
			if (arr[i] <= arr[j]) {
				result[k]=arr[i];
				i++;
			} else {
				result[k]=arr[j];
				j++;
			}
			k++;
		}
		while (i <= mid) {
			result[k++]=arr[i++];
		}
		while (j <= last) {
			result[k++]=arr[j++];
		}
		
		for(int v=0; v<k; v++) {
			arr[first+v]=result[first+v];
		}
			
	}
	
	private void mergeSortSub3(int[] arr, int first, int mid, int last, int[] result) {
		
		int i=first;
		int j=mid+1;
		int k=mid+1;
		
		while(i<=mid && j<=last) {
			
		}
		
	}
	
	
	
	public int[] mergeSort(int[] arr) {
		int[] result;
		
		int half=arr.length/2;
		int[] arr1=new int[half];
		int[] arr2=new int[arr.length-half];
		
		System.arraycopy(arr, 0, arr1, 0, arr1.length);
		System.arraycopy(arr, half, arr2, 0, arr2.length);
		
		int[] arrSort1=mergeSort(arr1);
		int[] arrSort2=mergeSort(arr2);
		
		result=mergeSortSub(arrSort1, arrSort2);
		
		return result;
	}
	
	public int[] mergeSortSub(int[] arr1, int[] arr2) {
		
		int[] result=new int[arr1.length+arr2.length];
		
		int i=0;
		int j=0;
		int k=0;
		
		while (true) {
			if (arr1[i] < arr2[j]) {
				result[k++]=arr1[i];
				if (++i>arr1.length-1) break;
			} else {
				result[k++]=arr2[j];
				if (++j>arr2.length-1) break;
			}
		}
		
		for(;i<arr1.length;i++) {
			result[k++]=arr1[i];
		}
		for(;j<arr2.length;j++) {
			result[k++]=arr2[j];
		}
		
		return result;
	}
}
