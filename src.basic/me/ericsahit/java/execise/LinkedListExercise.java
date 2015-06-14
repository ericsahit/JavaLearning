package me.ericsahit.java.execise;

import junit.framework.Assert;

import org.junit.Test;

public class LinkedListExercise {
	
	@Test
	public void test() {
		
		LinkNode head = createLinkedList(new int[] {1, 4, 5, 7, 2, 6, 8, 10});
		
		Assert.assertEquals(8, getListNodeCount(head));
		
		//检查LinkList是否有环
		Assert.assertTrue(!hasCircle(head));
		Assert.assertTrue(!hasCircle(createLinkedList(new int[] {3})));
		Assert.assertTrue(!hasCircle(createLinkedList(new int[] {1, 4})));
		
		LinkNode head2 = getNode(-1), node1=getNode(4), node2=getNode(5), node3=getNode(78), node4=getNode(4);
		head2.next=node1;
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node2;
		
		Assert.assertTrue(hasCircle(head2));
		
	}
	
	/**
	 * 判断链表是否存在环 
	 * ****重点在于链表的判断条件
	 */
	public boolean hasCircle(LinkNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		
		LinkNode nodeSlow = head.next;//慢指针每一次走一步
		LinkNode nodeFast = nodeSlow;//快指针每一次走两步
		
		while (nodeFast != null && nodeFast.next != null) {
			
			nodeSlow = nodeSlow.next;
			nodeFast = nodeFast.next.next;

			if (nodeSlow == nodeFast) {
				return true;
			}
		}
		
		
		return false;
	}
	
	public int getListNodeCount(LinkNode head) {
		int len = 0;
		
		if (head == null) {
			return len;
		}
		
		LinkNode node = head.next;
		while (node != null) {//node不为空的时候，正在加一
			len++;
			node = node.next;
		}
		
		return len;
	}
	
	public LinkNode getLastKNode(LinkNode head, int k) {
		if (head == null) {
			
		}
	}
	
	public LinkNode createLinkedList(int[] arr) {
		LinkNode head = new LinkNode(-1);
		LinkNode curNode = head;
		for (int i: arr) {
			LinkNode node = new LinkNode(i);
			curNode.next = node;
			curNode = node;
		}
		return head;
	}
	
	public LinkNode getNode(int i) {
		return new LinkNode(i);
	}
	
}

class LinkNode {
	int val;
	LinkNode next = null;
	
	public LinkNode(int v) {
		val = v;
	}
}
