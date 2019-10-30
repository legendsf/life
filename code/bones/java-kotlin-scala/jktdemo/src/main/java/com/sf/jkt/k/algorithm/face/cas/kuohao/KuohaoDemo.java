package com.sf.jkt.k.algorithm.face.cas.kuohao;

import cn.hutool.core.collection.CollectionUtil;
import org.apache.logging.log4j.util.Strings;
import org.junit.Assert;
import scala.math.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class KuohaoDemo {
    public static void main(String[] args) {
        parentTheses();
        testVaild();
    }

    public static void parentTheses() {
        List<String> list = new ArrayList<>();
        StringBuilder sbd = new StringBuilder("");
        helper(list, sbd, 0, 0, 3);
        list.stream().forEach(System.out::println);
    }

    public static void helper(List<String> list, StringBuilder sbd, int open, int close, int max) {
        if (open == max && close == max) {
            list.add(sbd.toString());
            return;
        }
        if (open < max) {//直接排除不是这个范围内的解
            sbd.append("(");
            helper(list, sbd, open + 1, close, max);
            sbd.setLength(sbd.length() - 1);//回溯到跟上次open数量一致的sbd
        }
        if (close < open) {//直接排除掉不是这个范围内的解
            sbd.append(")");
            helper(list, sbd, open, close + 1, max);
            sbd.setLength(sbd.length() - 1);//回溯到上一次跟close数量一致的右括号
        }
    }

    public static void testVaild(){
        String valid="(()[()])(){}";
        String inValid="({[)}]})";
        Assert.assertTrue(valid(valid));
        Assert.assertFalse(valid(inValid));
    }

    public static boolean valid(String str) {
        //checkInput
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                if (c == ')' && stack.pop() != '(') {
                    return false;
                }
                if (c == ']' && stack.pop() != '[') {
                    return false;
                }
                if (c == '}' && stack.pop() != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
