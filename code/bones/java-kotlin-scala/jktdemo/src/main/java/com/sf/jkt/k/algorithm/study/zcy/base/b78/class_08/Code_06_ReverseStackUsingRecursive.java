package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_08;

import java.util.Stack;

public class Code_06_ReverseStackUsingRecursive {

	public static void reverse(Stack<Integer> stack) {
		if (stack.isEmpty()) {
			return;
		}
		int i = getAndRemoveLastElement(stack);
		reverse(stack);
		stack.push(i);
	}

	public static int getAndRemoveLastElement(Stack<Integer> stack) {
		int result = stack.pop();
		if (stack.isEmpty()) {
			return result;
		} else {
			int last = getAndRemoveLastElement(stack);
			stack.push(result);
			return last;
		}
	}

	public static void reverse1(Stack<Integer> stack){
		if (stack.isEmpty()){
			return;
		}
		int i=getAndRemoveLastElement1(stack);
		reverse1(stack);
		stack.push(i);
	}
	public static Integer getAndRemoveLastElement1(Stack<Integer> stack){
	    int result=stack.pop();
	    if (stack.isEmpty()){
	    	return result;
		}else {
	    	int last=getAndRemoveLastElement1(stack);
	    	stack.push(result);
	    	return last;
		}

	}

	public static void reverse2(Stack<Integer> stack){
		if (stack.isEmpty()){
			return;
		}
		Integer i=getAndRemoveLastElement2(stack);
		reverse(stack);
		stack.push(i);
	}
	public static Integer getAndRemoveLastElement2(Stack<Integer> stack){
		int result=stack.pop();
		if (stack.isEmpty()){
			return result;
		}else {
			int last=getAndRemoveLastElement2(stack);
			stack.push(result);
			return last;
		}
	}

	public static void main(String[] args) {
		Stack<Integer> test = new Stack<Integer>();
		test.push(1);
		test.push(2);
		test.push(3);
		test.push(4);
		test.push(5);
		reverse(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}
		for (int i=1;i<=5;i++){
			test.push(i);
		}
		System.out.println();
		reverse2(test);
		while (!test.isEmpty()) {
			System.out.println(test.pop());
		}


	}

}
