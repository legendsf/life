package com.sf.jkt.k.algorithm.algo.m1.algo.type;

import java.util.*;
public class FalseCoin {
	private static int weighNum, n;                //weightNum用来存储使用天平的次数，n为硬币总数

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String[] input = sc.nextLine().split(" ");//输入三个数，第一个是硬币总个数，第二个是假币是第几个，第三个是假币比真币轻还是重,1轻0重
		n = Integer.valueOf(input[0]);
		int k = Integer.valueOf(input[1]);
		int isLighter = Integer.valueOf(input[2]);
		int[] coins = new int[n];                    //硬币

		for (int i = 0; i < n; i++) {            //创建硬币数组
			if (i == (k - 1)) {
				if (isLighter == 0) {
					coins[i] = 2;
				} else {
					coins[i] = 0;
				}
			} else {
				coins[i] = 1;
			}
		}

		int location = findFalseCoin(0, n - 1, coins);
		System.out.println(location);        //打印假币位置


		int index = location - 1;
		if (index - 1 > -1)                    //打印假币轻重
		{
			System.out.println(weigh(coins[index - 1], coins[index]) == 1 ? "假币更轻" : "假币更重");
		} else if (index + 1 < n) {
			System.out.println(weigh(coins[index + 1], coins[index]) == 1 ? "假币更轻" : "假币更重");
		}

		System.out.println(weighNum);                        //打印使用天平次数
	}

	/**
	 * coins数组中，从下表s到e，查找假硬币
	 *
	 * @param s     起始下表
	 * @param e     结束下标
	 * @param coins
	 * @return 返回假硬币的位置
	 */
	private static int findFalseCoin(int s, int e, int[] coins) {
		int len = e - s + 1;
		int oneThreeLen = len / 3;
		int remainder = len % 3;

		if (len < 3) {
			return lenLessThanThree(s, e, coins) + 1;
		}

		//把s到e的硬币分成三份，计算每份的总重量
		int weight1 = getWeight(s, s + oneThreeLen - 1, coins);
		int weight2 = getWeight(s + oneThreeLen, s + 2 * oneThreeLen - 1, coins);
		int weight3 = getWeight(s + 2 * oneThreeLen, e - remainder, coins);

		//比较三份的重量，算出哪一份中有假币（或最后余出来硬币中）
		if (weigh(weight1, weight2) == 0 && weigh(weight2, weight3) == 0)    //三份相同，则在余出来的硬币中
		{
			return lenLessThanThree(e - remainder + 1, e, coins);
		} else if (weigh(weight1, weight2) == 0)                            //在第三份中
		{
			return findFalseCoin(s + 2 * oneThreeLen, e, coins);
		} else if (weigh(weight1, weight3) == 0)                            //在第二份中
		{
			return findFalseCoin(s + oneThreeLen, s + 2 * oneThreeLen - 1, coins);
		} else                                            //在第一份中
		{
			return findFalseCoin(s, s + oneThreeLen - 1, coins);
		}
	}

	//要搜寻的数组长度小于3时，调用此方法直接求出假币位置
	private static int lenLessThanThree(int s, int e, int[] coins) {
		int len = e - s + 1;
		if (len == 1) {
			return s;
		} else {
			if (s - 1 > -1) {
				return weigh(coins[s - 1], coins[s]) == 0 ? e : s;
			} else if (e + 1 < n) {
				return weigh(coins[e + 1], coins[e]) == 0 ? s : e;
			}
		}

		return 0;
	}

	//算出coins中从s到e的硬币总重量
	private static int getWeight(int s, int e, int[] coins) {
		int sum = 0;
		for (int i = s; i <= e; i++) {
			sum += coins[i];
		}
		return sum;
	}

	//使用天平，比较左右的重量，左边重返回1，轻返回-1，相同返回0
	private static int weigh(int left, int right) {
		weighNum++;
		if (left > right) {
			return 1;
		} else if (left < right) {
			return -1;
		} else {
			return 0;
		}
	}

}