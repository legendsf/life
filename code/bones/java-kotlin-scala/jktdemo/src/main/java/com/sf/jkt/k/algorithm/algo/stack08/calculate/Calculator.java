package com.sf.jkt.k.algorithm.algo.stack08.calculate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// 为了简单一点，只能计算整数的加减乘除
public class Calculator {
    // 辅助计算的方法：判断一个字符串是否是数字
    private static boolean isNumer(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 辅助计算的方法：获得符号的优先级
    private static Integer getPriority(String str) {
        switch (str) {
            case "+":
            case "-":
                return 1;
            case "*":
            case "/":
                return 2;
            default:
                return -1;
        }
    }

    // 辅助计算的方法：从numStack弹出两个数，operStack弹出一个符号进行运算
    private static int calcuate(Stack<Integer> numStack, Stack<String> operStack) {
        Integer num1 = numStack.pop();
        Integer num2 = numStack.pop();
        String oper = operStack.pop();
        int res = 0;
        switch (oper) {
            case "+":
                res = num2 + num1;
                break;
            case "-":
                res = num2 - num1;
                break;
            case "*":
                res = num2 * num1;
                break;
            case "/":
                res = num2 / num1;
                break;
        }
        return res;
    }


    // 计算表达式
    public static int calcuate(String experssion) {
        // 创建numStack
        Stack<Integer> numStack = new Stack<>();
        // 创建operStack
        Stack<String> operStack = new Stack<>();
        // 创建一个正则匹配器用来匹配表达式的字符和操作数，也可以用其它方式，但是使用正则表达式更方便
        // 如果不清除正则表达式可以去网上查资料了解一下
        Matcher matcher = Pattern.compile("\\d+|\\+|\\-|\\*|\\/|\\(|\\)").matcher(experssion);
        while (matcher.find()) {
            // 获得当前被匹配的字符串
            String str = matcher.group();
            if (isNumer(str)) {
                numStack.push(Integer.parseInt(str));
                // 如果当前字符串是表达式的最后一个子串(即表达式扫描完毕)
                if (matcher.end() == experssion.length()) {
                    // 如果符号栈不为空，就一直计算直到符号栈为空
                    while (!operStack.isEmpty()) {
                        Integer res = calcuate(numStack, operStack);
                        numStack.push(res);
                    }
                }
            } else {
                // 如果符号栈为空，直接将符号添加进符号栈
                if (operStack.isEmpty()) {
                    operStack.push(str);
                } else {
                    // 否则与符号栈的栈顶符号比较优先级
                    String topValue = operStack.getTopValue();
                    // 优先级小于等于栈顶符号的优先级
                    if (getPriority(str) <= getPriority(topValue)) {
                        // 优先级小于栈顶符号，则需要先计算
                        Integer res = calcuate(numStack, operStack);
                        // 计算完将结果压入numStack,将新符号压入operStack
                        numStack.push(res);
                        operStack.push(str);
                    } else {
                        // 优先级大于栈顶符号的优先级
                        operStack.push(str);
                    }
                }
            }
        }
        return numStack.pop();
    }

    public static int calcuate2(String expression) {
        Stack<Integer> numstack = new Stack<>();
        Stack<String> operstack = new Stack<>();
        String pattern = "\\d+|\\+|\\-|\\*|\\/|\\(|\\)";
        Matcher matcher = Pattern.compile(pattern).matcher(expression);
        while (matcher.find()) {
            String str = matcher.group();
            if (isNumer(str)) {
                numstack.push(Integer.parseInt(str));
                if (matcher.end() == expression.length()) {
                    //如果已经到最后一个操作数
                    while (!operstack.isEmpty()) {
                        Integer res = calcuate(numstack, operstack);
                        numstack.push(res);
                    }
                }
            } else {
                if (operstack.isEmpty()) {
                    operstack.push(str);
                } else {
                    String topValue = operstack.getTopValue();
                    if (getPriority(str) <= getPriority(topValue)) {
                        Integer res = calcuate(numstack, operstack);
                        numstack.push(res);
                        operstack.push(str);
                    } else {
                        operstack.push(str);
                    }
                }
            }
        }
        return numstack.pop();
    }


    public static void main(String[] args) {
        String experssion = "2*2*6-4/2";//22
        experssion = "3*4/2+3-5";//4
        experssion="5-3+2/4*3";//2
        System.out.println(experssion + "=" + calcuate2(experssion));
    }


}

/**
 * 因为不确定栈中会存放多少数据，所以选用链表来实现栈结构，也可以直接使用java.util.Stack
 *
 * @param <E>
 */
class Stack<E> {

    Node<E> bottom;
    Node<E> top;

    public Stack() {
    }

    public boolean isEmpty() {
        return bottom == null;
    }

    // 压栈操作
    public void push(E value) {
        Node<E> node = new Node(value);
        // 如果bottom为null,说明链表长度为0,此时需要给bottom赋值，让它成为栈底
        // 否者说明链表已经有节点，直接在链表末尾(栈顶)插入新节点即可
        if (isEmpty()) {
            bottom = node;
            top = node;
        } else {
            node.setPrev(top);
            top.setNext(node);
            top = node;
        }
    }

    // 出栈操作
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有元素");
        }

        // 保存出栈(栈顶)节点
        Node<E> deleteNode = top;
        // 重置top
        // 注意：如果重置后top==null,说明当前出栈的节点是栈底节点
        top = top.getPrev();
        if (top == null) {
            // 将bottom也置为null
            bottom = null;
        } else {
            // 否则删除出栈节点
            top.setNext(null);
        }
        return deleteNode.getValue();
    }

    // 获取栈顶元素的值
    public E getTopValue() {
        return top.getValue();
    }

    /**
     * 声明一个内部类Node，一个Node对象就是一个链表节点
     */
    private class Node<E> {
        private Node prev;  // 前节点指针
        private E value;  // 存放节点值
        private Node next;  // 后节点指针

        public Node(E value) {
            this.value = value;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", value=" + value +
                    '}';
        }
    }
}