package com.sf.jkt.k.algorithm.study.zhaoyun.c09;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;

public class QuiklySort {
	public static void main(String[] args) throws Exception {

		//int data[] = { 4, 5, 6, 3, 2, 1 };
		String str = null;
		String fileName = "E:\\workspace\\sort\\200w.txt";
		InputStreamReader isr = new InputStreamReader(new FileInputStream(fileName), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		double data[] = new double[2100002];
		int i = 0;
		while ((str = br.readLine()) != null) {
			data[i++] = Double.valueOf(str);
		}
		System.out.println("���ݶ�ȡ��ϣ�sizeΪ" + i);
		long start = System.currentTimeMillis();
		qSort(data, 0, data.length - 1);
		
		/*File file =new File("E:\\workspace\\sort\\200w-qsort.txt");
		Writer out =new FileWriter(file);
		for(i = 0 ; i< data.length ; i++) {
			out.write(String.valueOf(data[i]) + "\r\n");
		}
		out.close();*/
		System.out.println("�������ĵ�ʱ��Ϊ:" + (System.currentTimeMillis() - start) + "ms");
		//System.out.println(Arrays.toString(data));
	}

	public static void qSort(double data[], int left, int right) {

		double base = data[left];
		int ll = left;
		int rr = right;
		while (ll < rr) {
			while (ll < rr && data[rr] >= base) {
				rr--;
			}
			if (ll < rr) {
				double temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
				ll++;
			}
			while (ll < rr && data[ll] <= base) {
				ll++;
			}
			if (ll < rr) {
				double temp = data[rr];
				data[rr] = data[ll];
				data[ll] = temp;
				rr--;
			}
		}
		if (left < ll - 1)
			qSort(data, left, ll - 1);
		if (ll + 1 < right)
			qSort(data, ll + 1, right);
	}
}
