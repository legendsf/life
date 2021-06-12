package com.sf.jkt.k.algorithm.study.msb.mashibing.sys.class25;

import java.util.Stack;
//study001
// 测试链接：https://leetcode.com/problems/maximal-rectangle/
public class Code04_MaximalRectangle {

	public static int maximalRectangle(char[][] map) {
		if (map == null || map.length == 0 || map[0].length == 0) {
			return 0;
		}
		int maxArea = 0;
		int[] height = new int[map[0].length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				height[j] = map[i][j] == '0' ? 0 : height[j] + 1;
			}
			maxArea = Math.max(maxRecFromBottom(height), maxArea);
		}
		return maxArea;
	}

	// height是正方图数组
	public static int maxRecFromBottom(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < height.length; i++) {
			//栈不为空，并且当前值小于栈顶
			while (!stack.isEmpty() && height[i] <= height[stack.peek()]) {
				//弹出结算
				int j = stack.pop();//当前位置
				//k,弹出之后栈为空，那么k为-1，否则就是弹出后面的栈顶值
                //k,就是弹出之后栈还有没有东西，没东西就是-1，有东西就是弹出之后下一个栈顶的下标，
				int k = stack.isEmpty() ? -1 : stack.peek();
				//i,是右边界，自己在j位置上，左边界是k 中间是 i-k-1个数
				int curArea = (i - k - 1) * height[j];
				maxArea = Math.max(maxArea, curArea);
			}
			stack.push(i);
		}
		//最后栈中剩下的东西的结算
		while (!stack.isEmpty()) {
			int j = stack.pop();//当前j位置
			int k = stack.isEmpty() ? -1 : stack.peek();
			int curArea = (height.length - k - 1) * height[j];
			maxArea = Math.max(maxArea, curArea);
		}
		return maxArea;
	}


	public static void test1(){
	    char[][]m={
			{'1','0','1','1'},
			{'1','1','1','1'},
			{'1','1','1','0'}
		};

		System.out.println(maximalRectangle(m));
	}

	public static void main(String[] args) {
		test1();
	}

}
