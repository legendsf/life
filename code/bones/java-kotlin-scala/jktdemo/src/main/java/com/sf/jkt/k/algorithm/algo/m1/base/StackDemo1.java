package com.sf.jkt.k.algorithm.algo.m1.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackDemo1 {
    public static boolean isValidBrackets(String s){
        if(s==null||s.length()<1){
            return false;
        }
        Map<Character,Character> map=new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        Stack<Character> stack=new Stack<>();
        for (int i=0;i<s.length();i++){
            Character ch=s.charAt(i);
            if (map.containsKey(ch)){
                if(stack.isEmpty()||!map.get(ch).equals(stack.pop())){
                   return false;
                }
            }else {
                stack.push(ch);
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValidBrackets1(String s){
       if(s==null||s.length()<1){
           return false;
       }
       Stack<Character> stack=new Stack<>();
       Map<Character, Character> map=new HashMap<>();
       map.put('}','{');
       map.put(']','[');
       map.put('(',')');
       for (int i=0;i<s.length();i++){
           Character ch=s.charAt(i);
           if(map.containsKey(ch)){
               if(stack.isEmpty()||!map.get(ch).equals(stack.pop())){
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
        System.out.println(isValidBrackets1("{{})"));
        System.out.println(isValidBrackets1("{{}}"));
    }
}
