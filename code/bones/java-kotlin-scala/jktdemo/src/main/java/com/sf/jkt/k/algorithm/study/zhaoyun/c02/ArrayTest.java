package com.sf.jkt.k.algorithm.study.zhaoyun.c02;

public class ArrayTest {
	
	private int size;		//数组的长度
	private int data[];
	private int index;		//当前已经存的数据大小
	
	public ArrayTest(int size){		//数组的初始化过程
		this.size = size;
		data = new int[size];		//分配的内存空间{0,0,0,0,0}
		index = 0;
	}
	
	public void print(){
		System.out.println("index:" + index);
		for(int i = 0 ; i < index ; i++){
			System.out.print(data[i] + " ");
		}
		System.out.println();
	}
	
	public void insert(int loc,int n){		//时间复杂度 O(n);
		if(index ++ < size){
			for(int i = size - 1; i > loc ; i --){	//为什么不能写size 0~size-1 如果loc是0 O(n),O(1)=>O(n)
				data[i] = data[i - 1];	//把数据往后移一个
			}
			data[loc] = n;
		}
		//扩容 会把size*2 0.75 
	}
	public void delete(int loc){	//O(n)
		for(int i = loc ; i < size ; i++){
			if(i != size - 1){		//怕越界所以加一个判断
				data[i] = data[i + 1];
			}else{
				data[i] = 0;			//默认为0 就是没存数据的
			}
		}
		index -- ;
	}
	
	public void update(int loc,int n){//O(1)
		data[loc] = n;
	}
	
	public int get(int loc){		//O(1)
		return data[loc];
	}
	
	
	public static void main(String[] args) {
		//ArrayList
	}
}
