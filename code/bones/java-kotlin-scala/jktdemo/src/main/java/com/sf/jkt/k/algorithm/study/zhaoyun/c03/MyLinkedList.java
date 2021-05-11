package com.sf.jkt.k.algorithm.study.zhaoyun.c03;

public class MyLinkedList {
	
	private ListNode head;
	private int size = 0;		//
	
	public void insertHead(int data){		//插入链表的头部		data就是插入的数据
		ListNode newNode = new ListNode(data);
		//如果原来就有数据呢？
		newNode.next = head;		//栈内存的引用
		head = newNode;
		
		//插入O(1)
	}
	
	public void insertNth(int data,int position){		//插入链表的中间 假设定义在第N个插入 O(n)
		if(position == 0) {		//这个表示插入在头部了
			insertHead(data);
		}else{
			ListNode cur = head;
			for(int i = 1; i < position ; i++){
				cur = cur.next;		//一直往后遍历   p=p->next;  ->是c++里面的往后找指针
			}
			ListNode newNode = new ListNode(data);
			//
			newNode.next = cur.next;		//新加的点指向后面 保证不断链
			cur.next = newNode;			//把当前的点指向新加的点
		}
	}
	/*int a = 1;
	int b = a;
	int a = 2;*/
	
	public void deleteHead(){//O(1)
		head = head.next;
	}
	
	public void deleteNth(int position){//O(n)
		if(position == 0) {
			deleteHead();
		}else{
			ListNode cur = head;
			for(int i = 1; i < position ; i ++){
				cur = cur.next;
			}
			cur.next = cur.next.next; //cur.next 表示的是删除的点，后一个next就是我们要指向的
		}
	}
	
	public void find(int data){//O(n)
		ListNode cur = head;
		while(cur != null){
			if(cur.value == data) break;
			cur = cur.next;
		}
	}
	
	public void print(){
		ListNode cur = head;
		while(cur != null){
			System.out.print(cur.value + " ");
			cur = cur.next;
		}
		System.out.println();
	}
	public static void main(String[] args) {
		MyLinkedList myList = new MyLinkedList();
		myList.insertHead(5);
		myList.insertHead(7);
		myList.insertHead(10);
		myList.print(); // 10 -> 7 -> 5
		myList.deleteNth(0);
		myList.print(); // 7 -> 5
		myList.deleteHead();
		myList.print(); // 5
		myList.insertNth(11, 1);
		myList.print(); // 5 -> 11
		myList.deleteNth(1);
		myList.print(); // 5
	}
}

class ListNode{
	
	int value;		//值
	ListNode next;	//下一个的指针

	ListNode(int value){
		this.value = value;
		this.next = null;
	}
}
