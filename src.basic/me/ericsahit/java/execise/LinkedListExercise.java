package me.ericsahit.java.execise;

import java.util.Stack;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

public class LinkedListExercise {
	
	/**
	 * 链表的难点在于，循环时候确定边界条件，和处理异常的终止条件，需要多练习。
	 */
	
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
		
		//得到倒数第K个节点
		Assert.assertEquals(getNode(6), getLastKNode(head, 3));
		Assert.assertEquals(getNode(1), getLastKNode(head, 8));
		Assert.assertEquals(null, getLastKNode(head, 9));
		Assert.assertEquals(null, getLastKNode(null, 9));
		
		//反转链表
		LinkNode head3 = getNode(-1);
		head3.next = getNode(4);
		head3.next.next = getNode(6);
		
		printList(head);
		printList(reverseLinkedList(head));
		printList(reverseLinkedList(head3));
		
		//取链表的中间节点，如果n是偶数，则会返回(n+1)/2
		head = createLinkedList(new int[] {1, 4, 5, 7, 2, 6, 8, 10});
		Assert.assertEquals(
				getNode(2), 
				getMiddleNode(head)
		);
		
		head = createLinkedList(new int[] {1, 4, 5, 7, 2, 6, 8, 10, 45});
		Assert.assertEquals(
				getNode(2), 
				getMiddleNode(head)
		);
		//测试一个节点的链表
		head = getNode(-1);
		head.next = getNode(5);
		Assert.assertEquals(
				getNode(5), 
				getMiddleNode(head)
		);
		
		//测试两个节点的链表
		Assert.assertEquals(
				getNode(4), 
				getMiddleNode(head3)
		);
		
		head = createLinkedList(new int[] {1, 4, 5, 7, 2, 6, 8, 10, 45});
		Print.print("Original list: ");
		printList(head);
		Print.print("Reverse list: ");
		printReverseList(head.next);
		
		Print.print("Reverse list with stack: ");
		printReverseListWithStack(head);
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
	
	/**
	 * 得到链表节点个数
	 */
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
	
	/**
	 * 反转链表
	 * 思路： 
	 */
	public LinkNode reverseLinkedList(LinkNode head) {
		
		if (head == null) {
			return null;
		}
		
		LinkNode rhead = getNode(-1);
		LinkNode node = head.next;
		LinkNode nodeNext = null;
		while (node != null) {
			nodeNext = node.next;
			node.next = rhead.next;
			rhead.next = node;
			
			node = nodeNext;
		}
		return rhead;
	}
	
	/**
	 * 找到倒数第K个节点
	 * 思路：
	 * 两个指针，第一个先走K步，然后两个指针同时走，第二个走到队尾，第一个指针指向倒数第K个
	 */
	public LinkNode getLastKNode(LinkNode head, int k) {
		if (head == null || k <= 0) {
			return null;
		}
		
		LinkNode nodeK = head.next;
		LinkNode nodeFirst = head.next;
		int idx = 1;
		for (idx = 1; idx < k && nodeK != null; idx++) {//这里是难点，怎么找到第k个节点，要熟练
			nodeK = nodeK.next;
		}
		if (idx < k || nodeK == null) {
			return null;
		}
		
		while (nodeK.next != null) {
			nodeK = nodeK.next;
			nodeFirst = nodeFirst.next;
		}
		
		return nodeFirst;
		
	}
	
	/**
	 * 得到链表的中间节点
	 * 思路：两个指针，一个走两步，一个走一步，第二个到终点的时候，第一个就到中间
	 * 测试用例：
	 * 1.空列表
	 * 2.只有一个元素的列表
	 * 3.奇数和偶数个数的链表
	 * 
	 * ****写的有问题，
	 */
	public LinkNode getMiddleNode(LinkNode head) {
		if (head == null) {
			return null;
		}
		
		LinkNode nodeSlow = head.next;
		LinkNode nodeFast = head.next;
		
		while (nodeFast != null && nodeFast.next != null) {
			nodeSlow = nodeSlow.next;
			nodeFast = nodeFast.next.next;
		}
		
		return nodeSlow;
	}
	
	public void printReverseList(LinkNode head) {
		if (head == null) {
			return;
		}
		printReverseList(head.next);
		Print.printnb(", "+head);
	}
	
	public void printReverseListWithStack(LinkNode head) {
		if (head == null) {
			return;
		}
		Stack<LinkNode> stack = new Stack<LinkNode>();
		LinkNode node = head.next;
		while (node != null) {
			stack.push(node);
			node = node.next;
		}
		
		while (!stack.isEmpty()) {
			Print.printnb(stack.pop()+", ");
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
	
	public void printList(LinkNode head) {
		
		Print.printnb("Linked list: ");
		
		if (head == null) {
			return;
		}
		LinkNode node = head.next;
		while (node != null) {
			Print.printnb(node+", ");
			node = node.next;
		}
	}
	
}

class LinkNode {
	int val;
	LinkNode next = null;
	
	public LinkNode(int v) {
		val = v;
	}
	
	@Override
	public String toString() {
		return Integer.toString(val);
	}
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof LinkNode) {
			return this.val == ((LinkNode)obj).val;
		}
		return false;
	}
	
}
