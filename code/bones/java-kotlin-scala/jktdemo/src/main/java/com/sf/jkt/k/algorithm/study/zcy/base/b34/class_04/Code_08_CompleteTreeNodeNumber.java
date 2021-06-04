package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_04;

public class Code_08_CompleteTreeNodeNumber {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static int nodeNum(Node head) {
		if (head == null) {
			return 0;
		}
		return bs(head, 1, mostLeftLevel(head, 1));
	}

	public static int bs(Node node, int l, int h) {
		if (l == h) {
			return 1;
		}
		if (mostLeftLevel(node.right, l + 1) == h) {
			return (1 << (h - l)) + bs(node.right, l + 1, h);
		} else {
			return (1 << (h - l - 1)) + bs(node.left, l + 1, h);
		}
	}

	public static int mostLeftLevel(Node node, int level) {
		while (node != null) {
			level++;
			node = node.left;
		}
		return level - 1;
	}

	public static int nodeNum1(Node head){//完全二叉树的结点个数
		if(head==null){
			return 0;
		}
		return bs1(head,1,mostLeftLevel1(head,1));
	}
	public static int mostLeftLevel1(Node node,int level){
		while (node!=null){
			level++;
			node=node.left;
		}
		return level-1;
	}
	public static int bs1(Node node,int l,int h){
		if (l==h){
			return 1;
		}
		if (mostLeftLevel1(node.right,l+1)==h){
			return (1<<(h-l))+bs(node.right,l+1,h);
		}else {
			return (1<<(h-l-1))+bs(node.left,l+1,h);
		}
	}

	public static int nodeNum2(Node head){
		if (head==null){
			return 0;
		}
		return  bs2(head,1,mostLeftLevel2(head,1));
	}
	public static int mostLeftLevel2(Node node,int level){
		while (node!=null){
			level++;
			node=node.left;
		}
		return level-1;
	}
	public static int bs2(Node node,int level,int h){
	    if (level==h){
	    	return 1;
		}
	 	if (mostLeftLevel2(node.right,level+1)==h){
	 		return (1<<(h-level))+bs2(node.right,level+1,h);
		}else {
	 		return (1<<(h-level-1))+bs2(node.left,level+1,h);
		}
	}

	public static void test1(){
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		System.out.println(nodeNum(head));
		System.out.println(nodeNum1(head));
		System.out.println(nodeNum2(head));
	}
	public static void test2(){
		Node head=new Node(1);
		head.left=new Node(2);
//		head.right=new Node(3);
		System.out.println(nodeNum2(head));

	}

	public static void main(String[] args) {
		test2();
	}

}
