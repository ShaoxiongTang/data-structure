package com.mls.adt.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import com.mls.adt.graph.GraphDesc.Graph;

public class Traversal {
	// 当前节点动作：
	// next不存在：不存在回溯至之前节点
	// 存在：则使用next进行初始遍历
	// 过程中节点：根据next位置继续遍历
	Stack<Integer> stack = new Stack<Integer>();
	Queue<Integer> queue = new ArrayDeque<Integer>();

	public void dfs_recursion(Graph graph, int m) {
		graph.setMark(m);
		int i = 0;
		do {
			i = graph.next(m, i);
			if (i != -1) {
				if (graph.getMark(i) == 0) { // 如果节点未被访问则递归遍历
					graph.setMark(i);
				}
				dfs_recursion(graph, i);
			} else {
				System.out.print(m + " "); // 如果节点已经被访问过，打印当前节点
			}
		} while (i != -1); // 寻找next之后的邻接点
	}

	public void dfs_stack(Graph graph, int m) {
		graph.setMark(m); // 可拓展遍历动作
		stack.push(m);
		int tmp = 0;
		while (stack.size() > 0) {
			int cur = stack.peek(); // 当前节点
			int next = graph.next(cur, tmp); // 寻找当前节点 在tmp值之后的第一个邻接点
			if (next != -1) { // 存在邻接点
				stack.push(next); // 邻接点进栈
				graph.setMark(next); // 遍历标记
				tmp = 0; // 充值已经遍历位置
			} else {
				tmp = stack.pop(); // 用于标记之前节点的遍历位置
				System.out.print(tmp + " ");
			}
		}
	}

	public void bfs_queue(Graph graph, int m) {
		queue.add(m);
		graph.setMark(m);
		while (queue.size() > 0) {
			int cur = queue.poll();
			System.out.print(cur + " ");
			for (int w = graph.next(cur, 0); w != -1; w = graph.next(cur, w)) {
				if (graph.getMark(w) == 0) {
					queue.add(w);
					graph.setMark(w);
				}
			}
		}
	}

	public void bfs_recursion(Graph graph, int m) {
		queue.add(m);
		graph.setMark(m);
		while (queue.size() > 0) {
			int cur = queue.poll();
			System.out.print(cur + " ");
			for (int w = graph.next(cur, 0); w != -1; w = graph.next(cur, w)) {
				if (graph.getMark(w) == 0) {
					queue.add(w);
					graph.setMark(w);
				}
			}
		}
	}

	public static void main(String[] args) {
		Graph graph = new AdjacencyMetrixGraph(6); // 打印顺序 3 4 5 1 2 0
		graph.setEdge(0, 2);
		graph.setEdge(2, 1);
		graph.setEdge(1, 5);
		graph.setEdge(5, 3);
		graph.setEdge(5, 4);
		// graph.print();
		// new Traversal().dfs_recursion(graph, 0);

		Graph graph2 = new AdjacencyMetrixGraph(6); // 打印顺序 0 2 4 1 3 5
		graph2.setEdge(0, 2);
		graph2.setEdge(0, 4);
		graph2.setEdge(1, 5);
		graph2.setEdge(2, 3);
		graph2.setEdge(2, 1);
		graph2.setEdge(3, 5);
		graph2.setEdge(4, 5);
		// new Traversal().bfs_queue(graph2, 0);
	}
}
