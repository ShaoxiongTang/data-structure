package com.mls.adt.graph;

import java.util.HashSet;
import java.util.Set;

import com.mls.adt.graph.GraphDesc.Graph;

public class ShortestPath {

	public static class Dijikstra {
		public static int INFINITY = Integer.MAX_VALUE;

		void initDistance(Graph graph, int src, int[] disArr) {
			for (int i = 0; i < graph.vCount; i++) {
				if (graph.getWeight(src, i) > 0) {
					disArr[i] = graph.getWeight(src, i);
				} else {
					disArr[i] = INFINITY;
				}
			}
		}

		void dijikstra(Graph graph, int[] disArr, int src) {
			Set<Integer> minSet = new HashSet<Integer>();
			minSet.add(src);
			initDistance(graph, src, disArr); // 初始化距离数组
			for (int i = 0; i < graph.getVertexCount(); i++) {
				int n = minVertex(graph, disArr);
				if (n == -1)
					return;
				graph.setMark(n);
				for (int j = graph.next(n, 0); j != -1; j = graph.next(n, j)) {
					if (disArr[j] > (disArr[n] + graph.getWeight(n, j))) {
						disArr[j] = disArr[n] + graph.getWeight(n, j);
					}
				}
			}
		}

		int minVertex(Graph graph, int[] distArr) {
			int n = -1, j = INFINITY;
			for (int i = 0; i < graph.vCount; i++) {
				if (graph.getMark(i) == 0 && distArr[i] < j) {
					n = i;
					j = distArr[i];
				}
			}
			return n;
		}

	}

	public static void main(String[] args) {
		Graph graph = new AdjacencyMetrixGraph(5); // 打印顺序 3 4 5 1 2 0
		int[] disArr = new int[5];
		graph.setEdge(0, 2, 3);
		graph.setEdge(0, 1, 10);
		graph.setEdge(0, 3, 20);
		graph.setEdge(1, 3, 5);
		graph.setEdge(2, 4, 15);
		graph.setEdge(2, 1, 2);
		graph.setEdge(3, 4, 11);
		new Dijikstra().dijikstra(graph, disArr, 0);
		int i = (int) ('A');
		for (int j = i; j < i + 5; j++) {
			System.out.print(((char) j) + "\t");
		}
		System.out.println();
		for (int n = 0; n < 5; n++) {
			System.out.print((disArr[n] == Dijikstra.INFINITY ? -1 : disArr[n] )+ "\t");
		}
	}
}
