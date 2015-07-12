package me.ericsahit.java.execise;

import junit.framework.Assert;

import org.junit.Test;

public class OfferExercise {
	
	@Test
	public void test() {
		Assert.assertEquals(kNumberInSortedArray(new int[]{1,2,3,3,5,8,10}, 3), 2);
		Assert.assertEquals(kNumberInSortedArray(new int[]{1,2,3,3,3,8,10}, 3), 3);
		Assert.assertEquals(kNumberInSortedArray(new int[]{1,3,4,4,4,8,10}, 3), 1);
		Assert.assertEquals(kNumberInSortedArray(new int[]{1,2,4,4,4,8,10}, 3), 0);
		Assert.assertEquals(kNumberInSortedArray(new int[]{3,3,3,5,6,8,10,29}, 3), 3);
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
     * 2.
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
    		} else {
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
