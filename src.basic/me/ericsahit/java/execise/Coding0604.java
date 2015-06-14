package me.ericsahit.java.execise;

import java.util.Stack;

import junit.framework.Assert;

import org.junit.Test;

public class Coding0604 {
	
	@Test
	public void test() {
		int[] arr = new int[] {2, 1, 2, 3, 2, 5, 2, 3, 2, 6, 5, 2, 2};
		
		Assert.assertEquals(2, findMostAppearElem(arr));
	}

	private int findMostAppearElem(int[] arr) {
		
		if (arr == null || arr.length == 0) {
			return -1;
		}
		
		int curVal = arr[0];
		int count = 1;
		
		for (int i = 1; i < arr.length; i++) {
			if (curVal == arr[i]) {
				count++;
			} else {
				count--;
				if (count <= 0) {
					curVal = arr[i];
					count = 1;
				}
			}
		}
		
		return curVal;
	}
	
	
	private static class TreeNode<T> {
		public TreeNode<T> left, right;
		public T val;
	}
	
	interface Visit<T> {
		void visit(TreeNode<T> node);
	}
	
	public <T> void preOrderTranverse(TreeNode<T> root, Visit<T> visitFunc) {
		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
		
		TreeNode<T> node = root;
		
		while (node != null || !stack.isEmpty()) {
			if (node != null) {
				visitFunc.visit(node);
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				node = node.right;
			}
		}
	}
	
	//中序遍历
	private <T> void inOrderTranverse(TreeNode<T> root, Visit<T> visitFunc) {
		Stack<TreeNode<Integer>> stack = new Stack<TreeNode<Integer>>();
		
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.add(root);
				root = root.left;
			} else {
				TreeNode<Integer> node = stack.pop();
				visitFunc.visit(node);
				
				root = node.right;
			}
		}
	}
}
