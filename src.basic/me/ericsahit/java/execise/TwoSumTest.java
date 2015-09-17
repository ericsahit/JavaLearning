package me.ericsahit.java.execise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class TwoSumTest {
	
	/**
	 * 总结：
	 * 
	 * 1.链表类：难点在于终止条件
	 * 
	 * 2.二分查找类
	 * 3.二进制的位运算
	 * 
	 * code002 code043 code066 code067
	 * ****需要注意，链表题目一定不要忘记写p = p.next
	 * ****一个循环搞定，不需要三个循环。优化代码，主要是思路要清晰。
	 * 
	 * code043 大整数的字符串乘法，理解其思路
	 * TODO：offer使用二进制的方式来实现乘法的题目？
	 * 
	 * 4.字符串类型
	 * code003 最大不重复字串
	 * 
	 */
	
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
		
		//查找旋转数组中的最小数字
		Assert.assertEquals(1, findMin(new int[] { 1, 2, 3, 4, 5}));
		Assert.assertEquals(1, findMin(new int[] { 4, 5, 7, 1, 2, 3}));
		Assert.assertEquals(1, findMin(new int[] { 7, 1, 2, 3, 4, 5}));
		Assert.assertEquals(1, findMin(new int[] { 6, 7, 1, 2, 3, 4, 5}));
		Assert.assertEquals(1, findMin(new int[] { 2, 3, 4, 5, 7, 1}));
		Assert.assertEquals(1, findMin(new int[] { 2, 1}));
		
		StringBuilder sb = new StringBuilder();
		
		//code002 两个数链表相加
		ListNode ret = addTwoNumbers(new ListNode(0), new ListNode(0));
		Print.print(ret.val);
		
		//code066 数组表示的整数+1
		//Assert.assertEquals(new int[] {1, 0}, plusOne(new int[]{9}));
		
		//code067 二进制数相加
		Print.print(addBinary("11", "1"));
		Print.print(addBinary("1", "111"));
		Assert.assertEquals(addBinary2("11", "1"), addBinary("11", "1"));
		Assert.assertEquals(addBinary2("1", "111"), addBinary("1", "111"));
		
		//code003 最长不重复的字串
		Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
		Assert.assertEquals(4, lengthOfLongestSubstring("abcd"));
		Assert.assertEquals(6, lengthOfLongestSubstring("abcdafe"));
		Assert.assertEquals(5, lengthOfLongestSubstring("abcdcebf"));//abcdcaefb
		Assert.assertEquals(6, lengthOfLongestSubstring("abcdcaefb"));//
		
		//code004
		Print.print(findMedianSortedArrays(new int[]{1,1}, new int[]{1,2}));
		Print.print(findMedianSortedArrays(new int[]{100000}, new int[]{100001}));
	}
	
	/**
	 * code001: two sum
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
	 * code002 两个链表的和 
	 * 
	 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8 
	 * 
	 * 数据反向存储，即第一个节点表示最低位，最后一个节点表示最高位
	 * 
	 * 和offer的一个题目比较类似
	 * ****需要注意，链表题目一定不要忘记写p = p.next
	 * ****[1], [9,9] ==> [0,0,1]
	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode ret = new ListNode(0);
    	if (l1 == null || l2 == null) {
    		return ret;
    	}
    	
    	ListNode node = ret;
    	int addTag = 0;
    	ListNode node1 = l1, node2 = l2;
    	
    	while (node1 != null && node2 != null) {
    		int val  = node1.val + node2.val + addTag; 
    		addTag = val / 10;
    		val = val % 10;
    		
    		node1 = node1.next;
    		node2 = node2.next;
    		
    		node.next = new ListNode(val);
    		node = node.next;
    	}
    	
		while (node1 != null) {
    		int val  = node1.val + addTag; 
    		addTag = val / 10;
    		val = val % 10;
    		
    		node1 = node1.next;
    		node.next = new ListNode(val);
    		node = node.next;
		}
		
		while (node2 != null) {
    		int val  = node2.val + addTag; 
    		addTag = val / 10;
    		val = val % 10;
    		
    		node2 = node2.next;
    		node.next = new ListNode(val);
    		node = node.next;
		}
		
    	if ( addTag == 1) {
    		node.next = new ListNode(1);
    		node = node.next;
    	}
    	
    	return ret.next;
    }
    
    /**
     * leetcode 002 两个数以链表形式保存，相加，返回一个链表形式的和
     * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	 * Output: 7 -> 0 -> 8 
	 * 
	 * TODO：优化为只有一个循环
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    	
    	int exceed = 0;
    	ListNode phead = new ListNode(-1);
    	ListNode pnode = phead;
    	
    	while (l1 != null && l2 != null) {
    		int sum = l1.val + l2.val + exceed;
    		if (sum >= 10) {
    			sum -= 10;
    			exceed = 1;
    		} else exceed = 0;
    		
    		l1 = l1.next;
    		l2 = l2.next;
    		
    		pnode.next = new ListNode(sum);
    		pnode = pnode.next;
    	}
    	
    	while (l1 != null) {
    		int sum = l1.val + exceed;
    		if (sum >= 10) {
    			sum -= 10;
    			exceed = 1;
    		} else exceed = 0;
    		
    		pnode.next = new ListNode(sum);
    		l1 = l1.next;
    	}
    	
    	while (l2 != null) {
    		int sum = l2.val + exceed;
    		if (sum >= 10) {
    			sum -= 10;
    			exceed = 1;
    		} else exceed = 0;
    		
    		pnode.next = new ListNode(sum);
    		l2 = l2.next;
    	}
    	return phead.next;
    }
    
    /**
     * code003 寻找最大不重复字串
     * 思路：DP
     * 最简单的做法，时间复杂度O(n2)
     * 使用DP，时间复杂度降低到O(n) 
     */
    public int lengthOfLongestSubstring(String s) {//abcdcebf
    	if (s == null || s.length() == 0) {
    		return 0;
    	}
    	
    	char[] arr = s.toCharArray();
    	int maxLen = 0, maxStart=0, idx=0, curLen = 0, curStart = 0;
    	int[] hash = new int[256];
    	for (int i = 0; i < hash.length; i++) {
			hash[i] = -1;
		}
    	while (idx < s.length()) {
    		int pos = hash[arr[idx]];
    		if (pos == -1 || pos < curStart) {
    			hash[arr[idx]] = idx;
    			idx++;
    			curLen++;
    		} else {
    			curStart = pos+1;
    			curLen = idx - curStart + 1;
    			hash[arr[idx]] = idx;
    			idx++;
    		}
    		
    		if (maxLen < curLen) {
    			maxLen = curLen;
    			maxStart = curStart;
			}
    	}
    	
    	return maxLen;
    }
    
    /**
     * offer004 两个排序数组中的中位数，要求时间复杂度是O(m+n) 
     * 这道题比较难，要理解其思路，在netease曾经考过
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    	int total = nums1.length + nums2.length;
    	if (total % 2 == 0) {
    		int k1 = findKth(nums1, nums2, total/2);
    		int k2 = findKth(nums1, nums2, total/2+1);
    		return ((double)(k1 + k2)) / 2;
    	} else {
    		return findKth(nums1, nums2, total/2+1);
    	}
    }
    
    public int findKth(int[] nums1, int[] nums2, int k) {
    	
    	if (nums1.length > nums2.length) {
    		return findKth(nums2, nums1, k);
    	}
    	if (nums1.length == 0) {
    		return nums2[k - 1];
    	}
    	if (k == 1) {
    		return Math.min(nums1[0], nums2[0]);
    	}
    	int pa = Math.min(k/2, nums1.length), pb = k - pa;
    	if (nums1[pa-1] < nums2[pb-1]) {
    		int[] arr;
    		if (pa >= nums1.length) {
    			arr = new int[]{};
    		} else {
    			arr = Arrays.copyOfRange(nums1, pa, nums1.length-1);
    		}
    		return findKth(arr, nums2, k-pa);
    	} else if(nums1[pa-1] > nums2[pb-1]) {
    		int[] arr;
    		if (pb >= nums2.length) {
    			arr = new int[]{};
    		} else {
    			arr = Arrays.copyOfRange(nums2, pb, nums2.length-1);
    		}
    		return findKth(nums1, arr, k-pb);
    	} else 
    		return nums1[pa-1];
    }
    
    /**
     * code004 Longest Palindromic Substring
     * https://leetcode.com/problems/longest-palindromic-substring/
     * 最长回文字串，理解思路
     * 
     */
    public String longestPalindrome(String s) {
    	if (s == null || s.isEmpty()) {
    		return null;
    	}
    	String longest = s.substring(0, 1);
    	for (int i = 0; i < s.length(); i++) {
    		String tmp = getPalindrome(s, i, i);
    		if (tmp.length() > longest.length()) {
    			longest = tmp;
    		}
		}
    	
    	for (int i = 0; i < s.length()-1; i++) {
    		String tmp = getPalindrome(s, i, i+1);
    		if (tmp.length() > longest.length()) {
    			longest = tmp;
    		}
		}
    	
    	return longest;
    }
    /**
     * 得到以start和end为开始的最长回文字串
     */
    public String getPalindrome(String s, int start, int end) {
    	
    	while (start>= 0 && end<s.length()) {
    		if (s.charAt(start) != s.charAt(end)) {
    			break;
    		}
    		start--;
    		end++;
    	}
    	return s.substring(start+1, end);//这里要注意边界条件
    }
    
    /**
     * code005 ZigZag Conversion 
     * https://leetcode.com/problems/zigzag-conversion/
     * 
"PAYPALISHIRING"  => 
P   A   H   N
A P L S I I G
Y   I   R
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
	 * 对于这种题目，要有一个基本和规范的思路，我的问题目前就在这里
     */
    public String convert(String s, int numRows) {
    	if (s == null || s.isEmpty()) {
    		return "";
    	}
    	
    }
    
    
    /**
     * 
     * "", "" 这种情况没有考虑到
     *  i+j == haystack.length() 这个需要考虑到
     */
    public int strStr(String haystack, String needle) {
    	if (haystack == null || needle == null) {
    		return -1;
    	}
    	
    	if (needle.length() == 0) {//忘记这种情况了
    		return 0;
    	}
    	
    	for (int i = 0; i < haystack.length(); i++) {
    		for (int j = 0; j <= needle.length(); j++) {
				if (j == needle.length()) return i;
				
				if (i+j == haystack.length()) return -1;
				
				if (needle.charAt(j) != haystack.charAt(i+j)) break;
			}
		}
    	return -1;
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
     * (4 5 7 1 2 3)
     * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
     * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/ 
     * 自己写的
     */
    public int findMin(int[] nums) {
    	if (nums == null || nums.length == 0) {
    		return -1;
    	}
    	
    	int start = 0;
    	int end = nums.length - 1;
    	if (nums[start] <= nums[end]) {
    		return nums[0];
    	}
    	
    	while (start < end) {
    		int midIdx = (start + end)/2;
    		int midVal = nums[midIdx];
    		if (midVal >= nums[0]) { //位于第一个递增序列
    			start = midIdx + 1;
    		} else {
    			if (midVal < nums[midIdx - 1]) {
    				return midVal;
    			} else {
    				end = midIdx - 1;
    			}
    		}
    	}
    	return nums[start];
    }
    
    /**
     * offer043 大整数的乘法 
     * https://leetcode.com/problems/multiply-strings/
     * 思路：模拟整数的乘法来实现
     */
    public String multiply(String num1, String num2) {
    	if (num1 == null || num1.isEmpty()) {
    		return num2;
    	}
    	if (num2 == null || num2.isEmpty()) {
    		return num1;
    	}
    	
    	String n1 = new StringBuilder(num1).reverse().toString();
    	String n2 = new StringBuilder(num2).reverse().toString();
    	
    	int[] arr = new int[n1.length() + n2.length()];
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < n1.length(); i++) {
			for (int j = 0; j < n2.length(); j++) {
				arr[i+j] += (n1.charAt(i)-'0') * (n2.charAt(j)-'0');
			}
		}
    	
    	for (int i = 0; i < arr.length; i++) {
    		int val = arr[i] % 10;
    		int carry = arr[i] / 10;
    		if ((i + 1) < arr.length) {
    			arr[i+1] += carry;
    		}
    		sb.insert(0, val);
		}
    	
    	while (sb.charAt(0) == '0' && sb.length() > 1) {
    		sb.deleteCharAt(0);
    	}
    	
    	return sb.toString();
    }
    
    /**
     * code066
     * https://leetcode.com/problems/plus-one/
     */
    public int[] plusOne(int[] digits) {
    	if (digits == null || digits.length == 0) {
    		return new int[]{0};
    	}
    	
    	int carry = 1;
    	
    	for (int i = digits.length-1; i >= 0; i--) {
    		int val = digits[i] + carry;
    		carry = val / 10;
    		val = val % 10;
    		digits[i] = val;
		}
    	
    	if (carry == 0) {
    		return digits;
    	}
    	
    	int[] newdigits = new int[digits.length+1];
    	for (int i = newdigits.length-1; i > 0; i--) {
			newdigits[i] = digits[i-1];
		}
    	newdigits[0]=1;
    	
    	return newdigits;
    }
    
    /**
     * code067 二进制的加法
     * https://leetcode.com/problems/add-binary/
     * 难点：二进制的位运算，怎么来决定最后返回String的位数
     * 这道题目需要好好学习下别人的写法，让代码更加简洁
     * 1, 111 => 1000
     * ****StringBuilder的用法，可以翻转字符串；String.ValueOf，将int转换为string
     */
    public String addBinary(String a, String b) {
    	if (a == null) {
    		return b;
    	} 
    	if (b == null) {
    		return a;
    	}
    	
    	char[] arra = a.toCharArray();
    	char[] arrb = b.toCharArray();
    	char[] retarr = new char[(arra.length >= arrb.length ? arra.length : arrb.length)+1];
    	int idx = retarr.length-1;
    	int exceed = 0;
    	int i = arra.length-1, j = arrb.length-1;
    	
    	for (; i >= 0 && j >= 0; i--, j--) {
    		int vala = arra[i] - '0';
    		int valb = arrb[j] - '0';
    		int val = vala + valb + exceed;
    		if (val > 3) {
    			return "0";
    		}
    		exceed = val / 2;
    		val = val % 2;
    		retarr[idx--]=(char) (val+'0');
		}
    	
    	for (; i >= 0; i--) {
    		int vala = arra[i] - '0';
    		int val = vala + exceed;
    		if (val > 2) {
    			return "0";
    		}
    		exceed = val / 2;
    		val = val % 2;
    		retarr[idx--]=(char) (val+'0');
    	}
    	
    	for (; j >= 0; j--) {
    		int valb = arrb[j] - '0';
    		int val = valb + exceed;
    		if (val > 2) {
    			return "0";
    		}
    		exceed = val / 2;
    		val = val % 2;
    		retarr[idx--]=(char) (val+'0');
    	}
    	
    	if (exceed > 0) {
    		retarr[idx--] = '1';
    		return new String(retarr, 0, retarr.length);
    	}
    	
    	return new String(retarr, 1, retarr.length - 1);
    }
    
    /**
     * 改良的答案，使用一个循环即可，让代码更加简洁 
     */
    public String addBinary2(String a, String b) {
    	int pa = a.length() - 1;
    	int pb = b.length() - 1;
    	int carry = 0;
    	StringBuilder sb = new StringBuilder();
    	
    	while (pa >= 0 || pb >= 0) {
    		int va = 0;
    		int vb = 0;
    		
    		if (pa >= 0) {
    			va = a.charAt(pa) == '0' ? 0 : 1;
    			pa--;
    		}
    		
    		if (pb >= 0) {
    			vb = b.charAt(pb) == '0' ? 0 : 1;
    			pb--;
    		}
    		
    		int val = va + vb + carry;
    		if (val >= 2) {
    			carry = 1;
    			val = val - 2;
    		}
    		sb.append(String.valueOf(val));
    	}
    	
    	if (carry == 1) {
    		sb.append("1");
    	}
    	
    	return sb.reverse().toString();
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




