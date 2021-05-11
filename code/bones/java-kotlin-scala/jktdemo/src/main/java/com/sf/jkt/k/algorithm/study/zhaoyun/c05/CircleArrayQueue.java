package com.sf.jkt.k.algorithm.study.zhaoyun.c05;

public class CircleArrayQueue {

	private int data[];		// 数据
	private int head = 0;		//头
	private int tail = 0;		//尾
	private int n = 0;		//数组的大小 最大的空间
	private int size;		//当前已经存了几个数了
	
	public CircleArrayQueue(int cap){
		data = new int[cap];
		n = cap;
	}
	
	public void push(int m){		//入队列  O(1)		//排序
		if((tail + 1) % n == head){	
			return ; 
		}
		data[tail] = m;	// 1 2 3 4 5 	排序,树形结构
		tail = (tail + 1) % n;		//循环队列 tail=7 8越界了，(7+1)%8==0
	}
	
	public int pop(){		//出队列 1 2 3 4 5 O(1) 很大的空间浪费
		//要判断空，怎么判断空？
		if(isEmpty()) return -1;		//表示空
		int m = data[head];
		head = (head + 1) % n;
		return m;
	}
	
	
	
	public boolean isEmpty(){
		if(head == tail) return true;
		return false;
	}

}
