package com.sf.jkt.k.algorithm.base.queue;

import java.util.*;

public class RandomTest {

    public static void main(String[] args) {
       rand();
    }

    public static void rand(){
        Random random= new Random();
        int len=10;
        for(int i=0;i<len*10;i++){
           int res=random.nextInt(len);
//            System.out.println(res);
            double db=random.nextDouble();
            System.out.println(db*10);
        }
    }

    public static List<Integer> randomtest() {
        int m = 3;
        double costednum = 0.0;
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int len = array.length;
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<len; i++) {
            double probability = (m - costednum) / (len - i);
            double value = Math.random();
            if(probability > value) {
                list.add(array[i]);
                costednum += 1;
            }
        }
        return list;
    }

    public static void massive_randomtest() {
        Map<Integer, Integer> map = new HashMap();
        for(int i=0; i<10000; i++) {
            List<Integer> list = randomtest();
            for(int each: list) {
                map.put(each, map.getOrDefault(each, 0) + 1);
            }
        }
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
