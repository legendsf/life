package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_04;

public class Code_06_IsBalancedTree {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static boolean isBalance(Node head) {
		boolean[] res = new boolean[1];
		res[0] = true;
		getHeight(head, 1, res);
		return res[0];
	}

	public static int getHeight(Node head, int level, boolean[] res) {
		if (head == null) {
			return level;
		}
		int lH = getHeight(head.left, level + 1, res);
		if (!res[0]) {
			return level;
		}
		int rH = getHeight(head.right, level + 1, res);
		if (!res[0]) {
			return level;
		}
		if (Math.abs(lH - rH) > 1) {
			res[0] = false;
		}
		return Math.max(lH, rH);
	}

	public static boolean isBalance1(Node node){
		if (node==null){
			return true;
		}
		int leftHeight=getHeight1(node.left);
		int rightHeight=getHeight1(node.right);
		return isBalance1(node.left) && isBalance1(node.right) && Math.abs(leftHeight-rightHeight)<=1;
	}

	public static int getHeight1(Node node){
		if (node==null){
			return 0;
		}
		return Math.max(getHeight1(node.left),getHeight1(node.right))+1;
	}

	public static boolean isBalance2(Node node){
		if (node==null){
			return true;
		}
		return getHeight2(node)!=-1;
	}

	public static int getHeight2(Node node){
		if (node==null){
			return 0;
		}
		int leftHeight=getHeight2(node.left);
		if (leftHeight==-1){
			return -1;
		}
		int rightHeight=getHeight2(node.right);
		if (rightHeight==-1){
			return -1;
		}
		if (Math.abs(leftHeight-rightHeight)>1){
			return -1;
		}
		return Math.max(leftHeight,rightHeight)+1;
	}
	public static boolean isBalance3(Node node){
		if(node==null){
			return true;
		}
		boolean[]res=new boolean[]{true};
		getHeight3(node,1,res);
		return res[0];
	}
	public static int getHeight3(Node node,int level,boolean[]res){
		if (node==null){
			return level;
		}
		int leftHeight=getHeight3(node.left,level+1,res);
		if (!res[0]){
		    return level;
		}
		int rightHeight=getHeight3(node.right,level+1,res);
		if (!res[0]){
		    return level;
		}
		if (Math.abs(leftHeight-rightHeight)>1){
		    res[0]=false;
		}
		return Math.max(leftHeight,rightHeight);
	}

	public static boolean isBalance4(Node node){
	    if (node==null){
	    	return true;
		}
	    boolean[]res={true};
	    getHeight4(node,1,res);
	    return res[0];
	}
	public static int getHeight4(Node node,int level,boolean[]res){
		if (node==null){
			return level;
		}
		int hl=getHeight4(node.left,level+1,res);
		if (!res[0]){
			return level;
		}
		int hr=getHeight4(node.right,level+1,res);
		if (!res[0]){
			return level;
		}
		if (Math.abs(hl-hr)>1){
			res[0]=false;
		}
		return Math.max(hl, hr);
	}

	public static void main(String[] args) {
		Node head = new Node(1);
		head.left = new Node(2);
		head.right = new Node(3);
		head.left.left = new Node(4);
		head.left.left.left=new Node(8);
		head.left.right = new Node(5);
		head.right.left = new Node(6);
		head.right.right = new Node(7);
		head.right.right.right=new Node(9);
//		head.right.right.right.right=new Node(10);


		System.out.println(isBalance(head));
		System.out.println(isBalance1(head));
		System.out.println(isBalance2(head));
		System.out.println(isBalance3(head));
		System.out.println(isBalance4(head));
		System.out.println(getHeight3(head, 1, new boolean[]{true}));
		System.out.println(getHeight(head, 1, new boolean[]{true}));

	}

}
