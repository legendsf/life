package com.sf.jkt.k.algorithm.face.cas.sort;

import org.automon.implementations.SysOut;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public static void main(String[] args) {
        generateParenthesis(2).stream().forEach(System.out::println);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(res, sb, 0, 0, n);
        return res;
    }
    public static void helper(List<String> res, StringBuilder sb, int open, int close, int max){
        if(open == max && close == max){
            res.add(sb.toString());
            return;
        }

        if(open < max){
            sb.append("(");
            helper(res, sb, open+1, close, max);
            sb.setLength(sb.length()-1);
        }
        if(close < open){
            sb.append(")");
            helper(res, sb, open, close+1, max);
            sb.setLength(sb.length()-1);
        }
    }
}
