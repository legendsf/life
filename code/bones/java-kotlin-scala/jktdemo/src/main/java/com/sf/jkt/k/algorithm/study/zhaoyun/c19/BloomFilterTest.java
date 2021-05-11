package com.sf.jkt.k.algorithm.study.zhaoyun.c19;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {
	public static void main(String[] args) {
		int datasize = 100000000;	//����Ҫ���������Ҳ����n
		double fpp = 0.001;		//0.1%	������
		
		BloomFilter<Integer> bloomFilter = BloomFilter.create(Funnels.integerFunnel(), datasize, fpp);
		
		long start = System.currentTimeMillis();
		for(int i = 0 ; i < 100000000 ; i ++) {
			bloomFilter.put(i);
		}
		System.out.println((System.currentTimeMillis() - start) + ":ms");
		
		/*
		 * //��ô������������� int t = 0 ; for(int i = 20000000 ; i < 30000000 ; i++) {
		 * if(bloomFilter.mightContain(i)) { //��ʾ���� t ++; } }
		 * System.out.println("���еĸ���:" + t);
		 */
	}
}
