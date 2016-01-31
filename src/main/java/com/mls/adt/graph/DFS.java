package com.mls.adt.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import com.mls.adt.graph.GraphDesc.Graph;

public class DFS {
	// 当前节点动作：
	// next不存在：不存在回溯至之前节点
	// 存在：则使用next进行初始遍历
	// 过程中节点：根据next位置继续遍历
	Stack<Integer> stack = new Stack<Integer>();
	Queue<Integer> queue = new ArrayDeque<Integer>();

	public void dfs(Graph graph, int m, int n) { // 写得太尼玛搓了，还是看看别人的吧
		if (m == -1) {
			return;
		}
		if (!stack.contains(m)) {
			stack.push(m);
		}
		int next = graph.nextNeigbor(m, n);
		if (next == m) {
			System.out.print(stack.pop() + " ");
			dfs(graph, stack.size() == 0 ? -1 : stack.peek(), next);
		} else {
			dfs(graph, next, 0);
		}
	}

	public void bfs(Graph graph, int m) { 
		queue.add(m);
		while (queue.size() > 0) {
			int tmp = queue.poll();
			System.out.print(tmp + " ");
			int i = graph.firstNeigbor(tmp);
			if (i != tmp && !queue.contains(i)){
				queue.add(i);
			} else {
				continue;
			}
			for (; tmp != graph.nextNeigbor(tmp, i);) {
				int next = graph.nextNeigbor(tmp, i);
				if (!queue.contains(next)){
					queue.add(graph.nextNeigbor(tmp, i));
				}
				i = next;
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
		// System.out.println(graph.firstNeigbor(0));
		// System.out.println(graph.nextNeigbor(0, 0));
		// graph.print();
		// new DFS().dfs(graph, 0, 0);

		Graph graph2 = new AdjacencyMetrixGraph(6); // 打印顺序 3 4 5 1 2 0
		graph2.setEdge(0, 2);
		graph2.setEdge(0, 4);
		graph2.setEdge(1, 5);
		graph2.setEdge(2, 3);
		graph2.setEdge(2, 1);
		graph2.setEdge(3, 5);
		graph2.setEdge(4, 5);
		new DFS().bfs(graph2, 0);
	}
}
