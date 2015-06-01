package me.ericsahit.java.execise;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Assert;
import org.junit.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Coding0601 {

	@Test
	public void testFindMatrixNumber() {
		int[] arr = new int[] {
				1, 2, 8, 9, 
				2, 4, 9, 12, 
				4, 7, 10, 13,
				6, 8, 11, 15
				};
		
		Print.print();
		
		Assert.assertTrue(findMatrixNumber(arr, 4, 4, 10));
		Assert.assertTrue(!findMatrixNumber(arr, 4, 4, 19));
		Assert.assertTrue(!findMatrixNumber(arr, 4, 4, -1));
		Assert.assertTrue(!findMatrixNumber(arr, 4, 4, 14));
		Assert.assertTrue(findMatrixNumber(arr, 4, 4, 6));
	}
	
	/**
	 * 
	 * @param arr
	 * @param rowSize
	 * @param colSize
	 * @param num
	 * @return
	 * 
	 * 注意空指针
	 */
	public boolean findMatrixNumber(int[] arr, int rowSize, int colSize, int num) {
		boolean hasFound = false;
		
		if (arr != null && arr.length == rowSize * colSize && rowSize > 0 && colSize > 0) {
			int rowIndex = 0;
			int colIndex = colSize - 1;
			
			while (rowIndex < rowSize && colIndex >= 0) {
				int cell = arr[rowIndex * rowSize + colIndex];
				if (num == cell) {
					return true;
				} else if (num < cell) {
					colIndex--;
				} else if ( num > cell ) {
					rowIndex++;
				}
			}
		}
		
		return hasFound;
	}
	
	/**
	 * 实现一个函数，将字符串中的每个空格都替换成%20.
	 * Example: We are happy => We%20are%20happy
	 * @param arr
	 */
	public char[] StrReplaceSpace(char[] arr) {
		char[] ret = null;
		
		if (arr != null && arr.length > 0) {
			int spaceCount = 0;
			for (char c: arr) {
				if (c == ' ') {
					spaceCount++;
				}
			}
			
			ret = new char[arr.length + spaceCount * 2];
			
			int idx1 = arr.length - 1;
			int idx2 = 0;
			
			for (int i = arr.length - 1; i >= 0; i++) {
				if (arr[i] != ' ') {
					idx1--;
				} else {//如果识别这么多的索引
					int len = arr.length - idx1;
					if (len > 0) 
						System.arraycopy(arr, idx1+1, ret, ret.length - len, len);
					
				}
				
			}
		}
		
		return ret;
		
		
	}
}








