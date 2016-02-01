package com.mls.adt.graph;

import com.mls.adt.graph.GraphDesc.Graph;

public class AdjacencyMetrixGraph extends Graph {
	public int[][] graph;
	public int[] mark; // 标记节点是否已经遍历过

	private int edgeCount = 0;

	public AdjacencyMetrixGraph(int val) {
		vCount = val;
		graph = new int[vCount][vCount];
		for (int i = 0; i < vCount; i++) {
			for (int j = 0; j < vCount; j++) {
				graph[i][j] = 0;
			}
		}
		mark = new int[vCount];
		for (int i = 0; i < vCount; i++) {
			mark[i] = 0;
		}
	}

	@Override
	int getVertexCount() {
		return graph.length;
	}

	@Override
	int getEdgeCount() {
		return edgeCount;
	}

	@Override
	int first(int i) {
		for (int j = 0; j < vCount; j++) {
			if (graph[i][j] != 0)
				return j;
		}
		return -1;
	}

	@Override
	int next(int i, int j) {
		for (int t = j + 1; t < vCount; t++) {
			if (graph[i][t] != 0)
				return t;
		}
		return -1;
	}

	@Override
	void setEdge(int v1, int v2, int weight) {
		graph[v1][v2] = weight;
	}

	@Override
	void setEdge(int v1, int v2) {
		graph[v1][v2] = 1;
	}

	public void print() {
		for (int i = 0; i < vCount; i++) {
			for (int j = 0; j < vCount; j++) {
				System.out.print(graph[i][j] + (j == vCount - 1 ? "\n" : " "));
			}
		}
	}

	@Override
	void setMark(int i) {
		mark[i] = 1;
	}

	@Override
	int getMark(int i) {
		return mark[i];
	}

	@Override
	int getWeight(int i, int j) {
		return graph[i][j];
	}
}
