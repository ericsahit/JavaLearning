package me.ericsahit.java.execise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class CodeExercise {
	
	@Test
	public void test() {
		
		List<Interval> list = new ArrayList<Interval>();
		list.add(new Interval(1, 3));
		list.add(new Interval(2, 6));
		list.add(new Interval(8, 10));
		list.add(new Interval(9, 18));
		
		List<Interval> result = merge(list);
		for (Interval inv: result) {
			Print.print(inv);
		}
		
		//code020
		Assert.assertTrue(isValid("()[]{}"));
		Assert.assertTrue(!isValid("]"));
		Assert.assertTrue(!isValid("(]"));
		Assert.assertTrue(!isValid("([)]"));
		
		//code021
		List<String> list2 = generateParenthesis(3);
		for (String s: list2) {
			Print.print(s);
		}
	}
	
    /**
     * code054 螺旋矩阵
     * https://leetcode.com/problems/spiral-matrix/
     * Given a collection of intervals, merge all overlapping intervals. 
　　	 * For example, 
　　  * Given
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
　　  * You should return [1,2,3,6,9,8,7,4,5].
     * 给定一个m*n的矩阵，输入所有元素的螺旋顺序
     * 
     * 思路，按照螺旋顺序来输出，这个题目跟offer的一道题目是一样的
     * 主要问题：每次输出后需要判断，否则会多输出一次
     */
    public List<Integer> spiralOrder(int[][] matrix) {
    	
    	List<Integer> result = new ArrayList<Integer>();
    	if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
    		return result;
    	}
    	
    	int rows = matrix.length;
    	int cols = matrix[0].length;
    	int startRow = 0, startCol = 0, endRow = rows - 1, endCol = cols - 1;
    	
    	while (startRow <= endRow && startCol <= endCol) {
    		for (int i = startCol; i <= endCol; i++) {
    			result.add(matrix[startRow][i]);
			}
    		startRow++;
    		if (startRow > endRow) break;//增加了每一次的判断，否则会出错，当输入是[[1,2,3]]时候，会多输出一次
    		
    		for (int i = startRow; i <= endRow; i++) {
    			result.add(matrix[i][endCol]);
			}
    		endCol--;
    		if (startCol > endCol) break;
    		
    		for (int i = endCol; i >= startCol; i--) {
    			result.add(matrix[endRow][i]);
			}
    		endRow--;
    		if (startRow > endRow) break;
    		
    		for (int i = endRow; i >= startRow; i--) {
    			result.add(matrix[i][startCol]);
			}
    		startCol++;
    	}
    	
    	return result;
    }
	
	
    /**
     * code056 合并区间
     * https://leetcode.com/problems/merge-intervals/
     * 
     * Given a collection of intervals, merge all overlapping intervals. 
　　	 * For example, 
　　  * Given [1,3],[2,6],[8,10],[15,18], 
　　  * return [1,6],[8,10],[15,18]. 
     * 合并重叠的区间
     * 
     * 思路，先排序，遍历一遍合并
     */
	public List<Interval> merge(List<Interval> intervals) {
		
		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.isEmpty()) {
			return result;
		}
		
		Comparator<Interval> comparator = new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		};
		
		Collections.sort(intervals, comparator);
		
		Interval last = null;
		for (Interval inv: intervals) {
			if (last == null) {
				last = inv;
				result.add(inv);
			} else {
				if (inv.start <= last.end) {//这里容易出错，需要分为几种情况，结束区间大于前面结束区间，才做替换
					if (inv.end > last.end)
						last.end = inv.end;
				} else {
					result.add(inv);
					last = inv;
				}
			}
		}
		
		return result;
	}
	
    /**
     * code020 括号匹配
     * https://leetcode.com/problems/valid-parentheses/
     * 
　　Given a string containing just the characters ‘(‘, ‘)’, ‘{‘, ‘}’, ‘[’ and ‘]’, determine if the input string is valid. 
　　The brackets must close in the correct order, “()” and “()[]{}” are all valid but “(]” and “([)]” are not. 
     * 括号匹配
     * 
     * 思路，使用栈，遍历输出
     */
	public boolean isValid(String s) {
		if (s == null || s.trim().isEmpty()) {
			return true;
		}
		Stack<Character> stack = new Stack<Character>();
		char[] arr = s.toCharArray();
		for (char c: arr) {
			if (c == '(' || c == '{' || c== '[') {
				stack.push(c);
			} else if (c == ')' || c == '}' || c == ']') {
				
				if (stack.isEmpty()) {
					return false;
				}
				
				if (c == ')') {
					if (stack.peek() == '(') {
						stack.pop();
					} else {
						return false;
					}
				} else if (c == '}') {
					if (stack.peek() == '{') {
						stack.pop();
					} else {
						return false;
					}
				} else if (c == ']') {
					if (stack.peek() == '[') {
						stack.pop();
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		
		if (stack.isEmpty())
			return true;
		
		return false;
	}
	
    /**
     * code021 合并排序链表
     * https://leetcode.com/problems/merge-two-sorted-lists/
     * 
Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists. 
     * 
     * 思路，链表题目的难点，主要在于指针的操作。希望可以举一反三
     * 这道题目相似的有一些，可以都从思路上解决
     */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		ListNode head = new ListNode(0);
		ListNode pnode = head;
		ListNode p1 = l1;
		ListNode p2 = l2;
		while (p1 != null && p2 != null) {
			if (p1.val > p2.val) {
				pnode.next = p2;
				p2 = p2.next;
			} else {
				pnode.next = p1;
				p1 = p1.next;
			}
			pnode = pnode.next;
		}
		
		if (p1 != null) {
			pnode.next = p1;
		} else if (p2 != null) {
			pnode.next = p2;
		}
		
		return head.next;
	}
	
	/**
	 * code022
	 * https://leetcode.com/problems/generate-parentheses/
	 * 　Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses. 
　　For example, given n = 3, a solution set is:

"((()))", "(()())", "(())()", "()(())", "()()()"
	 * 产生所有合理的
	 * 思路：递归思路
	 * ****难点：怎么保证括号一个一个被写入，多思考
	 */
    public List<String> generateParenthesis(int n) {
    	List<String> result = new ArrayList<String>();
    	if (n <= 0) {
    		return result;
    	}
    	char[] arr = new char[n * 2];
    	generateParenthesisSub(n, n, 0, arr, result);
    	return result;
    }
    
    public void generateParenthesisSub(int left, int right, int idx, char[] arr, List<String> list) {
    	if (right < left) {
    		return;
    	} else if (left == 0 && right == 0) {
    		list.add(new String(arr));
    	} else {
    		if (left > 0) {
    			arr[idx]='(';
    			generateParenthesisSub(left-1, right, idx+1, arr, list);
    			//left--;
    		}
    		if (right > 0) {
    			arr[idx]=')';
    			generateParenthesisSub(left, right-1, idx+1, arr, list);
    			//right--;
    		}
    	}
    }
	
}

 class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }

 class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
     public String toString() {return start + ", " + end;}
 }
