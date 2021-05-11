package com.sf.jkt.k.algorithm.algo.array;

import java.util.Stack;

public class MStackDemo {
    public static void main(String[] args) {
        Stack<String> stack=new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");
        System.out.println(stack.firstElement());//栈底元素
        System.out.println(stack.lastElement());
        System.out.println(stack.get(1));
    }
}
