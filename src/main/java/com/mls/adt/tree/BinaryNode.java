package com.mls.adt.tree;

// 二叉树数据类型
public abstract class BinaryNode<T> {

	abstract T val(); // 返回二叉树根节点

	abstract void setVal(T t);

	abstract BinaryNode<T> left();

	abstract BinaryNode<T> right();

	abstract void setLeft(BinaryNode<T> node);

	abstract void setRight(BinaryNode<T> node);

	abstract boolean isLeaf();

}
