package me.ericsahit.java.execise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
     * 思路，按照螺旋顺序来输出
     */
    public List<Integer> spiralOrder(int[][] matrix) {
    	
    	return null;
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
}

 class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
     public String toString() {return start + ", " + end;}
 }
