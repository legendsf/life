package com.sf.jkt.k.algorithm.algo.stack08;

import java.util.Stack;

public class MStack {

    public static class LinkStack {
        Node top = null;

        public static class Node {
            Comparable data;
            Node next;

            public Node(Comparable data, Node next) {
                this.data = data;
                this.next = next;
            }

            public Node(Comparable data) {
                this(data, null);
            }
        }

        public void push(Comparable value) {
            Node nw = new Node(value);
            if (top == null) {
                top = nw;
            } else {
                //头插入
                nw.next = top;
                top = nw;
            }
        }

        public Comparable pop() {
            if (top == null) {
                return null;
            }
            Comparable value = top.data;
            top = top.next;
            return value;
        }
    }

    public static class ArrayStack {
        String[] items;
        int count;
        int n;

        public ArrayStack(int n) {
            items = new String[n];
            this.n = n;
            this.count = 0;
        }

        public boolean push(String item) {
            if (count == n) {
                return false;
            }
            items[count] = item;
            count++;
            return true;
        }

        public String pop() {
            if (count == 0) {
                return null;
            }
            String temp = items[count - 1];
            count--;
            return temp;
        }
    }

    public static boolean matching(String expression) {
        if (expression == null || expression == "") {
            System.out.println("输入表达式为空或没有输入表达式");
        }

        Stack<Character> stack = new Stack<Character>();

        for (int index = 0; index < expression.length(); index++) {
            switch (expression.charAt(index)) {
                case '(':
                    stack.push(expression.charAt(index));
                    break;
                case '{':
                    stack.push(expression.charAt(index));
                    break;
                case ')':
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    }
                    break;

                case '}':
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    }
            }
        }

        if (stack.empty())
            return true;
        return false;
    }

    public static void testBrakets() {

        String expression = "{((1+3)+2+4)+9*7}";
//        expression = "}}}";


        boolean flag = isMatch(expression);

        if (flag) {
            System.out.println("匹配成功！");
        } else {
            System.out.println(" 匹配失败 ");
        }
    }

    public static boolean isMatch(String exp) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            switch (ch) {
                case '(':
                case '{':
                case '[':
                    st.push(ch);
                    break;
                case ')':
                case '}':
                case ']':
                    if (st.isEmpty()) {
                        return false;
                    }
                    char left = st.pop();
                    if (!((left == '(' && ch == ')') || (left == '{' && ch == '}') || (left == '[' && ch == ']'))) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }

        if (st.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        testBrakets();
    }


    public static void testpush() {
        ArrayStack s = new ArrayStack(2);
        s.push("1");
        s.push("2");
        System.out.println(s.pop());
        System.out.println(s.pop());
    }
}

