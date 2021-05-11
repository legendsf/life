package com.sf.jkt.k.algorithm.study.zhaoyun.c08;

public class ShellSort {
	public static void main(String[] args) {
		int a[] = { 9, 8, 7, 0, 1, 3, 2 };
		int n = a.length;

		for (int add = n / 2; add >= 1; add /= 2) {
			for (int i = add; i < n; i++) { // 为什么i要从1开始？
				int data = a[i];
				int j = i - add;
				for (; j >= 0; j-= add) {// 从尾到头 1+2+3+4+5+...+n=>
					if (a[j] > data) {
						a[j + add] = a[j]; // 数据往后移动
					} else { // 因为前面已经是排好序的 那么找到一个比他小的就不用找了，因为前面的肯定更小
						break; // O(1) 如果这个break执行的越多 那么我是不是效率就越高?
					}
				}
				a[j + add] = data;
			}
		}

	}
}
