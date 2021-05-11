package com.sf.jkt.k.algorithm.algo.m1;

import java.util.*;

public class StackDemo {
    public static boolean isValidBrackets(String s){
        if(s==null||s.length()<1){
            return false;
        }
        Map<Character,Character> map=new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        Deque<Character> stack=new LinkedList<Character>();
        for(int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            if(map.containsKey(ch)){
               if(stack.isEmpty()||!map.get(ch).equals(stack.pop())) {
                   return false;
               }
            }else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValidBrackets("{{})"));
        System.out.println(isValidBrackets("{{}}"));
    }
}
