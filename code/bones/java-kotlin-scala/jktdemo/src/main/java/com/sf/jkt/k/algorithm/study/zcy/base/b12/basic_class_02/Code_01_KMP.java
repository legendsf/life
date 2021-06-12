package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_02;

public class Code_01_KMP {

	public static int getIndexOf(String s, String m) {
		if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
			return -1;
		}
		char[] ss = s.toCharArray();
		char[] ms = m.toCharArray();
		int si = 0;
		int mi = 0;
		int[] next = getNextArray(ms);
		while (si < ss.length && mi < ms.length) {
			if (ss[si] == ms[mi]) {//匹配上就++
				si++;
				mi++;
			} else if (next[mi] == -1) {//乙已经跳到开头了，还是配不上
				si++;//甲后移
			} else {
				mi = next[mi];//乙还可以往前跳
			}
		}
		//如果乙指针成功划过所有string2那么说明找到了，否则返回
		return mi == ms.length ? si - mi : -1;
	}

	public static int[] getNextArray(char[] ms) {//str2
		if (ms.length == 1) {
			return new int[] { -1 };
		}
		int[] next = new int[ms.length];//存放某个位置上最长的前缀长度
		next[0] = -1;
		next[1] = 0;
		int pos = 2;
		int cn = 0;//我跳到的位置
		while (pos < next.length) {
			if (ms[pos - 1] == ms[cn]) {//如果我当前跳到的位置，和我前一个字符是一样的
				next[pos++] = ++cn;//长度确定了，之前的长度是cn-1个，我现在的长度就是++cn那个值
			} else if (cn > 0) {
				//cn 还可以往前跳，next数组那个值就是我要去的位置
				cn = next[cn];
			} else {
				//跳无可跳
				next[pos++] = 0;
			}
		}
		return next;
	}

	public static void main(String[] args) {
		String str = "abcabcababaccc";
		String match = "ababa";
		System.out.println(getIndexOf(str, match));

	}

}
