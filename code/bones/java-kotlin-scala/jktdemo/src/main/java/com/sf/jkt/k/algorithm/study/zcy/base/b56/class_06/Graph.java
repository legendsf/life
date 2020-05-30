package com.sf.jkt.k.algorithm.study.zcy.base.b56.class_06;

import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	public HashMap<Integer,Node> nodes;
	public HashSet<Edge> edges;

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
