package com.sf.jkt.k.algorithm.study.zhaoyun.c01;

public class BigO {
	public static void main(String[] args) {
		int a = 1;		//1次 O(1)
		for(int i = 0 ;i < 3;i++){//这里会运行几次？4次 在第4次的时候结束 跳出 i=3 (0 1 2 3)
			a = a + 1;			//这里运行几次？ 3次 O(1)? n+1 n 1 O(3)? => O(1)
		}
		int n = Integer.MAX_VALUE;		//表示n是未知
		
		int i = 1;
		for(int j = 0 ; j < n ;j++){
			while ( i <= n){
				i = i * 2;
			}
		}
		while( i <= n){
			 i = i * 3;		//O(logn)
		}
		//i的值：2 4 8 16 32,=》2^0,2^1,2^2,2^3,.....2^n
		//===> 2^x=n =>求出x就是我们运行的次数 => x=log2n =>计算机忽略掉常数 => x = logn =>O(logn)
		//二分查找 为什么是logn的算法？
		//1~100 找69这个数
		//50:(1+100)/2 = 50
		
		for(i = 0 ; i < n;i++){
			a = a +1;			//运行了多少次？O(n) n一定是一个未知的，如果n是已知6的
		}
		
		for(i = 0 ; i < n;i++){	// 乘法 n次
			for(int j = 0 ; j < n ;j ++){		//n次
				a = a +1;			//运行了多少次？		O(n^2)
			}
		}
		
		for(i = 0 ; i < n;i++){	// 乘法 n次
			for(int j = i ; j < n ;j ++){		//n次 
				a = a +1;			//运行了多少次？	n*(n+1)/2 => O(n^2); => (n^2+n)/2 => 注意有个规律，有加减法的时候，找次数最高的那个
				
			}
		}
		/*
		 * 外面的循环次数是确定的 O(n) n次，1 2 3 4 。。。n
		 * 
		 * i=n 运行1次
		 * i=n-1 运行2次
		 * .
		 * .
		 * .
		 * i=1 运行n次
		 * 
		 * 1,2,3 …… n次 最后里面这层要运行多少次？1+2+3+……+n=n*(n+1)/2 => 
		 * 
		 */
		/*int data[100];
		List<>; list.add();
		Map put
		Set add
		Queue*/
		
		
	}
}
