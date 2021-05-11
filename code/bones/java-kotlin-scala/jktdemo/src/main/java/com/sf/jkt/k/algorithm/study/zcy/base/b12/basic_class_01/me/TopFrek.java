package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import java.util.*;

public class TopFrek {
    public static void main(String[] args) {
        System.out.println("love".compareTo("a"));
        String[] list= new String[]{"a","b","a","a","b","c"};
        list =new String[]{"i", "love", "leetcode", "i", "love", "coding"};
        topKFrequent(list,2);
    }

    public static List<String> topKFrequent(String[] words, int k) {
        List<String> result=new ArrayList();
        Map<String,Integer> map=new HashMap();
        for(int i=0;i<words.length;i++){
            map.put(words[i],map.getOrDefault(words[i],0)+1);
        }
        if(words==null){
            return result;
        }
        //大根堆
        PriorityQueue<String> heap=new PriorityQueue<String>((w1, w2)->{
            return map.get(w1).equals(map.get(w2))?w2.compareTo(w1):
                    map.get(w2)-map.get(w1);
        });


        for(int i=0;i<words.length;i++){

            if(heap.size()>= k){
                break;
            }
            heap.offer(words[i]);

        }
        while(!heap.isEmpty()){
            result.add(heap.poll());
        }
        return result;
    }

}
