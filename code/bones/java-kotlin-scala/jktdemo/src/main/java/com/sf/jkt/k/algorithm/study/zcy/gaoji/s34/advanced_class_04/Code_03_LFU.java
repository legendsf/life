package com.sf.jkt.k.algorithm.study.zcy.gaoji.s34.advanced_class_04;

import java.util.HashMap;

public class Code_03_LFU {

	public static class Node {
		public Integer key;
		public Integer value;
		public Integer times;
		public Node up;
		public Node down;

		public Node(int key, int value, int times) {
			this.key = key;
			this.value = value;
			this.times = times;
		}
	}

	public static class LFUCache {

		public static class NodeList {
			public Node head;
			public Node tail;
			public NodeList last;
			public NodeList next;

			public NodeList(Node node) {
				head = node;
				tail = node;
			}

			public void addNodeFromHead(Node newHead) {
				newHead.down = head;
				head.up = newHead;
				head = newHead;
			}

			public boolean isEmpty() {
				return head == null;
			}

			public void deleteNode(Node node) {
				if (head == tail) {
					head = null;
					tail = null;
				} else {
					if (node == head) {
						head = node.down;
						head.up = null;
					} else if (node == tail) {
						tail = node.up;
						tail.down = null;
					} else {
						node.up.down = node.down;
						node.down.up = node.up;
					}
				}
				node.up = null;
				node.down = null;
			}
		}

		private int capacity;
		private int size;
		private HashMap<Integer, Node> records;//key,Value
		private HashMap<Node, NodeList> heads;//一个node来自哪个nodeList
		private NodeList headList;

		public LFUCache(int capacity) {
			this.capacity = capacity;
			this.size = 0;
			this.records = new HashMap<>();
			this.heads = new HashMap<>();
			headList = null;
		}

		public void set(int key, int value) {
			if (records.containsKey(key)) {//挂到下一个词频的NodeList
				Node node = records.get(key);
				node.value = value;
				node.times++;
				NodeList curNodeList = heads.get(node);
				move(node, curNodeList);
			} else {
				if (size == capacity) {//头部的nodeList的词频是最小的，头部的down的node是最久未访问的
					Node node = headList.tail;
					headList.deleteNode(node);
					modifyHeadList(headList);
					records.remove(node.key);
					heads.remove(node);
					size--;
				}
				Node node = new Node(key, value, 1);
				if (headList == null) {//没有词频为1的则新建nodeList
					headList = new NodeList(node);
				} else {
					if (headList.head.times.equals(node.times)) {//词频等于1则挂到head
						headList.addNodeFromHead(node);
					} else {//没有词频为1的，词频有2 3没有1，则要新建出1，再往下挂载
						NodeList newList = new NodeList(node);
						newList.next = headList;
						headList.last = newList;
						headList = newList;
					}
				}
				records.put(key, node);//记录
				heads.put(node, headList);//node属于哪个nodeList
				size++;
			}
		}

		/***
		 * 原来来自于老的nodeList现在要挂到新的上面去
		 *
		 * @param node
		 * @param oldNodeList
		 */
		private void move(Node node, NodeList oldNodeList) {
			oldNodeList.deleteNode(node);
			NodeList preList = modifyHeadList(oldNodeList) ? oldNodeList.last
					: oldNodeList;//判断是不是老链表都没了，没了要删除，新链表的前一个链表是3 还是 4呢
			NodeList nextList = oldNodeList.next;
			if (nextList == null) {//已经是最高词频，则需要再新建nodeList然后挂载上去
				NodeList newList = new NodeList(node);
				if (preList != null) {
					preList.next = newList;
				}
				newList.last = preList;
				if (headList == null) {
					headList = newList;
				}
				heads.put(node, newList);
			} else {
				//不是最高词频的
				if (nextList.head.times.equals(node.times)) {//下一个已经找到对应词频的nodeList
					nextList.addNodeFromHead(node);
					heads.put(node, nextList);
				} else {
				    //下一个词频不等于那么必然大于当前词频，所以要新建当前词频的nodeList，然后前后重连
					NodeList newList = new NodeList(node);
					if (preList != null) {
						preList.next = newList;
					}
					newList.last = preList;
					newList.next = nextList;
					nextList.last = newList;
					if (headList == nextList) {
						headList = newList;
					}
					heads.put(node, newList);
				}
			}
		}

		// return whether delete this head
		private boolean modifyHeadList(NodeList nodeList) {
			//如果不为空则不用东
			if (nodeList.isEmpty()) {
				if (headList == nodeList) {//是否是headList头部
					headList = nodeList.next;
					if (headList != null) {
						headList.last = null;
					}
				} else {//前后左右连上
					nodeList.last.next = nodeList.next;
					if (nodeList.next != null) {
						nodeList.next.last = nodeList.last;
					}
				}
				return true;
			}
			return false;
		}

		public int get(int key) {
			if (!records.containsKey(key)) {
				System.out.println("null");
				return -1;
			}
			Node node = records.get(key);
			node.times++;
			NodeList curNodeList = heads.get(node);
			move(node, curNodeList);//挂载到词频加一的位置
			System.out.println(node.value);
			return node.value;
		}

	}

	/**
	 1
	 null
	 3
	 null
	 3
	 4
	 */
	public static void test1(){
		LFUCache cache=new LFUCache(2);
		cache.set(1,1);
		cache.set(2,2);
		cache.get(1);
		cache.set(3,3);
		cache.get(2);
		cache.get(3);
		cache.set(4,4);
		cache.get(1);
		cache.get(3);
		cache.get(4);

	}

	public static void main(String[] args) {
		test1();
	}
}