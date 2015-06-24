package me.ericsahit.java.execise;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class MixedExercise {
	
	@Test
	public void test() {
		
		//求一个数的n次方
		Assert.assertTrue(equalDouble(0.0, power(0.0, 1)));
		Assert.assertTrue(equalDouble(0.0, power(0.0, -1)));
		Assert.assertTrue(equalDouble(4.0, power(2.0, 2)));
		Assert.assertTrue(equalDouble(0.25, power(2.0, -2)));
		Assert.assertTrue(equalDouble(1, power(2.0, 0)));
		
		//两个栈，来模拟一个队列
		StackQueue<Integer> queue = new StackQueue<Integer>();
		queue.enqueue(1);
		queue.enqueue(3);
		queue.enqueue(7);
		queue.enqueue(5);
		
		while (!queue.isEmpty()) {
			Print.printnb(queue.dequeue() + ", ");
		}
		
	}
	
	/**
	 * 求一个double类型的整数次方
	 * 思路：写健壮的代码，能满足各个输入
	 * 1.base为0，不为0
	 * 2.exponent为正数，0，负数
	 *  
	 */
	public double power(double base, int exponent) {
		
			if (equalDouble(base, 0.0)) {
				if (exponent == 0)
					throw new IllegalArgumentException("base 0 with exponent 0!");
				return 0.0;
			}
		
		int positiveExp = Math.abs(exponent);
		double ret = powerWithPositiveNumber2(base, positiveExp);
		if (exponent < 0) {
			ret = 1 / ret;
		}
		
		return ret;
	}
	
	private double powerWithPositiveNumber(double base, int exponent) {
		double ret = 1.0;
		int curExp = 0;
		while (curExp < exponent) {
			ret *= base;
			curExp++;
		}
		
		return ret;
	}
	
	/**
	 * 使用公式来n次方 
	 */
	public double powerWithPositiveNumber2(double base, int exponent) {
		if (exponent == 0) 
			return 1;
		else if (exponent == 1)
			return base;
		
		double halfPower = power(base, exponent >> 1);
		double ret = halfPower * halfPower;
		if ((exponent & 0x1) == 1)
			ret *= base;
		
		return ret;
	}
	
	private boolean equalDouble(double a, double b) {
		if (Math.abs(a - b) < 0.0000001) {
			return true;
		}
		return false;
	}
	
	/**
	 * 一个有序的数组，输入一个整数K，找出其中所有两个元素之和为K的元素位置，返回所有符合条件的位置
	 * 怎么应对有重复数值的情况？
	 */
	public List<TupleInt> findTwoElementWithSumK(int[] arr, int k) {
		List<TupleInt> list = new ArrayList<TupleInt>();
		if (arr == null || arr.length == 0)
			return list;
		
		int start=0, end=arr.length-1;
		
		while (start < end) {
			int sum = arr[start]+arr[end];
			if (sum == k) {
				
				int repeatIdx = start;
				list.add(new TupleInt(start, end));
				if (repeatIdx-1 < end && arr[repeatIdx-1] == arr[repeatIdx]) {
					list.add(new TupleInt(repeatIdx-1, end));
					repeatIdx -= 1;
				}
				
				
				
				start++;
				end--;
			} else if (sum < k) {
				start++;
			} else if (sum > k) {
				end--;
			}
		}
		
		
		
		return list;
	}
	
	private static class TupleInt {
		int first, second;
		public TupleInt(int fir, int sec) {
			first = fir;
			second = sec;
		}
	}
}



class StackQueue<T> {
	
	//负责入队列
	Stack<T> stack1 = new Stack<T>();
	//负责出队列，如果为空，则把stack1的所有元素都出队，然后再出队
	Stack<T> stack2 = new Stack<T>();
	
	public boolean isEmpty() {
		return stack1.isEmpty() && stack2.isEmpty();
	}
	
	public T dequeue() {
		
		if (stack2.isEmpty()) {
			while (!stack1.isEmpty()) {
				stack2.push(stack1.pop());
			}
		}
		
		if (!stack2.isEmpty()) {
			return stack2.pop();
		} else {
			return null;
		}
		
	}
	
	public void enqueue(T val) {
		stack1.push(val);
	}
	
}