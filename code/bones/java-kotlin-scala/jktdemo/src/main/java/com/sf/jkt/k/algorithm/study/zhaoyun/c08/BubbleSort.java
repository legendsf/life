package com.sf.jkt.k.algorithm.study.zhaoyun.c08;

import java.util.Arrays;

public class BubbleSort {
	public static void main(String[] args) {

		int data[] = { 4, 5, 6, 3, 2, 1 };
		int n = data.length;

		//1 2
		for (int i = 0; i < n - 1; i++) {	//排序的次数
			boolean flag = false;
			for (int j = 0; j < n - 1 - i; j++) {	//具体冒泡 n - 1 - i,6,5,4,3,2,1
				if (data[j] > data[j + 1]) {
					int temp = data[j];	//用了第三个变量，不用第三个变量
					data[j] = data[j + 1];
					data[j + 1] = temp;
					flag = true;
				}
			}
			if(!flag) break;
		}
		System.out.println(Arrays.toString(data));
	}
}
// a:2 b:3
// 3 2 => a:3 b:2
// 用加减
//a = a + b => a = 3+2 =5;
//b = a - b => b = 5-3 =2;
//a = a - b => a = 5-2 =3;