package com.mls.adt.tree;

// 指针实现的二叉树
public class PtrBinNode<T> extends BinaryNode<T> {
	private BinaryNode<T> left;
	private BinaryNode<T> right;
	private T val;

	public PtrBinNode(T val) {
		this.val = val;
	}

	@Override
	T val() {
		return val;
	}

	@Override
	void setVal(T t) {
		val = t;
	}

	@Override
	BinaryNode<T> left() {
		return left;
	}

	@Override
	BinaryNode<T> right() {
		return right;
	}

	@Override
	void setLeft(BinaryNode<T> node) {
		left = node;
	}

	@Override
	void setRight(BinaryNode<T> node) {
		right = node;
	}

	@Override
	boolean isLeaf() {
		return left == null && right == null;
	}
}
