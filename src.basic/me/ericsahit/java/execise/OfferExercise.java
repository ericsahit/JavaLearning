package me.ericsahit.java.execise;

import java.util.Arrays;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class OfferExercise {
	
	@Test
	public void test() {
		//k在排序数组中的出现次数
		Assert.assertEquals(kNumberInSortedArray(new int[] {1,2,3,3,5,8,10}, 3), 2);
		Assert.assertEquals(kNumberInSortedArray(new int[] {1,2,3,3,3,8,10}, 3), 3);
		Assert.assertEquals(kNumberInSortedArray(new int[] {1,3,4,4,4,8,10}, 3), 1);
		Assert.assertEquals(kNumberInSortedArray(new int[] {1,2,4,4,4,8,10}, 3), 0);
		Assert.assertEquals(kNumberInSortedArray(new int[] {3,3,3,5,6,8,10,29}, 3), 3);
		
		int[] ret = findTwoAppearOnceNumber(new int[] {2, 4, 3, 6, 3, 2, 5, 5});
		Assert.assertEquals(ret[0], 6);
		Assert.assertEquals(ret[1], 4);
		
		findSeqWithSumA(15);
		
		Print.print('a' - 'a');
		
		int[] array1 = {1, 2, 3, 4, 5};
		System.out.println(Arrays.toString(constructB(array1)));//expected[] = {120, 60, 40, 30, 24};
		
        int[] array2 = {1, 2, 0, 4, 5};
        System.out.println(Arrays.toString(constructB(array2))); // double expected[] = {0, 0, 40, 0, 0};
        int[] array3 = {1, 2, 0, 4, 0};
        System.out.println(Arrays.toString(constructB(array3))); // double expected[] = {0, 0, 0, 0, 0};
        int[] array4 = {1, -2, 3, -4, 5};
        System.out.println(Arrays.toString(constructB(array4))); // double expected[] = {120, -60, 40, -30, 24};
        int[] array5 = {1, -2};
        System.out.println(Arrays.toString(constructB(array5))); // double expected[] = {-2, 1};
        
        
        //offer 3二维递增数组的查找
        int[][] matrix = new int[][] {
		  {1,   3,  5,  7},
		  {10, 11, 16, 20},
		  {23, 30, 34, 50}
        };
        Assert.assertTrue(search2DMatrix(matrix, 3));
        Assert.assertTrue(search2DMatrix(matrix, 50));
        Assert.assertTrue(!search2DMatrix(matrix, 31));
	}
	
	public static void main(String[] args) {
		System.out.println(findLongestNoRepeatedSubString(""));
		System.out.println(findLongestNoRepeatedSubString("abcd"));
		System.out.println(findLongestNoRepeatedSubString("abcdafe"));
		System.out.println(findLongestNoRepeatedSubString("abcdcebf"));
		System.out.println(findLongestNoRepeatedSubString("abcdcaefb"));
	}
	
    public int maxProfit(int[] prices) {
    	return 0;
    }
    
    /**
     * offer
     * 1 2 3 3 5 8 10
     * 1 3 4 4 5 9 10
     * 求排序数组中，数字k出现的次数
     * 例如 3 3 3 5 6 8 10 29和k=3，那么3的次数是3
     * 思路：分别寻找起点和终点
     * 异常情况，k在数组中不存在 
     * 二分法的难点在于：
     * 1.怎么确定终止条件，到底是start<end还是start<=end?本题应该是start<=end
     * 2.趁热打铁，把二分法的相关都掌握好
     * 
     */
    public int kNumberInSortedArray(int[] arr, int k) {
    	if (arr == null || arr.length == 0) {
    		return 0;
    	}
    	
    	int start = 0, end = arr.length - 1;
    	int stidx = -1, endidx = -1;
    	while (start <= end) {
    		int mid = (start+end)/2;
    		int midVal = arr[mid];
    		if (midVal > k) {
    			end = mid - 1;
    		} else if (midVal < k) {
    			start = mid + 1;
    		} else {//这种方法很巧妙，直接判断是否
    			if (mid > 0 && (arr[mid-1] == k)) {
    				end = mid - 1;
    			} else {
    				stidx = mid;
    				break;
    			}
    		}
    	}
    	
    	if (stidx >= 0) {
    		start = 0;//重新设置为开始节点！！又忘记了，总是很粗心
    		end = arr.length - 1;
    		
    		while (start <= end) {
        		int mid = (start+end)/2;
        		int midVal = arr[mid];
        		if (midVal > k) {
        			end = mid - 1;
        		} else if (midVal < k) {
        			start = mid + 1;
        		} else {
        			if (mid < arr.length-1 && (arr[mid+1] == k)) {
        				start = mid + 1;
        			} else {
        				endidx = mid;
        				break;
        			}
        		}
        	}
    		
    		return (endidx-stidx)+1;
    		
    	}
    	
    	return 0;
    	
    }
    
    /**
     * offer 3 搜索二维矩阵
     * leetcode 074
     * 
[
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
     * 查找3，返回3的位置 
     * 
     * 思路：二分查找行，再二分查找列，时间复杂度是O(logn)
     * 查找行时候是二分的变种，找到第一个比k小的数
     */
    public boolean search2DMatrix(int[][] matrix, int k) {
    	
    	if (matrix == null || matrix[0] == null) {
    		return false;
    	}
    	
    	int rows = matrix.length;
    	int cols = matrix[0].length;
    	
    	int start = 0, end = rows, row = -1, col = -1;
    	boolean found = false;
    	while (start <= end) {
    		int mid = (start+end)/2;
    		int midVal = matrix[mid][0];
    		if (midVal == k) {
    			return true;
    		} else if (k < midVal) {
    			end = mid - 1;
    		} else {
    			
    			//像上面的处理方式一样，处理特殊情况，这样
    			if (mid + 1 < rows && k >= matrix[mid+1][0])
    				start = mid + 1;
    			else {
    				row = mid;
    				break;
    			}
    		}
    	}
    	
    	if (row == -1) return false;
    	
    	start = 0;
    	end = cols;
    	
    	while (start <= end) {
    		int mid = (start+end)/2;
    		int midVal = matrix[row][mid];
    		if (midVal == k) {
    			return true;
    		} else if (midVal > k) {
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	return false;
    	
    }
    
    
    
    /**
     * 40.数组里出出现一次的数，数组里只有两个数字出现了一次，其他都是两次，求这两个数
     * 抑或的XOR的应用，自己XOR自己，结果是0
     * 
     * 与http://www.cnblogs.com/wuyuegb2312/p/3139926.html
     * 与给定一个随机排列的32位整数的顺序文件，找出一个不在文件中的32位整数。
     * 这个问题类似。
     * 按照位扫描，分别统计0和1的位数，把较小的那一部门用作下一次递归，
     * 从第32位一直扫描完第0位，必然得到一个不含元素的空集
     * 这个集合对应就是缺失的元素
     */
    public int[] findTwoAppearOnceNumber(int[] arr) {
    	int[] retArr = new int[2];
    	if (arr == null || arr.length <= 1) {
    		return retArr;
    	}
    	int xorResult = 0;
    	for (int i = 0; i < arr.length; i++) {
    		xorResult ^= arr[i];
		}
    	
    	int firstBit1Idx = findFirstBit1(xorResult);
    	
    	int xor0 = 0, xor1 = 0;
    	for (int i = 0; i < arr.length; i++) {
    		if (checkBit1(arr[i], firstBit1Idx)) {
    			xor0 ^= arr[i];
    		} else {
    			xor1 ^= arr[i];
    		}
		}
    	retArr[0] = xor0;
    	retArr[1] = xor1;
    	return retArr;
    }
    
    private int findFirstBit1(int num) {
    	int idx = 0;
    	while ((num & 1) == 0 && idx < 32) {
    		num >>>= 1;
    		idx ++;
    	}
    	return idx;
    }
    
    private boolean checkBit1(int num, int idx) {
    	num >>>= idx;
        return (num & 1) == 1;
    }
    
    /**
     * 41.和为s的连续整数序列
     */
    public void findSeqWithSumA(int sum) {
    	int min = 0;
    	int max = 1;
    	int partSum = 1;
    	
    	while (min < (sum+1)/2) {//这里注意结束的条件，是min<，而不是max<
    		if (partSum == sum) {
    			for (int i = min; i <= max; i++) {
					Print.printnb(i+",");
				}
    			Print.print();
    			partSum -= min;
    			min++;
    			
    		} else if (partSum < sum) { //一定要注意顺序！！！
    			max++;
    			partSum += max;
    		} else { //一定要注意顺序！！！
    			partSum -= min;
    			min ++;
    		}
    	}
    }
    
    /**
     * 找到字符串的最大不重复字串
     * 例如"abcdcaefb", 最大不重复字串是"dcaefb"
     * 时间复杂度O(N)，空间复杂度O(1)
     * 只针对ASCII字符，用一个int[256]数组保存并且更新位置
     */
    public static String findLongestNoRepeatedSubString2(String str) {
    	if (str == null || str.isEmpty()) {
    		return "";
    	}
    	
    	char[] charArr = str.toCharArray();
    	
    	for (char c: charArr) {
    		if (c < 0 || c > 255) {
    			throw new IllegalArgumentException("Input String must only include ASCII char.");
    		}
    	}
    	
    	int[] posArr = new int[256];
    	for (int i = 0; i < posArr.length; i++) {
			posArr[i] = -1;
		}
    	
    	int begin = 0, curLen = 1, maxLen = 1, maxStart = 0;
    	posArr[charArr[0]] = 0;
    	for (int i = 1; i < charArr.length; i++) {
    		int existPos = posArr[charArr[i]];
    		
    		if (existPos < 0) {
    			curLen ++;
    			posArr[charArr[i]] = i;
    		} else {
    			if (existPos >= begin) {
    				curLen = i - existPos; 
    				begin = existPos + 1;
    				posArr[charArr[i]] = i;
    			} else {
    				curLen ++;
    				posArr[charArr[i]] = i;
    			}
    		}
    		
    		if (curLen > maxLen) {
    			maxLen = curLen;
    			maxStart = i - maxLen + 1;
    		}
		}
    	
    	return new String(charArr, maxStart, maxLen);
    }
    
    
    /**
     * 找到字符串的最大不重复字串
     * 例如"abcdcaefb", 最大字串是dcaefb
     * 时间复杂度O(N)，空间复杂度O(1)
     * 只针对ASCII字符，用一个int[256]数组保存字符，索引为字符ASCII码，值为字符在字符串中的位置
     */
    public static String findLongestNoRepeatedSubString(String str) {
    	if (str == null || str.isEmpty()) {
    		return "";
    	}
    	
    	char[] charArr = str.toCharArray();
    	
    	for (char c: charArr) {
    		if (c < 0 || c > 255) {
    			throw new IllegalArgumentException("Input String must only include ASCII char.");
    		}
    	}
    	
    	int[] posArr = new int[256];
    	for (int i = 0; i < posArr.length; i++) {
			posArr[i] = -1;
		}
    	
    	int begin = 0, curLen = 1, maxLen = 1, maxStart = 0;
    	posArr[charArr[0]] = 0;
    	for (int i = 1; i < charArr.length; i++) {
    		int charIdx = charArr[i];
    		
    		if (posArr[charIdx] == -1) {//字符第一次遇到
    			curLen ++;
    			posArr[charIdx] = i;
    		} else {
    			//字符重复遇到，在数组中位置是begin或在其后面，更新begin为重复字符第一次出现的位置后面
    			if (posArr[charIdx] >= begin) { 
    				curLen = i - posArr[charIdx]; 
    				begin = posArr[charIdx] + 1;
    				posArr[charIdx] = i;
    			} else { //字符重复遇到，在数组中位置在begin前面，不需要重复统计
        			curLen ++;
        			posArr[charIdx] = i;
    			}
    		}
    		
    		if (curLen > maxLen) {
    			maxLen = curLen;
    			maxStart = i - maxLen + 1;
    		}
		}
    	
    	return new String(charArr, maxStart, maxLen);
    }
    
    /**
     * 找到字符串的最大不重复字串
     * 例如"abcdcaefb", 最大字串是dcaefb
     * 时间复杂度O(N)，空间复杂度O(1)
     */
    public static String findLongestNoRepeatedSubString3(String str) {
    	if (str == null || str.isEmpty()) {
    		return "";
    	}
    	
    	char[] charArr = str.toCharArray();
    	
    	for (char c: charArr) {
    		if (c < 0 || c > 255) {
    			throw new IllegalArgumentException("Input String must only include ASCII char.");
    		}
    	}
    	
    	int[] posArr = new int[256];
    	for (int i = 0; i < posArr.length; i++) {
			posArr[i] = -1;
		}
    	
    	int begin = 0, end = 0, maxLen = 1, maxStart = 0;
    	posArr[charArr[0]] = 0;
    	for (int i = 1; i < charArr.length; i++) {
    		int charIdx = charArr[i];
    		
    		if (posArr[charIdx] == -1) {//字符第一次遇到
    			end ++;
    			posArr[charIdx] = i;
    		} else {
    			//字符重复遇到，在数组中位置是begin或在其后面，更新begin为重复字符第一次出现的位置后面
    			if (posArr[charIdx] >= begin) { 
    				end = i - posArr[charIdx]; 
    				begin = posArr[charIdx] + 1;
    				posArr[charIdx] = i;
    			} else { //字符重复遇到，在数组中位置在begin前面，不需要重复统计
    				end ++;
        			posArr[charIdx] = i;
    			}
    		}
    		
    		if ((end - begin + 1) > maxLen) {
    			maxLen = (end - begin + 1);
    			maxStart = i - maxLen + 1;
    		}
		}
    	
    	return new String(charArr, maxStart, maxLen);
    }

    /**
     * offer49 字符串转换为正整数 atoi 
     */
    public int stringToInt(String s) {
    	if (s == null || s.isEmpty()) {
    		return -1;
    	}
    	
    	boolean isNeg = false;
    	int startIdx = 0;
    	char[] chars = s.toCharArray();
    	int len = chars.length;
    	boolean hasFlag = false;
    	int res = 0;
    	
    	while (startIdx < len && chars[startIdx] == ' ') {
    		startIdx++;
    	}
    	
    	if (startIdx >= len ) {
    		return -1;
    	}
    	
    	if (chars[startIdx] == '+' || chars[startIdx] == '-') {
    		if (chars[0] == '-') isNeg = true;
    		hasFlag = true;
    		startIdx++;
    	}
    	
    	while (startIdx < len) {
    		char c = chars[startIdx];
    		if (c > '9' || c < '0') {
    			return -1;
    		}
    		res = res * 10 + (c - '0');
    	}
		return res;
    	
    }
    
    /**
     * offer 52 构建乘积数组
     * 给定一个数组A[0,1,…,n-1],请构建一个数组B[0,1,…,n-1],其中B中的元素B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1],不能使用除法
     */
    public int[] constructB(int[] a) {
    	
    	if (a == null || a.length < 2)
    		return null;
    	
    	int[] b = new int[a.length];
    	b[0] = 1;
    	for (int i = 1; i < b.length; i++) {
			b[i] = b[i-1] * a[i-1]; 
		}
    	
    	int tmp = 1;
    	for (int j = b.length - 2; j >= 0; j--) {
			tmp = tmp * a[j + 1];
			b[j] = b[j] * tmp; 
		}
    	
    	return b;
    }
    
    /**
     * offer 53 正则表达式匹配 
     */
    public void Regex(String a, String b) {
    	
    }
    
    
    
    /**
     * 这道题目比较难
     * 求中位数
     */
//    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//    	if (nums1 == null || nums2 == null)
//    		return -1.0;
//    	int len = nums1.length;
//    	
//    	int start1 = 0; 
//    	int start2 = 0;
//    	int end1 = nums1.length - 1;
//    	int end2 = nums2.length - 1;
//    	
//    	int mid1 = (start1+end1)/2;
//    	int mid2 = (start2+end2)/2;
//    	
//    	while (start)
//    	
//    	int m1 = nums1[nums1.length/2];
//    	int m2 = nums2[nums2.length/2];
//    	
//    	if (m1 == m2) {
//    		
//    	}
//    	
//    }
}
