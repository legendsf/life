package com.sf.jkt.k.algorithm.study.zcy.base.b56.class_05;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Code_04_UnionFind {

	public static class Node {
		// whatever you like
		int value;

		public Node(int value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return "Node{" +
					"value=" + value +
					'}';
		}
	}

	public static class UnionFindSet {
		public HashMap<Node, Node> fatherMap;
		public HashMap<Node, Integer> sizeMap;

		public UnionFindSet() {
			fatherMap = new HashMap<Node, Node>();
			sizeMap = new HashMap<Node, Integer>();
		}

		public void makeSets(List<Node> nodes) {
			fatherMap.clear();
			sizeMap.clear();
			for (Node node : nodes) {
				fatherMap.put(node, node);
				sizeMap.put(node, 1);
			}
		}

		private Node findHead(Node node) {
			Node father = fatherMap.get(node);
			if (father != node) {
				father = findHead(father);
			}
			fatherMap.put(node, father);
			return father;
		}
		
		public boolean isSameSet(Node a, Node b) {
			return findHead(a) == findHead(b);
		}

		public void union(Node a, Node b) {
			if (a == null || b == null) {
				return;
			}
			Node aHead = findHead(a);
			Node bHead = findHead(b);
			if (aHead != bHead) {
				int aSetSize= sizeMap.get(aHead);
				int bSetSize = sizeMap.get(bHead);
				if (aSetSize <= bSetSize) {
					fatherMap.put(aHead, bHead);
					sizeMap.put(bHead, aSetSize + bSetSize);
				} else {
					fatherMap.put(bHead, aHead);
					sizeMap.put(aHead, aSetSize + bSetSize);
				}
			}
		}

	}

	private static class UnionFindSet1{
	    HashMap<Node,Node> fatherMap;
	    HashMap<Node, Integer>sizeMap;

		public UnionFindSet1(List<Node> nodes) {
		   makeSets(nodes);
		}
		private void makeSets(List<Node> nodes){
			fatherMap=new HashMap<>();
			sizeMap=new HashMap<>();
			for (Node node:nodes){
				fatherMap.put(node,node);
				sizeMap.put(node,1);
			}
		}
		private Node findHead(Node node){
			Node father=fatherMap.get(node);
			if (father!=node){
				father=findHead(father);
			}
			fatherMap.put(node,father);
			return father;
		}
		public boolean isSameSet(Node a,Node b){
			return findHead(a)==findHead(b);
		}
		public void union(Node a,Node b){
			if (a==null||b==null){
				return;
			}
			Node aHead=findHead(a);
			Node bHead=findHead(b);
			if (aHead!=bHead){
				int asize=sizeMap.get(aHead);
				int bsize=sizeMap.get(bHead);
				if (asize<=bsize){
					fatherMap.put(aHead,bHead);
					sizeMap.put(bHead,asize+bsize);
				}else {
					fatherMap.put(bHead,aHead);
					sizeMap.put(aHead,asize+bsize);
				}
			}
		}

	}

	public static void test1(){
		Node n1,n2,n3,n4;
		Node[] nodes={n1=new Node(1),n2=new Node(2),n3=new Node(3),n4=new Node(4)};
		List<Node> list= Arrays.asList(nodes);
		UnionFindSet1 us=new UnionFindSet1(list);
		System.out.println(us.findHead(n1));
		System.out.println(us.isSameSet(n1, n2));
		us.union(n1,n2);
		System.out.println(us.isSameSet(n1, n2));
	}

	public static void main(String[] args) {
		test1();
	}

}
