package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_02;

public class Code_04_Manacher {

	public static char[] manacherString(String str) {
		char[] charArr = str.toCharArray();
		char[] res = new char[str.length() * 2 + 1];
		int index = 0;
		for (int i = 0; i != res.length; i++) {
			res[i] = (i & 1) == 0 ? '#' : charArr[index++];
		}
		return res;
	}

	public static int maxLcpsLength(String str) {//str2
		if (str == null || str.length() == 0) {
			return 0;
		}
		char[] charArr = manacherString(str);
		//回文半径数组
		int[] pArr = new int[charArr.length];
		int index = -1;
		int pR = -1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i != charArr.length; i++) {
			//i'的回文到r的距离，那个小，那个就是内部的回文半径
			pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
			//尝试扩充回文半径
			while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
				if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
					pArr[i]++;
				else {
					break;
				}
			}
			//扩出来的区域超过原有最大值，那么就要更新
			if (i + pArr[i] > pR) {
				pR = i + pArr[i];
				index = i;
			}
			max = Math.max(max, pArr[i]);
		}
		return max - 1;
	}

	public static void main(String[] args) {
		String str1 = "abc1234321ab";
		System.out.println(maxLcpsLength(str1));
	}

}
