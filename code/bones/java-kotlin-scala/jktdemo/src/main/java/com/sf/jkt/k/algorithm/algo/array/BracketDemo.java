package com.sf.jkt.k.algorithm.algo.array;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class BracketDemo {
    
    public static boolean isValid(String str){
        if(str==null||(str.length()%2)==1){
            return false;
        }
        Map<Character,Character> map=new HashMap<>();
        map.put('}','{');
        map.put(']','[');
        map.put(')','(');
        Deque<Character> deque=new LinkedList<>();
        for(int i=0;i<str.length();i++){
            Character ch=str.charAt(i);
            if(map.containsKey(ch)){
                if(deque.isEmpty()||deque.peek()!=map.get(ch)){
                    return false;
                }
                deque.pop();
            }else {
                deque.push(ch);
            }
        }
        return deque.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("{{})"));
    }
}
