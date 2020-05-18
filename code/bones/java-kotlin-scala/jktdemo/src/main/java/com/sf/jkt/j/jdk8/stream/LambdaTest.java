package com.sf.jkt.j.jdk8.stream;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class LambdaTest {
   static   List<String> names = Arrays.asList("peter","anna","mike","xenia");
    static {
        names = Arrays.asList("1","2","3","5");
    }

    public static void main(String[] args) {
//        testSort();
        testLambda();
    }

    public static void testLambda(){
        UnaryOperator<String> unaryOperator = x -> x+"2";
        System.out.println(unaryOperator.apply("9420-"));
        BinaryOperator<Integer> bina=(x,y)->{
            return x>y ?x:y;
        };
        System.out.println(bina.apply(1,2));
    }

    public static void testSort(){
        String[] ia=new String[names.size()];
        names.toArray(ia);
        Arrays.parallelSort(ia,(o1,o2)->o2.compareTo(o1));
        Collections.sort(names,(a,b)->b.compareTo(a));
        names.forEach(System.out::println);
    }

}
