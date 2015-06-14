package me.ericsahit.java.execise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

import junit.framework.Assert;

import org.googlecode.java.thinking.basic.util.Print;
import org.junit.Test;

import sun.rmi.runtime.NewThreadAction;


public class TreeExecise {

/* 
	    1  
	   / \  
	  2   3  
	 / \   \  
	4   5   6
	 \     /
	  7   8
*/  
	public TreeNode createTree() {
		TreeNode<Integer> r1 = new TreeNode<Integer>(1);
		TreeNode<Integer> r2 = new TreeNode<Integer>(2);
		TreeNode<Integer> r3 = new TreeNode<Integer>(3);
		TreeNode<Integer> r4 = new TreeNode<Integer>(4);
		TreeNode<Integer> r5 = new TreeNode<Integer>(5);
		TreeNode<Integer> r6 = new TreeNode<Integer>(6);
		TreeNode<Integer> r7 = new TreeNode<Integer>(7);
		TreeNode<Integer> r8 = new TreeNode<Integer>(8);
		
		r1.left = r2;
		r1.right = r3;
		r2.left = r4;
		r2.right = r5;
		r3.right = r6;
		r4.right = r7;
		r6.left = r8;
		
		return r1;
	}
	
	/* 
	     1  
	   /  \  
	  2    3  
	 / \  / \  
	4   5 10 6
	 \     /
	  7   8
	 */  
	public TreeNode createTree2() {
		TreeNode<Integer> r1 = new TreeNode<Integer>(1);
		TreeNode<Integer> r2 = new TreeNode<Integer>(2);
		TreeNode<Integer> r3 = new TreeNode<Integer>(3);
		TreeNode<Integer> r4 = new TreeNode<Integer>(4);
		TreeNode<Integer> r5 = new TreeNode<Integer>(5);
		TreeNode<Integer> r6 = new TreeNode<Integer>(6);
		TreeNode<Integer> r7 = new TreeNode<Integer>(7);
		TreeNode<Integer> r8 = new TreeNode<Integer>(8);
		TreeNode<Integer> r9 = new TreeNode<Integer>(9);
		TreeNode<Integer> r10 = new TreeNode<Integer>(10);
		
		r1.left = r2;
		r1.right = r3;
		r2.left = r4;
		r2.right = r5;
		r3.left = r10;
		r3.right = r6;
		r4.right = r7;
		r6.left = r8;
		//r8.right = r9;
		
		return r1;
	}
	
	
	TreeNode<Integer> getNode(int val) {
		return new TreeNode<Integer>(val);
	}
	
	@Test
	public void atestTranverse() {
		
		TreeNode<Integer> r1 = createTree();
		
		PrintNode<Integer> printNode = new PrintNode<Integer>();
		
		TreeNode<Integer> root = r1;
		preOrderTranverseRecursive(root, printNode);
		Print.print();
		preOrderTranverse(root, printNode);
		Print.print();
		inOrderTranverseRecursive(root, printNode);
		Print.print();
		inOrderTranverse(root, printNode);
		
		Print.print("\nLevel traverse: ");
		levelTraverse(root, printNode);
		
		
		Print.print("\nFind node path: ");
		Stack<TreeNode<Integer>> paths = new Stack<TreeNode<Integer>>();
		Assert.assertTrue(findNodePath(root, getNode(8), paths));
		for (TreeNode node: paths) {
			Print.printnb(node+", ");
		}
		Print.print("\nFind node path: ");
		paths.clear();
		Assert.assertTrue(findNodePath(root, getNode(5), paths));
		for (TreeNode node: paths) {
			Print.printnb(node+", ");
		}
		
		//求树节点的个数
		Assert.assertEquals(getNodeNum(root), 8);
		
		//求树的深度
		Assert.assertEquals(getTreeDepth(root), 4);
		
		//将二叉树改造为一个双向链表
		root = createTree();
		TreeNode ret = convertBSTtoLinkedList2(root);
		Assert.assertEquals(ret.left, getNode(4));
		Assert.assertEquals(ret.right, getNode(6));
		
		//求K层的节点个数
		root = r1;
		Assert.assertEquals(0, findLevelKNodeNum(root, 5));
		Assert.assertEquals(0, findLevelKNodeNum(null, 5));
		Assert.assertEquals(2, findLevelKNodeNum(root, 4));
		Assert.assertEquals(3, findLevelKNodeNum(root, 3));
		
		//求叶子节点的个数 
		Assert.assertEquals(3, findLeafNodeNum(root));
		
		TreeNode root2 = createTree();
		Assert.assertEquals(true, checkTreeDiff(root, root2));
		Assert.assertEquals(false, checkTreeDiff(root, null));
		Assert.assertEquals(false, checkTreeDiff(root, getNode(1)));
		
		//查看是否平衡二叉树
		Assert.assertEquals(true, isBalancedTree(null, getNode(1)));
		Assert.assertEquals(false, isBalancedTree(createTree(), getNode(1)));
		Assert.assertEquals(true, isBalancedTree(createTree2(), getNode(1)));
		
		//寻找两个节点最近的祖先节点
		Assert.assertEquals(getNode(2), getNearestParent(root, getNode(5), getNode(7)));
		Assert.assertEquals(getNode(1), getNearestParent(root, getNode(8), getNode(7)));
		Assert.assertEquals(null, getNearestParent(root, getNode(5), getNode(18)));
		//这种特殊情况应该怎么处理，2是5的祖先，那么共同祖先应该是1
		//使用迭代处理，共同祖先为2本身
		Assert.assertEquals(getNode(2), getNearestParent(root, getNode(5), getNode(2)));
		
		//查找结点路径
		Print.print("\nFind node path2 for 8: ");
		List<TreeNode> paths2 = new ArrayList<TreeNode>();
		Assert.assertTrue(findNodePath2(root, getNode(8), paths2));
		for (TreeNode node: paths2) {
			Print.printnb(node+", ");
		}
		Print.print("\nFind node path2 for 7: ");
		paths2.clear();
		Assert.assertTrue(findNodePath2(createTree2(), getNode(7), paths2));
		for (TreeNode node: paths2) {
			Print.printnb(node+", ");
		}
		paths2.clear();
		Assert.assertFalse(findNodePath2(root, getNode(11), paths2));
		
		//寻找两个节点最近的祖先节点，非递归的解法
		Assert.assertEquals(getNode(2), getNearestParent2(root, getNode(5), getNode(7)));
		Assert.assertEquals(getNode(1), getNearestParent2(root, getNode(8), getNode(7)));
		Assert.assertEquals(null, getNearestParent2(root, getNode(5), getNode(18)));
		//这种特殊情况应该怎么处理，2是5的祖先，那么共同祖先应该是1
		//使用迭代处理，共同祖先为2本身
		Assert.assertEquals(getNode(2), getNearestParent2(root, getNode(7), getNode(2)));
		
		//重建二叉树
	}
	
	@Test
	public void atest2() {
		//传递了对象的引用，在其中进行修改，外部不会改变
		TreeNode root = new TreeNode<Integer>(7);
		//Print.print(root);
		testChangeRef(root);
		//Print.print(root);
	}
	
	//加final后，引用不能改变。但是可以改变对象的属性
	public void testChangeRef(final TreeNode root) {
		root.val = 6;
		//root = new TreeNode<Integer>(8); 
	}
	
	/**
	 * 求树节点个数
	 */
	public int getNodeNum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return getNodeNum(root.left)+getNodeNum(root.right)+1;
	}
	
	/**
	 * 求树的深度
	 */
	public int getTreeDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int leftDepth = getTreeDepth(root.left);
		int rightDepth = getTreeDepth(root.right);
		
		return leftDepth >= rightDepth ? (leftDepth +1) : (rightDepth+1); 
	}
	
	/**
	 * 求一条到节点的路径
	 * 
	 */
	public <T> boolean findNodePath(TreeNode<T> root, TreeNode<T> node, Stack<TreeNode<T>> paths) {
		if (root == null)
			return false;
		if (root.equals(node)) {
			paths.push(root);
			return true;
		}
		
		paths.push(root);
		boolean found = false;
		found = findNodePath(root.left, node, paths);
		if (!found)
			found = findNodePath(root.right, node, paths);
		if (!found)
			paths.pop();
		
		return found;
	}
	
	/**
	 * 将二叉排序树，变换成有序的双向链表
	 * 要求不能创建新节点，只能调整指针
	 * 
	 * Java不能直接传递对象，改变引用。所以改为返回一个TreeNode节点
	 * 
	 * 思路：递归
	 */
	public <T> TreeNode<T> convertBSTtoLinkedList(TreeNode root, TreeNode first, TreeNode last) {
		TreeNode<T> ret = new TreeNode<T>();
		if (root == null) {
			//first = last = null;
			return ret;
		}
		
		//Print.print("Visit: "+root);
		
		TreeNode leftFirst, leftLast, rightFirst, rightLast;
		leftFirst = leftLast = rightFirst = rightLast = null;
		if (root.left == null) {
			first = root;
		} else {
			TreeNode ret1 = convertBSTtoLinkedList(root.left, leftFirst, leftLast);
			first = ret1.left;
			ret1.right.right = root;
			root.left = ret1.right;
		}
		
		if (root.right == null) {
			last = root;
		} else {
			TreeNode ret2 = convertBSTtoLinkedList(root.right, rightFirst, rightLast);
			last = ret2.right;
			root.right = ret2.left;
			ret2.left.left = root;
		}
		
		ret.left = first;
		ret.right = last;
		return ret;
	}
	
	public <T> TreeNode<T> convertBSTtoLinkedList2(TreeNode root) {
		TreeNode<T> ret = new TreeNode<T>();
		if (root == null) {
			//first = last = null;
			return ret;
		}
		
		TreeNode<T> first = null, last = null;
		
		//Print.print("Visit: "+root);
		
		if (root.left == null) {
			first = root;
		} else {
			TreeNode ret1 = convertBSTtoLinkedList2(root.left);
			first = ret1.left;
			ret1.right.right = root;
			root.left = ret1.right;
		}
		
		if (root.right == null) {
			last = root;
		} else {
			TreeNode ret2 = convertBSTtoLinkedList2(root.right);
			last = ret2.right;
			root.right = ret2.left;
			ret2.left.left = root;
		}
		
		ret.left = first;
		ret.right = last;
		return ret;
	}
	
	/**
	 * 求二叉树第K层节点的个数
	 * 
	 * 思路：递归
	 * 1.如果k<=0，则返回0
	 * 2.如果k=1，则返回1
	 * 3.如果k>1，则返回子树的k-1层的节点个数
	 * 
	 * 注意：root等于null
	 */
	public int findLevelKNodeNum(TreeNode root, int k) {
		if ( root == null || k <= 0) {
			return 0;
		} else if (k == 1) {
			return 1;
		} 
		return findLevelKNodeNum(root.left, k-1) + findLevelKNodeNum(root.right, k-1);
	}
	
	/**
	 * 求叶子节点的个数 
	 * 
	 * 思路：递归。如果左右孩子都为空，则返回1，否则返回左右孩子的叶子树之和
	 */
	public int findLeafNodeNum(TreeNode root) {
		if (root == null) {
			return 0;
		}
		
		if (root.left == null && root.right == null) {
			return 1;
		} else {
			return findLeafNodeNum(root.left)+findLeafNodeNum(root.right);
		}
	}
	
	/**
	 * 判断两棵树结构是否相同
	 */
	public boolean checkTreeDiff(TreeNode root, TreeNode root2) {
		if (root == null) {
			if (root2 == null) {
				return true;
			} else {
				return false;
			}
		}
		
		if (root.equals(root2)) {
			boolean checkLeft = checkTreeDiff(root.left, root2.left);
			boolean checkRight = checkTreeDiff(root.right, root2.right);
			
			return checkLeft && checkRight;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断二叉树是否为平衡二叉树
	 * 思路：
	 * 1.如果二叉树为空，返回真
	 * 2.如果二叉树不为空，如果左子树和右子树都是AVL树并且左子树和右子树高度相差不大于1，返回真，其他返回假
	 */
	public boolean isBalancedTree(TreeNode root, TreeNode<Integer> ret) {
		
		if (root == null) {
			ret.val = 0;
			return true;
		}
		
		TreeNode<Integer> retLeft=getNode(-1), retRight=getNode(-1);
		boolean isLeft = isBalancedTree(root.left, retLeft);
		boolean isRight = isBalancedTree(root.right, retRight);
		
		int leftDepth = retLeft.val;
		int rightDepth = retRight.val;
		ret.val = ( leftDepth >= rightDepth ? leftDepth : rightDepth) + 1;
		if (isLeft && isRight && Math.abs(leftDepth - rightDepth) <= 1) {
			return true;
		}
		Print.print("isBalancedTree visit false: "+root);
		return false;
	}
	
	/**
	 * 得到二叉树的镜像 
	 */
	public TreeNode treeMirror(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		treeMirror(root.left);
		treeMirror(root.right);
		
		TreeNode tmp = root.left;
		root.left = root.right;
		root.right = tmp;
		
		return root;
	}
	
	public boolean findNode(TreeNode root, TreeNode node) {
		if (root == null) {
			return false;
		}
		if (root.equals(node)) {
			return true;
		}
		return findNode(root.left, node) || findNode(root.right, node);
	}
	
	
	/**
	 * 得到两个节点的最近公共祖先
	 * 递归的思路：
	 * 如果两个节点，一个在左子树，一个在右子树，则返回父节点。
	 * 如果都在左子树，则对左子树递归调用
	 * 
	 * 1.如果两个节点分别在根节点的左子树和右子树，则返回根节点
	 * 2.如果两个节点都在左子树，则递归处理左子树；如果两个节点都在右子树，则递归处理右子树
	 * 
	 * ****怎么处理一个节点是另一个节点的祖先的情况？
	 * 目前的处理是返回祖先的那个节点
	 * 
	 * 注意：判断为空的情况
	 */
	public TreeNode getNearestParent(TreeNode root, TreeNode node1, TreeNode node2) {
		
		TreeNode parent = null;
		if (root == null || node1 == null || node2 == null) {
			return parent;
		}
		
		//特殊情况，node1或node2为根节点
		if (root.equals(node1) || root.equals(node2)) {
			return root;
		}
		
		boolean node1InLeft = findNode(root.left, node1);
		boolean node1InRight = findNode(root.right, node1);
		
		boolean node2InLeft = findNode(root.left, node2);
		boolean node2InRight = findNode(root.right, node2);
		
		if (node1InLeft) {
			if (node2InLeft) {
				parent = getNearestParent(root.left, node1, node2);
			} else if (node2InRight) {
				parent = root;
			}
		} else if (node1InRight) {
			if (node2InLeft) {
				parent = root;
			} else if (node2InRight) {
				parent = getNearestParent(root.left, node1, node2);
			} 
		}
		
		return parent;
		
	}
	
	/**
	 * 公共祖先的非迭代解法
	 * 
	 * 先得到两个节点的路径，然后比较路径即可
	 * 
	 * 处理节点在路径上的情况
	 */
	public TreeNode getNearestParent2(TreeNode root, TreeNode node1, TreeNode node2) {
		
		if (root == null || node1 == null || node2 == null) {
			return null;
		}
		
		List<TreeNode> path = new ArrayList<TreeNode>();
		boolean found = findNodePath2(root, node1, path);
		
		List<TreeNode> path2 = new ArrayList<TreeNode>();
		if (found)
			found = findNodePath2(root, node2, path2);
		
		Print.print();
		Print.printnb(node1+" node1 path: ");
		for (TreeNode node: path) {
			Print.printnb(node+", ");
		}
		
		Print.print();
		Print.printnb(node2+" node2 path: ");
		for (TreeNode node: path2) {
			Print.printnb(node+", ");
		}
		
		if (found) {
			TreeNode lastNode = null;
			for (int i = 0; i < path.size() && i < path2.size(); i++) {
				if (path.get(i).equals(path2.get(i))) {
					lastNode = path.get(i);
				} else {
					return lastNode;
				}
			}
			return lastNode;
		}
		
		return null;
	}
	
	/**
	 * 根据节点寻找从跟节点开始的路径 
	 * 如果一个节点是另一个节点的祖先节点呢？
	 */
	public boolean findNodePath2(TreeNode root, TreeNode node, List<TreeNode> list) {
		if (root == null || node == null) {
			return false;
		}
		if (root.equals(node)) {
			list.add(root);
			return true;
		}
		
		list.add(root);
		
		boolean isLeft=false, isRight=false;
		
		isLeft = findNodePath2(root.left, node, list);
		if (!isLeft)
			isRight = findNodePath2(root.right, node, list);
		
		if (!isLeft && !isRight)
			list.remove(list.size()-1);
		
		return isLeft || isRight;
		
	}
	

	
	
	//前序递归
	public <T> void preOrderTranverseRecursive(TreeNode<T> root, Visit<T> visitFunc) {
		if (root != null) {
			visitFunc.visit(root);
			preOrderTranverseRecursive(root.left, visitFunc);
			preOrderTranverseRecursive(root.right, visitFunc);
		}
	}
	
	//前序非递归
	public <T> void preOrderTranverse(TreeNode<T> root, Visit<T> visitFunc) {
		
		if (root == null) return;
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
	
	//中序递归遍历
	public <T> void inOrderTranverseRecursive(TreeNode<T> root, Visit<T> visitFunc) {
		if (root != null) {
			preOrderTranverseRecursive(root.left, visitFunc);
			visitFunc.visit(root);
			preOrderTranverseRecursive(root.right, visitFunc);
		}
	}

	//中序非递归遍历
	private <T> void inOrderTranverse(TreeNode<T> root, Visit<T> visitFunc) {
		Stack<TreeNode<T>> stack = new Stack<TreeNode<T>>();
		
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			} else {
				TreeNode<T> node = stack.pop();
				visitFunc.visit(node);
				
				root = node.right;
			}
		}
	}
	
	public void levelTraverse(TreeNode root, Visit func) {
		if (root == null)
			return;		
		Queue<TreeNode> list = new LinkedList<TreeNode>(); 
		list.add(root);
		while (!list.isEmpty()) {
			TreeNode node = list.poll();
			func.visit(node);
			if (node.left != null)
				list.add(node.left);
			if (node.right != null)
				list.add(node.right);
		}
		
	}

}

class TreeNode<T> {
	public TreeNode<T> left=null, right=null;
	public T val;
	
	public TreeNode() {
		
	}
	
	public TreeNode(T v) {
		val = v;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TreeNode) {
			return this.val.equals(((TreeNode<T>) obj).val);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.val.toString();
	}
}

interface Visit<T> {
	void visit(TreeNode<T> node);
}

class PrintNode<T> implements Visit<T> {

	@Override
	public void visit(TreeNode<T> node) {
		Print.printnb(node+", ");
	}
	
}




