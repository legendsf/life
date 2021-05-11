package com.sf.jkt.k.algorithm.study.zhaoyun.c05;

public class ArrayQueue {

	private int data[];		// 数据
	private int head = 0;		//头
	private int tail = 0;		//尾
	private int n = 0;		//数组的大小 最大的空间
	private int size;		//当前已经存了几个数了
	
	public ArrayQueue(int cap){
		data = new int[cap];
		n = cap;
	}
	
	public void push(int m){		//入队列  O(1)
		//判断我们这个队列是不是已经满了
		if(tail == n){	//链表,链表是不能使用CPU缓存的
			//在这里才去移动	最好，最坏。O(1),最坏的情况下才是O(n);项目中如何来进行准确的估算呢？平均时间复杂度，n=1000，前999都是O(1) n*2/n,最坏的情况只有一次
			return ; //n-1
		}
		data[tail] = m;	// 1 2 3 4 5 
		tail ++ ;
	}
	
	public int pop(){		//出队列 1 2 3 4 5 O(1) 很大的空间浪费
		//要判断空，怎么判断空？
		if(isEmpty()) return -1;		//表示空
		int m = data[head];
		head ++ ;
		return m;
	}
	
	public int pop2(){		//出队列 1 2 3 4 5 后没次移动，你们说这个时间复杂度：O(n),我们在入队的时候如果没有空间了我们在集中移动一次
		//要判断空，怎么判断空？
		if(isEmpty()) return -1;		//表示空
		int m = data[head];
		head ++ ;
		//数组移动
		return m;
	}
	
	public boolean isEmpty(){
		if(head == tail) return true;
		return false;
	}

}
