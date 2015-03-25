package org.googlecode.java.thinking.basic.algorithm;

import java.util.ArrayList;
import java.util.List;

public class HeapSortTest {
	
	private List<Integer> heap = new ArrayList<Integer>();
	
	public void test() {
		
		
	}
	
	private void addElement()
}


/**
 * 小顶堆，用数组表示一个完全二叉树
 * @author hwang
 *
 */
class IntHeap {
	private List<Integer> heap = new ArrayList<Integer>(1);
	
	private int index=0;
	
	public void add(int i) {
		heap.add(i);
		
		int k=heap.size()-1;
		
		while (k > 1 && heap.get(k/2) > heap.get(k)) {
			Integer tmp = heap.get(k/2);
			heap.set(k/2, heap.get(k));
			heap.set(k, tmp);
			k=k/2;
		}
	}
	
	public int getMin() {
		if (heap.size() < 1) return -1;
		Integer res = heap.get(1);
		
		heap.set(1, heap.remove(heap.size()-1));
		
		int k = 1;
		int j;
		while () {
			j=2*k;
			if (heap.size() >j+1 && heap.get(j) > heap.get(j+1)) {
				j++;
			}
			
		}
		
		return res;
	}
	
}
