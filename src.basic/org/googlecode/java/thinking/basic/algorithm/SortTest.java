package org.googlecode.java.thinking.basic.algorithm;

import java.util.Random;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class SortTest {
	
	@Test
	public void test() {
		
		int[] arr = new int[100];
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < 100; i++) {
			int val = rand.nextInt(100);
			arr[i] = val;
		}
		int[] arrSort = arr.clone();
		//System.arraycopy(arr, 0, arrSort, 0, arr.length);
		bubbleSort(arr);
		for (int i: arrSort) {
			Print.printnb(i+", ");
		}
	}
	
	//男
	private void bubbleSort(int[] arr) {
		
		int swapCount = 0;
		
		boolean completeFlag = false;
		
		for (int i = 0; i < arr.length; i++) {
			
			if (completeFlag) {//平均在80-90左右
				Print.print("complete not to end: "+i);
				return;
			}
			completeFlag = true;
			
			for (int j = arr.length-1; j > i; j--) {

				if (arr[j] < arr[j-1]) {
					
					
					
					int temp = arr[j];
					arr[j]=arr[j-1];
					arr[j-1]=temp;
					completeFlag = false;
				}
			}
		}
	}
	
	private void insertSort(int[] arr) {
		
	}
	
}
