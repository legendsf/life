package com.sf.jkt.k.algorithm.study.zhaoyun.c12;

import java.util.Scanner;


class BinaryNode {

	int data;
	BinaryNode left;
	BinaryNode right;
	BinaryNode parent;

	public BinaryNode(int data) {
		this.data = data;
		this.left = null;
		this.right = null;
		this.parent = null;
	}

}

public class BinarySearchTree {

	public BinaryNode find(BinaryNode root, int key) {
		BinaryNode current = root;
		while (current != null) {
			if (key < current.data) {
				current = current.left;
			} else if (key > current.data) {
				current = current.right;
			} else {
				return current;
			}
		}
		return null;
	}

	// ���ڻ�����Ĳ���
	public int getTreeDepth(BinaryNode root) {
		return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
	}

	private void writeArray(BinaryNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
		// ��֤���������Ϊ��
		if (currNode == null)
			return;
		// �Ƚ���ǰ�ڵ㱣�浽��ά������
		res[rowIndex][columnIndex] = String.valueOf(currNode.data);

		// ���㵱ǰλ�����ĵڼ���
		int currLevel = ((rowIndex + 1) / 2);
		// ���������һ�㣬�򷵻�
		if (currLevel == treeDepth)
			return;
		// ���㵱ǰ�е���һ�У�ÿ��Ԫ��֮��ļ������һ�е��������뵱ǰԪ�ص�������֮��ļ����
		int gap = treeDepth - currLevel - 1;

		// ������ӽ����жϣ���������ӣ����¼��Ӧ��"/"������ӵ�ֵ
		if (currNode.left != null) {
			res[rowIndex + 1][columnIndex - gap] = "/";
			writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
		}

		// ���Ҷ��ӽ����жϣ������Ҷ��ӣ����¼��Ӧ��"\"���Ҷ��ӵ�ֵ
		if (currNode.right != null) {
			res[rowIndex + 1][columnIndex + gap] = "\\";
			writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
		}
	}

	public void show(BinaryNode root) {
		if (root == null) {
			System.out.println("EMPTY!");
			return ;
		}
		// �õ��������
		int treeDepth = getTreeDepth(root);

		// ���һ�еĿ��Ϊ2�ģ�n - 1���η���3���ټ�1
		// ��Ϊ������ά����Ŀ��
		int arrayHeight = treeDepth * 2 - 1;
		int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
		// ��һ���ַ����������洢ÿ��λ��Ӧ��ʾ��Ԫ��
		String[][] res = new String[arrayHeight][arrayWidth];
		// ��������г�ʼ����Ĭ��Ϊһ���ո�
		for (int i = 0; i < arrayHeight; i++) {
			for (int j = 0; j < arrayWidth; j++) {
				res[i][j] = " ";
			}
		}

		// �Ӹ��ڵ㿪ʼ���ݹ鴦��������
		writeArray(root, 0, arrayWidth / 2, res, treeDepth);

		// ��ʱ���Ѿ���������Ҫ��ʾ��Ԫ�ش��浽�˶�ά�����У�����ƴ�Ӳ���ӡ����
		for (String[] line : res) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < line.length; i++) {
				sb.append(line[i]);
				if (line[i].length() > 1 && i <= line.length - 1) {
					i += line[i].length() > 4 ? 2 : line[i].length() - 1;
				}
			}
			System.out.println(sb.toString());
		}
	}

	public void insert(BinaryNode root, int data) {
		if (root.data < data) {
			if (root.right != null) {
				insert(root.right, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root;
				root.right = newNode;
				
			}
		} else {
			if (root.left != null) {
				insert(root.left, data);
			} else {
				BinaryNode newNode = new BinaryNode(data);
				newNode.parent = root;
				root.left = newNode;
			}
		}
	}

	public BinaryNode finSuccessor(BinaryNode node) { // ����node�ĺ�̽ڵ�
		if (node.right == null) { // ��ʾû���ұ� �Ǿ�û�к��
			return node;
		}
		BinaryNode cur = node.right;
		BinaryNode pre = node.right; // ��һ������Ŀռ� �������غ�̽ڵ㣬��Ϊ����Ҫ�ҵ�Ϊ�յ�ʱ����ô��ʵ���ص�����һ���ڵ�
		while (cur != null) {
			pre = cur;
			cur = cur.left; // ע���̽ڵ���Ҫ������ң���Ϊ�ұߵĿ϶�����ߵĴ�����Ҫ�ҵ��ǵ�һ���ȸ��ڵ�С�ģ�����ֻ�������
		}
		return pre; // ��Ϊcur����null��ʵ��������Ҫcur����һ���㣬���Ծ���pre������
	}

	public BinaryNode remove(BinaryNode root, int data) { // ɾ��data
		BinaryNode delNode = find(root, data);
		if (delNode == null) {
			System.out.println("Ҫɾ����ֵ��������");
			return root;
		}
		// 1.ɾ���ĵ�û����������
		if (delNode.left == null && delNode.right == null) {
			if (delNode == root) {
				root = null;
			} else if (delNode.parent.data < delNode.data) { // ˵��ɾ���ĵ������ӽڵ�
				delNode.parent.right = null;
			} else {
				delNode.parent.left = null;
			}
		} else if (delNode.left != null && delNode.right != null) { // 2.ɾ���Ľڵ��������ӽڵ�
			BinaryNode successor = finSuccessor(delNode); // ���ҵĺ�̽ڵ�
			// ��̽ڵ��ɾ���ڵ���н��������Ⱥ�̽ڵ����ڵ��ǿ϶�Ϊ�յ�
			successor.left = delNode.left; // ��̵���߱�Ϊɾ�������
			successor.left.parent = successor; // ɾ��������parentָ���̽ڵ�
			// ��������̽ڵ���ұ�
			if (successor.right != null && successor.parent != delNode) { // ��̽ڵ����ұ�,����ʵ�����������3�ĵ�һ��
				successor.right.parent = successor.parent;
				successor.parent.left = successor.right;
				successor.right = delNode.right;
				successor.right.parent = successor;
			}else if(successor.right == null) {	//�����̽ڵ�û���ұ�,����ʵ�������1��û����������
				if(successor.parent != delNode) {		//�����̽ڵ��parent������ɾ���ĵ� ��ô����Ҫ��ɾ������������ֵ����̽ڵ�
					successor.parent.left = null;		//ע��ԭ���ĺ�̽ڵ��ϵ�����Ҫɾ��,�������ѭ��
					successor.right = delNode.right;
					successor.right.parent = successor;
				}
			}
			// �滻�����������Ҫɾ���ڵ���
			if (delNode == root) {
				successor.parent = null;
				root = successor;
				return root;
			}
			successor.parent = delNode.parent;
			if (delNode.data > delNode.parent.data) { // ɾ���ĵ����ұߣ�����������
				delNode.parent.right = successor;
			} else {
				delNode.parent.left = successor;
			}

		} else { // 3.ɾ������һ���ڵ�
			if (delNode.right != null) { // ���ҽڵ�
				if (delNode == root) {
					root = delNode.right;
					return root;
				}
				delNode.right.parent = delNode.parent; // ���ҽڵ��parentָ��ɾ�����parent
				// �������ڵ����������
				if (delNode.data < delNode.parent.data) { // ɾ���ĵ������
					delNode.parent.left = delNode.right;
				} else {
					delNode.parent.right = delNode.right;
				}
			} else {
				if (delNode == root) {
					root = delNode.left;
					return root;
				}
				delNode.left.parent = delNode.parent;
				if (delNode.data < delNode.parent.data) {
					delNode.parent.left = delNode.left;
				} else {
					delNode.parent.right = delNode.left;
				}
			}
		}
		return root;
	}

	public void inOrde(BinaryNode root) {
		if (root != null) {
			inOrde(root.left);
			System.out.print(root.data);
			inOrde(root.right);
		}
	}
	/**
	 * 
	 * ��������
	 * 15
	 * 10
	 * 19
	 * 8
	 * 13
	 * 16
	 * 28
	 * 5
	 * 9
	 * 12
	 * 14
	 * 20
	 * 30
	 * -1
	 * ɾ����15 8 5 10 12 19 16 14 30 9 13 20 28
	 * @param args
	 */

	public static void main(String[] args) {
		BinarySearchTree binarySearchTree = new BinarySearchTree();
		BinaryNode root = null;
		Scanner cin = new Scanner(System.in);
		int t = 1;
		System.out.println("�����������ٶ������ظ����ӽڵ�,�ظ�������������ע��~~");
		System.out.println("��������ڵ�:");
		int rootData = cin.nextInt();
		root = new BinaryNode(rootData);
		System.out.println("�������" + t + "����:����-1��ʾ����");
		while (true) { //
			int data = cin.nextInt();
			if (data == -1)
				break;
			binarySearchTree.insert(root, data);
			t++;
			System.out.println("�������" + t + "����:����-1��ʾ����");
		}
		binarySearchTree.show(root);		//�ҵı���д�Ĵ�ӡ�������νṹ���о����������Ը�������
		System.out.println("ɾ������:");
		while(true) {
			System.out.println("������Ҫɾ���ĵ㣺-1��ʾ����");
			int key = cin.nextInt();
			root = binarySearchTree.remove(root, key);
			binarySearchTree.show(root);
			if(root == null) {
				System.out.println("���Ѿ�û��������~~");
				break;
			}
		}
	}
}
