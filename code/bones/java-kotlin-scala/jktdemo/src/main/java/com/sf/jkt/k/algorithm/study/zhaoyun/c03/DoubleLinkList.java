package com.sf.jkt.k.algorithm.study.zhaoyun.c03;

public class DoubleLinkList {		// 双向链表

	private DNode head;		//头 
	private DNode tail;		// 尾
	
	DoubleLinkList(){
		head = null;
		tail = null;
	}
	
	public void inserHead(int data){
		DNode newNode = new DNode(data);
		if(head == null){
			tail = newNode;
		}else{
			head.pre = newNode;
			newNode.next = head;
		}
		head = newNode;
	}
	public void deleteHead(){
		if(head == null) return ;		//没有数据
		if(head.next == null){		//就一个点
			tail = null;
		}else{
			head.next.pre = null;	
		}
		head = head.next;
	}
	public void deleteKey(int data){
		DNode current = head;
		while (current.value != data) {
			if (current.next == null) {
				System.out.println("没找到节点");
				return ;
			}
			current = current.next;
		}
		if (current == head) {// 指向下个就表示删除第一个
			deleteHead();
		} else {
			current.pre.next = current.next;
			if(current == tail){		//删除的是尾部
				tail = current.pre;
				current.pre = null;
			}else{
				current.next.pre = current.pre;
			}
		}
	}
}

class DNode{
	
	int value;		//值
	DNode next;		//下一个的指针
	DNode pre;		//指向的是前一个指针

	DNode(int value){
		this.value = value;
		this.next = null;
		this.pre = null;
	}
}
