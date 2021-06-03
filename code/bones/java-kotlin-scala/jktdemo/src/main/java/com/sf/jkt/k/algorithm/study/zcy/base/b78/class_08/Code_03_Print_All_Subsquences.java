package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_08;

import java.util.ArrayList;
import java.util.List;

public class Code_03_Print_All_Subsquences {

	public static void printAllSubsquence(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0);
	}

	public static void process(char[] chs, int i) {
		if (i == chs.length) {
			System.out.println(String.valueOf(chs));
			return;
		}
		process(chs, i + 1);
		char tmp = chs[i];
		chs[i] = ' ';
		process(chs, i + 1);
		chs[i] = tmp;
	}
	
	public static void function(String str) {
		char[] chs = str.toCharArray();
		process(chs, 0, new ArrayList<Character>());
	}
	
	public static void process(char[] chs, int i, List<Character> res) {
		if(i == chs.length) {
			printList(res);
		}
		List<Character> resKeep = copyList(res);
		resKeep.add(chs[i]);
		process(chs, i+1, resKeep);
		List<Character> resNoInclude = copyList(res);
		process(chs, i+1, resNoInclude);
	}
	
	public static void printList(List<Character> res) {
		// ...;
	}
	
	public static List<Character> copyList(List<Character> list){
		return null;
	}

	public static void printAllSub(char[]str,int i,String res){
		if (i==str.length){
			System.out.println(res);
			return;
		}
		printAllSub(str,i+1,res);
		printAllSub(str,i+1,res+str[i]);

	}

	public static void printAllSub1(char[] str,int i){
		if (i==str.length){
			System.out.println(new String(str));
		    return;
		}
		printAllSub1(str,i+1);//选择
		char temp=str[i];
		str[i]=' ';//回溯
		printAllSub1(str,i+1);//选择空
		str[i]=temp;//恢复再回溯

	}


	public static void main(String[] args) {
		String test = "abc";
		printAllSubsquence(test);
		printAllSub(test.toCharArray(),0,"");
	}

}
