package com.sf.jkt.k.algorithm.study.zhaoyun.c04;

public class ArrayStack<Item> implements MyStack<Item>{
	
	private Item [] a = (Item[]) new Object[1];		//最好就是开始的时候就设置大小
	private int n = 0;		//大小 初始的元素个数
	
	public ArrayStack(int cap) {
		a = (Item[]) new Object[cap];
	}

	public MyStack<Item> push(Item item) {	//入栈就完成了		//时间复杂度 O(1)
		
		judgeSize();
		a[n++] = item;
		
		return null;
	}
	
	private void judgeSize(){
		if(n >= a.length){		//元素个数已经超出了数组的个数
			resize(2 * a.length);		//10*2*2=40个大小了，我出栈了20个了，只剩下20了吧。
		}else if(n > 0 && n <= a.length / 2){
			resize(a.length / 2);
		}
	}
	
	private void resize(int size){		//扩容O（n）
		Item[] temp = (Item[]) new Object[size];
		for(int i = 0 ; i < n; i ++){
			temp[i] = a[i];
		}
		a = temp;
	}

	public Item pop() {		//出栈 O(1)
		if(isEmpty()){
			return null;
		}
		//item[n--]
		//item[--n]
		Item item = a[--n];	//n不是已经--了么 --n和n-- --n是先把n减了在用，n--先用了在减
		a[n] = null;	//为什么要这一步
		return item;
	}

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return n == 0;
	}

}
