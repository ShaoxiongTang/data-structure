package com.mls.adt.graph;

import com.mls.adt.graph.GraphDesc.Graph;

public class AdjacencyMetrixGraph extends Graph {
	public int[][] graph;
	public int[] mark;

	private int edgeCount = 0;
	private int size = 0;

	public AdjacencyMetrixGraph(int val) {
		this.size = val;
		graph = new int[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				graph[i][j] = 0;
			}
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
	int firstNeigbor(int i) {
		for (int j = 0; j < size; j++) {
			if (graph[i][j] != 0)
				return j;
		}
		return i;
	}

	@Override
	int nextNeigbor(int i, int j) {
		for (int t = j + 1; t < size; t++) {
			if (graph[i][t] != 0)
				return t;
		}
		return i;
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
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(graph[i][j] + (j == size - 1 ? "\n" : " "));
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
}
