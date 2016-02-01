package com.mls.adt.graph;

import java.util.List;

import com.mls.adt.Concept;

@Concept(names = "有向图，无向图，加权图", desc = "")
public class GraphDesc {
	// 简单路径： 途中所有节点构成有向的通路
	// 回路： 某个顶点通过大于等于3的路径长度，回到本身的路径
	// 简单回路： 简单路径是回路
	// 连通子图： 子图中任意节点间可达
	// 有向图记录方式： 邻接矩阵，邻接表

	public static abstract class Graph {
		public int vCount;
		
		public static class Edge {
			int src, dest, weight;

			public Edge() {
				src = -1;
				dest = -1;
				weight = -1;
			}

			public Edge(int src, int dest) {
				this.src = src;
				this.dest = dest;
			}
		}

		public static class Vertex {
			public Object ver;

			public Vertex(Object vertex) {
				ver = vertex;
			}
		}

		abstract int getVertexCount();

		abstract int getEdgeCount();

		abstract int first(int i); // 该节点的第一个邻接点

		abstract int next(int i, int j);// 该节点的下一个邻接点
		
		abstract void setEdge(int v1, int v2, int weight);

		abstract void setEdge(int v1, int v2);

		abstract void print();
		
		abstract void setMark(int i);
		
		abstract int getMark(int i);
		
		abstract int getWeight(int i, int j);
	}

	public static class AdjacencyMatrixGraph {
		public int[] verteies;
		public int[][] edges;
	}

	public static class AdjacencyListGraph {
		public int[] verteies;
		public List<Node> edges;

		public static class Node {
			public Object point;
			public Node next;
		}
	}
}
