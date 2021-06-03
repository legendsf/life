package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_08;

import java.util.HashSet;

public class Code_04_Print_All_Permutations {

	public static void printAllPermutations1(String str) {
		char[] chs = str.toCharArray();
		process1(chs, 0);
	}

	public static void process1(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
		}
		for (int j = i; j < chs.length; j++) {
			swap(chs, i, j);
			process1(chs, i + 1);
			swap(chs, i, j);
		}
	}

	public static void printAllPermutations2(String str) {
		char[] chs = str.toCharArray();
		process2(chs, 0);
	}

	public static void process2(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
		}
		HashSet<Character> set = new HashSet<>();
		for (int j = i; j < chs.length; j++) {
			if (!set.contains(chs[j])) {
				set.add(chs[j]);
				swap(chs, i, j);
				process2(chs, i + 1);
				swap(chs, i, j);
			}
		}
	}

	public static void swap(char[] chs, int i, int j) {
		char tmp = chs[i];
		chs[i] = chs[j];
		chs[j] = tmp;
	}

	public static void permute(char[] str,int index){
		if (index==str.length){
			System.out.println(new String(str));
			return;
		}
		for (int j=index;j<str.length;j++){
			swap(str,index,j);
			permute(str,index+1);
			swap(str,index,j);//huisuo
		}
	}

	public static void permute1(char[] str,int index){
		if (index==str.length){
			System.out.println(new String(str));
			return;
		}
		HashSet<Character>set=new HashSet<>();
		for (int j=index;j<str.length;j++){
			if (!set.contains(str[j])){
				set.add(str[j]);
				swap(str,index,j);//第index的位置选择哪个元素
				permute1(str,index+1);
				swap(str,index,j);//huisuo
			}
		}
	}
	public static void printAllSub(char[] str,int index){
		if (index==str.length){
			System.out.println(new String(str));
			return;
		}
		printAllSub(str,index+1);//xuanze
		char temp=str[index];
		str[index]=' ';//huisuo,xuanzekong
		printAllSub(str,index+1);
		str[index]=temp;//huisuo,xuanzefeikong
	}
	public static void printAllSub(char[]str,int index,String res){
		if (index==str.length){
			System.out.println(res);
			return;
		}
		printAllSub(str,index+1,res);
		printAllSub(str,index+1,res+str[index]);
	}


	public static void test1(){
		String test1 = "abc";
		printAllPermutations1(test1);
		System.out.println("======");
		printAllPermutations2(test1);
		System.out.println("======");

		String test2 = "acc";
		printAllPermutations1(test2);
		System.out.println("======");
		printAllPermutations2(test2);
		System.out.println("======");
		permute(test1.toCharArray(),0);
		System.out.println("=====");
		permute(test2.toCharArray(),0);
		System.out.println("=====");
		permute1(test2.toCharArray(),0);
		System.out.println("=====");
		printAllPermutations2(test2);

	}

	public static void test2(){
		String test1="abc",test2="acc";
		printAllSub(test1.toCharArray(),0);
		printAllSub(test1.toCharArray(),0,"");
	}

	public static void main(String[] args) {
		test2();

	}

}
