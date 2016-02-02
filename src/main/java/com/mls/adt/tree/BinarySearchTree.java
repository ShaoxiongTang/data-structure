package com.mls.adt.tree;

import java.util.ArrayDeque;
import java.util.Queue;

// BST 
public class BinarySearchTree<T> {
	BinaryNode<T> root = null;
	NodeComparator<T> cp = null;

	public BinarySearchTree(T val, NodeComparator<T> cp) {
		this.cp = cp;
		root = new PtrBinNode<T>(val);
	}

	void insert(T v) {
		PtrBinNode<T> node = new PtrBinNode<T>(v);
		_insert(root, node);
	}

	boolean isExsits(T val) {
		if (val == null || root == null)
			return false;
		return _isExsits(root, val);
	}

	// 情况1： 删除节点有没有子树-直接删除
	// 情况2： 删除节点有一个左（右）子树，提升子树节点
	// 情况3： 删除节点存在左右两个子树-使用右子树中最小值代替当前节点，删除右子树中最小节点
	void delete(T k) {
		_delete(root, k);
	}
	
	

	void print() { // 层次打印 
		Queue<BinaryNode<T>> q = new ArrayDeque<BinaryNode<T>>();
		q.add(root);
		int l = 0;
		int c = 0;
		while (q.size() > 0) {
			BinaryNode<T> tmp = q.poll();
			if (tmp == null) continue;
			System.out.print(tmp.val()  == null ? "" : (tmp.val() + " "));
//			if (++c == Math.pow(2.0, l)) {
//				System.out.println();
//				l++;
//				c = 0;
//			}
			if (tmp.left() != null) q.add(tmp.left());
			if (tmp.right() != null) q.add(tmp.right());
		}
	}
	
	
	private int comp(T o1, T o2){
		return cp.compare(o1, o2);
	}
	
	private boolean _isExsits(BinaryNode<T> node, T v) {
		if (node == null)
			return false;
		if (comp(v, node.val()) < 0)
			return _isExsits(node.left(), v);
		else if (comp(v, node.val()) > 0)
			return _isExsits(node.right(), v);
		else
			return true;
	}
	
	private void _insert(BinaryNode<T> p, PtrBinNode<T> node) {
		if (comp(node.val(), p.val()) < 0) {
			if (p.left() == null) {
				p.setLeft(node);
				return;
			}
			_insert(p.left(), node);
		} else if (comp(node.val(), p.val()) > 0) {
			if (p.right() == null) {
				p.setRight(node);
				return;
			}
			_insert(p.right(), node);
		} else { // 相同值节点处理： 只出现在右子树中
			// TODO
		}
	}
	
	BinaryNode<T> _delete(BinaryNode<T> node, T k) {
		if (comp(node.val(), k) == 0) {
			BinaryNode<T> min = new PtrBinNode<T>(null);
			deleteMin(node.right(), min);
			node.setVal(min.val());
			return node;
		} else if (comp(node.val(), k) > 0) {
			node.setLeft(_delete(node.left(), k));
		} else {
			node.setRight(_delete(node.right(), k));
		}
		return null;
	}
	
	// 删除最小节点 返回
	private BinaryNode<T> deleteMin(BinaryNode<T> node, BinaryNode<T> min) {
		if (node == null) {
			return null;
		}
		if (node.left() == null) {
			min.setVal(node.val());
			return node.right();
		} else {
			node.setLeft(deleteMin(node.left(), min)); // 左子树的左子节点为空 返回该节点的右子节点，作为当前节点的左子节点
			return node;
		}
	}
	
	public static void main(String[] args) {
//		BinaryNode<Integer> nodeA = new PtrBinNode<Integer>(37);
//		BinaryNode<Integer> nodeB = new PtrBinNode<Integer>(24);
//		BinaryNode<Integer> nodeC = new PtrBinNode<Integer>(42);
//		nodeA.setLeft(nodeB);
//		nodeA.setRight(nodeC);
//		nodeC.setRight(new PtrBinNode<Integer>(32));
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>(37, new NodeComparator<Integer>() {
			public int compare(Integer o1, Integer o2) {
				return o1 > o2 ? 1 : (o1.intValue() < o2 ? -1 : 0);
			}
		});
		bst.insert(40);
		bst.insert(24);
		bst.insert(42);
		bst.insert(32);
		bst.insert(34);
		bst.insert(7);
		bst.insert(2);
		bst.insert(120);
		bst.delete(37);
//		BinaryNode<Integer> min = new PtrBinNode<Integer>(null);
//		BinaryNode<Integer> ret = bst.deleteMin(bst.root , min);
		// System.out.println(bst);
		// System.out.println(bst.isExsits(20));
		bst.print();
	}
}
