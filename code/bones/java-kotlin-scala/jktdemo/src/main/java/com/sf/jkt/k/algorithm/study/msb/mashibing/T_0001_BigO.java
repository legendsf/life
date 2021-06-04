package com.sf.jkt.k.algorithm.study.msb.mashibing;

/**
 * 
 * @author ��ʿ�� 
 * http://www.mashibing.com
 * 
 * ȡ�����±�Ϊ�̶�ֵ����
 * �㷨�����鳤���޹أ���ȡ���ǵڼ���λ��Ҳû��ϵ������ʱ�临�Ӷ����ǳ�ΪO(1)
 * ��ƽ���������ѵ�ʱ�䣬���Ը����鳤���йأ������ϵ�����鳤�ȳ����ȣ����ǳ�ΪO(n)
 * �����n������Ĺ�ģ������˵�����ݵĹ�ģ
 */

public class T_0001_BigO {
	public static void main(String[] args) {

		int[] a = new int[10_000_000];
		for (int m = 0; m < a.length; m++) {
			a[m] = m;
		}
		//�㷨��ʼʱ��
		long before = System.currentTimeMillis();

		for (long i = 0; i < 100000L; i++) {
			a[1000000] = 8;
			//avg(a);
		}

		long after = System.currentTimeMillis();

		System.out.println(after - before);

	}
	
	static int avg(int[] arr) {
		long sum = 0;
		
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		
		return (int)sum/arr.length;
	}
}
