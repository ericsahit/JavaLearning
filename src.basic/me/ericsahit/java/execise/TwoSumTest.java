package me.ericsahit.java.execise;

import java.util.HashMap;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class TwoSumTest {
	
	@Test
	public void test() {
		Print.print(reverseWords("the sky is blue"));
		Print.print(reverseWords("nospace"));
		Print.print(reverseWords(""));
		Print.print(reverseWords(null));
		Print.print(reverseWords(" fsfesf idja"));
		Print.print(reverseWords(" fsfesf idja "));
		
		Print.print(reverseWords2("the sky is blue"));
		Print.print(reverseWords2("nospace"));
		Print.print(reverseWords2(""));
		Print.print(reverseWords2(null));
		Print.print(reverseWords2(" fsfesf idja"));
		Print.print(reverseWords2(" fsfesf     idja "));
	}
	
	/**
	 * leetcode 1: two sum
	 * https://leetcode.com/problems/two-sum/
	 * 解法比较慢，是否可以优化执行时间？ 
	 */
	public int[] twoSum(int[] nums, int target) {
		
		if (nums == null || nums.length < 2) {
			return null;
		}
		
		int[] ret = new int[2];
		
		HashMap<Integer, Integer> valLocationMap = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < nums.length; i++) {
			int second = target - nums[i];
			if (valLocationMap.containsKey(second)) {
				ret[0] = valLocationMap.get(second);
				ret[1] = i + 1;
				break;
			} else {
				valLocationMap.put(nums[i], i + 1);
			}
		}
		return ret;
	}
	
	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 *     int val;
	 *     TreeNode left;
	 *     TreeNode right;
	 *     TreeNode(int x) { val = x; }
	 * }
	 */
	
	/**
	 * leetcode 2 求二叉树的深度
	 * 递归解法很慢，是否利用其它来优化？ 
	 */
    public int maxDepth(TreeNode root) {
    	if (root == null) {
    		return 0;
    	}
    	int leftDepth = maxDepth(root.left);
    	int rightDepth = maxDepth(root.right);
    	
    	return leftDepth >= rightDepth ? (leftDepth + 1) : (rightDepth + 1); 
    }
    
	/**
	 * leetcode 3 反转一个字符串中的单词，例如the sky is blue => blue is sky the 
	 * 
	 * 前面和后面的空格需要去掉
	 * 多个空格需要压缩成一个空格：
	 * 1.简单的想法是，用一个辅助空间，将字符反转之后拷贝进去，在拷贝之前增加一个空格。
	 */
    public String reverseWords(String s) {
    	
    	if (s == null) {
    		return null;
    	}
        
    	int start = 0;
    	int end = 0;
    	char[] charArr = s.toCharArray();
    	
    	for (int i = 0; i < charArr.length; i++) {
    		if (charArr[i] == ' ') {
    			end = i-1;
    			reverseCharArr(charArr, start, end);
    			start = i + 1;
    		}
    		//少考虑了终止时候的一次反转
		}
    	//少考虑了一次反转，最后一个空格到最后的单词的反转
    	reverseCharArr(charArr, start, charArr.length - 1);
    	
    	reverseCharArr(charArr, 0, charArr.length - 1);
    	
    	return String.valueOf(charArr).trim();
    }
    
    public void reverseCharArr(char[] arr, int start, int end) {
    	while (start < end) {
    		char tmp = arr[start];
    		arr[start] = arr[end];
    		arr[end] = tmp;
    		
    		start++;
    		end--;
    	}
    }
    /**
	 * 多个空格需要压缩成一个空格：
	 * 1.简单的想法是，用一个辅助空间，将字符反转之后拷贝进去，在拷贝之前增加一个空格。
     */
    public String reverseWords2(String s) {
    	
    	if (s == null) {
    		return null;
    	}
        
    	int start = 0;
    	int end = 0;
    	int retIdx = 0;
    	char[] charArr = s.toCharArray();
    	char[] retArr = new char[charArr.length];
    	
    	for (int i = 0; i < charArr.length; i++) {
    		if (charArr[i] == ' ') {
    			end = i-1;
    			retIdx = copyReverseCharArr(charArr, start, end, retArr, retIdx);
    			start = i + 1;
    		}
		}
    	//少考虑了一次反转，最后一个空格到最后的单词的反转
    	copyReverseCharArr(charArr, start, charArr.length - 1, retArr, retIdx);
    	
    	reverseCharArr(retArr, 0, retArr.length - 1);
    	
    	
    	return String.valueOf(retArr).trim();
    }
    
    public int copyReverseCharArr(char[] originArr, int start, int end, char[] destArr, int idx) {
    	
    	if (start > end) {
    		return idx;
    	}
    	if (idx > 0) 
    		destArr[idx++] = ' ';
    	
    	for (int i = end; i >= start; i--) {
    		destArr[idx++] = originArr[i];
		}
    	return idx;
    }
    
    /**
     * leetcode 2 两个数以链表形式保存，相加，返回一个链表形式的和
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8 
	 * 
	 * 
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	
    }
	
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
   public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
   }
}




