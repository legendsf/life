package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_07;

import java.util.Arrays;
import java.util.Comparator;

public class Code_05_LowestLexicography {

	public static class MyComparator implements Comparator<String> {
		@Override
		public int compare(String a, String b) {
			return (a + b).compareTo(b + a);
		}
	}

	public static String lowestString(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		// �����µıȽϷ�ʽ����
		Arrays.sort(strs, new MyComparator());
		String res = "";
		for (int i = 0; i < strs.length; i++) {
			res += strs[i];
		}
		return res;
	}

	public static String lowestString1(String[] strs){
		if (strs==null||strs.length<1){
			return "";
		}
		Arrays.sort(strs,(s1,s2)->{
			return (s1+s2).compareTo(s2+s1);
		});
		String res="";
		for (int i=0;i<strs.length;i++){
			res += strs[i];
		}
		return res;
	}

	public static String lowestString2(String[] strs){
		if (strs==null||strs.length<1){
			return "";
		}
		Arrays.sort(strs,(s1,s2)->{
			return (s1+s2).compareTo(s2+s1);
		});
		String res="";
		for (int i=0;i<strs.length;i++){
			res += strs[i];
		}
		return res;
	}

	public static void main(String[] args) {
		String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
		System.out.println(lowestString(strs1));
		System.out.println(lowestString1(strs1));

		String[] strs2 = { "ba", "b" };
		System.out.println(lowestString(strs2));
		System.out.println(lowestString1(strs2));
		System.out.println(lowestString2(strs2));

	}

}
