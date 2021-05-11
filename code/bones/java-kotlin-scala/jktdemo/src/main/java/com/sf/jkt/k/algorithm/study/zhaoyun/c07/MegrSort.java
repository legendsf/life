package com.sf.jkt.k.algorithm.study.zhaoyun.c07;

import java.util.Arrays;

public class MegrSort {
	public static void main(String[] args) {

		int data[] = { 9, 5, 6, 8, 0, 3, 7, 1 };
		megerSort(data, 0, data.length - 1);
		System.out.println(Arrays.toString(data));
		//JDK里面的排序源码

	}

	public static void megerSort(int data[], int left, int right) { // 数组的两端
		if (left < right) { // 相等了就表示只有一个数了 不用再拆了
			int mid = (left + right) / 2;
			megerSort(data, left, mid);
			megerSort(data, mid + 1, right);
			// 分完了 接下来就要进行合并，也就是我们递归里面归的过程
			meger(data, left, mid, right);
		}
	}

	public static void meger(int data[], int left, int mid, int right) {
		int temp[] = new int[data.length];		//借助一个临时数组用来保存合并的数据
		
		int point1 = left;		//表示的是左边的第一个数的位置
		int point2 = mid + 1;	//表示的是右边的第一个数的位置
		
		int loc = left;		//表示的是我们当前已经到了哪个位置了
		while(point1 <= mid && point2 <= right){
			if(data[point1] < data[point2]){
				temp[loc] = data[point1];
				point1 ++ ;
				loc ++ ;
			}else{
				temp[loc] = data[point2];
				point2 ++;
				loc ++ ;
			}
		}
		//接下来要干嘛呢？合并排序完成 了吗？
		while(point1 <= mid){
			temp[loc ++] = data[point1 ++];
		}
		while(point2 <= right){
			temp[loc ++] = data[point2 ++];
		}
		for(int i = left ; i <= right ; i++){
			data[i] = temp[i];
		}
	}
}
