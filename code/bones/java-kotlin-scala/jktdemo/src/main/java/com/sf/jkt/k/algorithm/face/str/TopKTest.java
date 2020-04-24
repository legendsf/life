package com.sf.jkt.k.algorithm.face.str;

import java.util.*;

public class TopKTest {
    public static void main(String[] args) {
        int[] a = {1, 1, 1, 2, 2, 3};
        System.out.println(topKFrequent(a, 2));
        System.out.println(topKFrequent2(a, 2));
        HashMap<String,String> map=new HashMap<>(16);
        map.put("a","av");
        map.put("b","bv");
        System.out.println(map.size());
        System.out.println(map.entrySet().size());
        System.out.println(map.size()==map.entrySet().size());
    }

    public static List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(nums.length);
        for (int n : nums) {
            Integer val = map.get(n);
            map.put(n, val == null ? 1 : val + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet().size());
        list.addAll(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o2.getValue();//降序从大到小
            }
        });
        List<Integer> result = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            result.add(list.get(i).getKey());
        }
        return result;
    }

    public static List<Integer> topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int n : nums) {
            Integer val = hashMap.get(n);
            hashMap.put(n, val == null ? 1 : val + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> q = new PriorityQueue<Map.Entry<Integer, Integer>>(hashMap.entrySet().size(), new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
        q.addAll(hashMap.entrySet());
        List<Integer> result = new ArrayList<Integer>(k);
        for (int i = 0; i < k; i++) {
            result.add(q.poll().getKey());
        }
        return result;
    }
}
