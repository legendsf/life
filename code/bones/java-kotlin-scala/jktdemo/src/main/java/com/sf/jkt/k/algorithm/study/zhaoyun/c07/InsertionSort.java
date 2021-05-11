package com.sf.jkt.k.algorithm.study.zhaoyun.c07;

public class InsertionSort {

	/**
	 * 
	 1.将数组分成已排序段和未排序段。初始化时已排序端只有一个元素 
	 2.到未排序段取元素插入到已排序段，并保证插入后仍然有序
	 3.重复执行上述操作，直到未排序段元素全部加完。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int a[] = { 9, 8, 7, 0, 1, 3, 2 };
		int n = a.length;
		//这里面会有几层循环 2
		//时间复杂度：n^2
		//最好的情况：什么情况下：O(n); O(1)
		//for(){		//分段
		for(int i = 1 ; i < n;i++){		//为什么i要从1开始？ 第一个不用排序，我们就把数组从i分开，0~I的认为已经排好序
			int data = a[i];
			int j = i -1;
			for(;j>=0;j--){//从尾到头 1+2+3+4+5+...+n=>
				if(a[j] > data){
					a[j+1] = a[j];		// 数据往后移动
				}else{	//因为前面已经是排好序的 那么找到一个比他小的就不用找了，因为前面的肯定更小
					break; //O(1)		如果这个break执行的越多 那么我是不是效率就越高?
				}
			}	
			a[j+1] = data;
			System.out.print("第" +i +"次的排序结果为：");
			for(j = 0 ; j < n;j++){
				System.out.print(a[j]+" ");
			}
			System.out.println();
		}
	//	}
		
	}

}
