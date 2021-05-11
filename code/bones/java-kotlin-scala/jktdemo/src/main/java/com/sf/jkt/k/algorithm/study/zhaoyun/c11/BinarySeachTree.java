package com.sf.jkt.k.algorithm.study.zhaoyun.c11;

public class BinarySeachTree {
	
	private int color = 0;		//0表示黑，1表示红
	int data;
	BinarySeachTree left;
	BinarySeachTree right;
	
	BinarySeachTree parent;
	
	public BinarySeachTree(int data) {
		this.data = data;
		this.left = null;
		this.parent = null;
		this.color = 1;
		this.right = null;
		//parent.parent	;爷爷
		//parent.parent.left 左边的叔叔
		//parent.left 兄弟姐妹
	}
	//插入的时候每次都是和根结点比较。一直要找到它应该插入的位置。
	//肯定会插在叶子结点。那么其实大家可以看到 插入其实就是查找。 默认root不会为空

	public void insert(BinarySeachTree root,int data) {
		//if(root == null) {}
		if(root.data < data) {	//根节点小 我们要放到右边
			if(root.right == null) {
				root.right = new BinarySeachTree(data);
			}else {
				insert(root.right, data);
			}
		}else {
			if(root.left == null) {
				root.left = new BinarySeachTree(data);
			}else {
				insert(root.left, data);
			}
		}
	}
	
	public void find(BinarySeachTree root,int data) {
		if(root != null) {
			if(root.data < data) {
				find(root.right, data);
			}else if(root.data > data) {
				find(root.left, data);
			}else {
				System.out.println("找到了");
				System.out.println(root.data);
				return ;
			}
		}
	}
	public void pre() {	
		
	}
	public void post() {	
		
	}
	public void in(BinarySeachTree root) {		//中序遍历
		if(root != null) {
			in(root.left);
			System.out.print(root.data + " ");
			in(root.right);
		}
	}
	
	public static void main(String[] args) {
		//快速排序，归并排序，二叉树排序
		int data[] = {0,5,9,1,2,3,10};
		BinarySeachTree root = new BinarySeachTree(data[0]);	//第一个点作为跟结点
		for(int i = 1 ; i < data.length ; i ++) {
			root.insert(root, data[i]);
		}
		System.out.println("中序遍历:");
		root.in(root);
	}
}
