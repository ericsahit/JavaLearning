package me.ericsahit.java.execise;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class StringExercise {
	
	@Test
	public void test() {
		Print.print(reverseWordInString("the sky is blue"));
		Print.print(reverseWordInString("nospace"));
		Print.print(reverseWordInString(""));
		Print.print(reverseWordInString(null));
		Print.print(reverseWordInString(" fsfesf idja"));
		Print.print(reverseWordInString(" fsfesf idja "));
		
		Print.print(leftRotateString("abcdef", 3));
		Print.print(leftRotateString("abcdef", 2));
		Print.print(leftRotateString("", 2));
		Print.print(leftRotateString(null, 4));
		Print.print(leftRotateString("abcdef", 0));
		Print.print(leftRotateString("abcdef", 1));
	}
	
	/**
	 * offer42 p218
	 * leetcode Reverse word in string
	 * leetcode 3 反转一个字符串中的单词，例如the sky is blue => blue is sky the 
	 * 
	 * 对比自己写的解法，这种代码写法清晰了不少
	 * 核心：设置两个指针
	 */
	public String reverseWordInString(String str) {
		if (str == null)
			return null;
		
		char[] arr = str.toCharArray();
		
		//设置两个指针，实现上更清晰一些
		int start=0, end=0;
		while (start < arr.length) {
			if (arr[start] == ' ') {
				start++;
				end++;
				//注意要先判断是否超限，再判断空格，否则报错
			} else if (end >= arr.length || arr[end] == ' ') {
				reverseCharArr(arr, start, end - 1);
				start = ++end;
			} else {
				end++;
			}
		}
		reverseCharArr(arr, 0, arr.length - 1);
		return String.valueOf(arr);
	}
	
	public void reverseCharArr(char[] arr, int start, int end) {
		while (start < end) {
			char c = arr[start];
			arr[start] = arr[end];
			arr[end] = c;
			
			start++;
			end--;
		}
	}
	
	/**
	 * offer42-2 p219 左旋字符串
	 * input "abcdefg" 2
	 * output "cdefgab"
	 * 与上面的思路类似
	 */
	public String leftRotateString(String str, int idx) {
		if (str == null || idx < 0 || idx >= str.length()) return null;
		char[] arr = str.toCharArray();
		
		reverseCharArr(arr, 0, idx-1);
		reverseCharArr(arr, idx, arr.length-1);
		reverseCharArr(arr, 0, arr.length-1);
		
		return String.valueOf(arr);
	}
}
