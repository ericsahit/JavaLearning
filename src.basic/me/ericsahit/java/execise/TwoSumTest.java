package me.ericsahit.java.execise;

import java.util.HashMap;

import org.junit.Test;

public class TwoSumTest {
	
	@Test
	public void test() {
		
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
	 * leetcode 3 反转一个字符串
	 * 递归解法很慢，是否利用其它来优化？ 
	 */
    public String reverseWords(String s) {
    	
    	if (s == null) {
    		return null;
    	}
        
    	char[] charArr = s.toCharArray();
    	
    	
    	return String.valueOf(charArr);
    }
    
	
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}




