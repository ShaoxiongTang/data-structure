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
		inner_insert(root, node);
	}

	private void inner_insert(BinaryNode<T> p, PtrBinNode<T> node) {
		if (comp(node.val(), p.val()) < 0) {
			if (p.left() == null) {
				p.setLeft(node);
				return;
			}
			inner_insert(p.left(), node);
		} else if (comp(node.val(), p.val()) > 0) {
			if (p.right() == null) {
				p.setRight(node);
				return;
			}
			inner_insert(p.right(), node);
		} else { // 相同值节点处理： 只出现在右子树中
			// TODO
		}
	}

	boolean isExsits(T val) {
		if (val == null || root == null)
			return false;
		return _isExsits(root, val);
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
	
	void delete(BinaryNode<T> node) {

	}

	void print() { // 层次打印
		Queue<BinaryNode<T>> q = new ArrayDeque<BinaryNode<T>>();
		q.add(root);
		int l = 0;
		int c = 0;
		while (q.size() > 0) {
			BinaryNode<T> tmp = q.poll();
			if (tmp == null) continue;
			System.out.print(tmp.val() + " ");
			if (++c == Math.pow(2.0, l)) {
				System.out.println();
				l++;
				c = 0;
			}
			if (tmp.left() != null) q.add(tmp.left());
			if (tmp.right() != null) q.add(tmp.right());
		}
	}
	
	
	private int comp(T o1, T o2){
		return cp.compare(o1, o2);
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
		bst.insert(24);
		bst.insert(42);
		bst.insert(32);
		bst.insert(7);
		System.out.println(bst.isExsits(20));
		// bst.print();
	}
}
