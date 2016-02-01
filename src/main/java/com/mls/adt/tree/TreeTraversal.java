package com.mls.adt.tree;

public class TreeTraversal {

	static void preOrder(BinaryNode<?> node) {
		if (node == null)
			return;
		visit(node);
		preOrder(node.left());
		preOrder(node.right());
	}

	static void midOrder(BinaryNode<?> node) {
		if (node == null)
			return;
		midOrder(node.left());
		visit(node);
		midOrder(node.right());
	}

	static void visit(BinaryNode<?> node) {
		System.out.print(node.val() + " ");
	}

	public static void main(String[] args) {
		BinaryNode<String> nodeA = new PtrBinNode<String>("A");
		BinaryNode<String> nodeB = new PtrBinNode<String>("B");
		nodeB.setRight(new PtrBinNode<String>("D"));
		BinaryNode<String> nodeC = new PtrBinNode<String>("C");
		BinaryNode<String> nodeE = new PtrBinNode<String>("E");
		BinaryNode<String> nodeF = new PtrBinNode<String>("F");
		nodeA.setLeft(nodeB);
		nodeA.setRight(nodeC);
		nodeC.setLeft(nodeE);
		nodeC.setRight(nodeF);
		nodeE.setLeft(new PtrBinNode<String>("G"));
		nodeF.setLeft(new PtrBinNode<String>("H"));
		nodeF.setRight(new PtrBinNode<String>("I"));
		// preOrder(nodeA);
		midOrder(nodeA);
	}
}
