package com.sf.jkt.k.algorithm.study.zhaoyun.c06;

public class Fibonacci {

	private static int data[]; // 初始换全部是0

	// 1 1 2 3 5 8 13
	// f(n)=f(n-1)+f(n-2)
	public static int fab(int n) { // 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度 都是：O(2^n)
		if (n <= 2)
			return 1; // 递归的终止条件
		return fab(n - 1) + fab(n - 2); // 继续递归的过程
	}

	public static int fac(int n) { // 求N的阶乘 用普通递归怎么写 5=5*4*3*2*1=> f(n) =
		if (n <= 1)
			return 1;
		return n * fac(n - 1);
	}

	public static int noFab(int n) { // 不用递归 O(n)
		// 循环
		if (n <= 2)
			return 1;
		int a = 1;
		int b = 1;
		int c = 0;
		for (int i = 3; i <= n; i++) {
			c = a + b;
			a = b;
			b = c;
		}
		return c;
	}

	public static int fab2(int n) { // 用数组来做缓存 将为了O(n)，空间也降至为O(n)
		if (n <= 2)
			return 1; // 递归的终止条件
		if (data[n] > 0) {
			return data[n];
		}
		int res = fab2(n - 1) + fab2(n - 2); // 继续递归的过程
		data[n] = res;
		return res;
	}
	
	public static int tailfab(int pre,int res,int n) { // 分析一段代码好坏，有两个指标，时间复杂度和空间复杂度 都是： O(n)
		if (n <= 2)
			return res; // 递归的终止条件
		return tailfab(res, pre + res, n - 1);		//JDK源码
		//参数：
		/**
		 * n 是肯定有的
		 * res 上一次运算出来结果
		 * pre 上上一次运算出来的结果
		 */
	}
	
	public static int tailFac(int n, int res) { // 尾递归 传结果，res就是我们每次保存的结果
		System.out.println(n + ":" + res);	// 这个地方打印出来的值是怎么样的？
		if (n <= 1)
			return res;
		return tailFac(n - 1, n * res);	//倒着算
	}
	
	

	
	public static void main(String[] args) {
		for (int i = 1; i <= 45; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + tailfab(1,1,i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		//tailFac(5, 1);
		
		/*
		 * for (int i = 1; i <= 45; i++) { long start =
		 * System.currentTimeMillis(); System.out.println(i + ":" + fab(i) +
		 * " 所花费的时间为" + (System.currentTimeMillis() - start) + "ms"); }
		 */

		/*for (int i = 1; i <= 45; i++) {
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + noFab(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}
		System.out.println("==================================");
		System.out.println("加了缓存的情况");
		for (int i = 1; i <= 45; i++) {
			data = new int[46];
			long start = System.currentTimeMillis();
			System.out.println(i + ":" + fab2(i) + " 所花费的时间为"
					+ (System.currentTimeMillis() - start) + "ms");
		}*/
	}

}
