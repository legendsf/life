package com.sf.jkt.k.algorithm.study.zhaoyun.c16;

import java.util.Arrays;
import java.util.Random;

public class TopK {

	private int k = 10;
	private int data[] = new int[k];

	public void topK() {
		Random r = new Random();
		long time = System.currentTimeMillis();
		int size = 0;
		boolean init = false;
		for (int i = 0; i < 100000000; i++) {
			int num = r.nextInt(1000000000);
			if (size < k) {
				data[size] = num;
				size++;
			} else {
				if (!init) { // 初始化堆，这里我们只需要初始化一次就可以了
					for (int j = k / 2 - 1; j >= 0; j--) {
						minHeap(data, 0, k);
					}
					init = true;
				}
				if (num > data[0]) { // 小顶堆那么堆顶是最小的，如果当前的数比堆顶大，则替换堆顶，然后做一次堆化
					data[0] = num;
					minHeap(data, 0, k);
				}
			}
		}
		System.out.println("耗时：" + (System.currentTimeMillis() - time) + "ms");
		System.out.println(Arrays.toString(data));
		

	}

	public void minHeap(int data[], int start, int end) { // 构建一个小顶堆 从上往下构建
		int parent = start;
		int son = parent * 2 + 1; // 下标是从0开始的就要加1，从1就不用
		while (son < end) {
			int temp = son;
			// 比较左右节点和父节点的大小
			if (son + 1 < end && data[son] > data[son + 1]) { // 表示右节点比左节点到
				temp = son + 1; // 就要换右节点跟父节点
			}
			// temp表示的是 我们左右节点小的那一个
			if (data[parent] < data[temp])
				return; // 不用交换
			else { // 交换
				int t = data[parent];
				data[parent] = data[temp];
				data[temp] = t;
				parent = temp; // 继续堆化
				son = parent * 2 + 1;
			}
		}
		return;
	}

	public static void main(String[] args) {
		TopK topK = new TopK();
		topK.topK();
	}
}
