package org.googlecode.java.thinking.basic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.googlecode.java.thinking.basic.util.Print;
import org.googlecode.java.thinking.basic.util.Range;
import org.junit.Test;

public class HeapSortTest {
	
	
	
	
	
	@Test
	public void test() {
		
		IntHeap heap1 = new IntHeap();
		Random rand = new Random(47);
		for (int i = 0; i < 100; i++) {
			int val = rand.nextInt(100);
			Print.info(val);
			heap1.add(val);
		}
		Print.warn("begin get sorted array: ");
		for (int i = 0; i < 100; i++) {
			Print.print(heap1.getMin());
		}
	}
	
}


/**
 * 小顶堆，用ArrayList表示一个完全二叉树
 * @author hwang
 *
 */
class IntHeap {
	private List<Integer> heap = new ArrayList<Integer>(Arrays.asList(-1));
	
	public void add(int i) {
		heap.add(i);
		
		int k=heap.size()-1;
		
		while (k > 1 && heap.get(k/2) > heap.get(k)) {
			swapListElement(heap, k/2, k);
			k=k/2;
		}
		Print.print("swap position: " + k);
	}
	
	/**
	 * 得到一个堆顶，并且调整堆顶
	 * @return
	 */
	public int getMin() {
		int realSize = heap.size()-1;
		if (realSize <= 1) 
			return -1;
		
		Integer res = heap.get(1);
		
		heap.set(1, heap.remove(realSize)); //这里会有额外的复杂度，remove造成数组的拷贝
		realSize--;
		
		int k = 1;
		int j;
		while (2*k <= realSize) {
			j=2*k;
			if (realSize >= j + 1 && heap.get(j) > heap.get(j+1)) {
				j++;
			}
			swapListElement(heap, k, j);
			k++;
		}
		
		return res;
	}
	private <T> void swapListElement(List<T> list, int src, int dest) {
		T tmp = list.get(dest);
		list.set(dest, list.get(src));
		list.set(src, tmp);
	}
	
}

class IntHeap2 {
	private List<Integer> heap = new LinkedList<Integer>();
}
