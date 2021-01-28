package com.sf.jkt.k.algorithm.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

public class StreamDemo {
    /*
    BigDecimal:
BigDecimal bb =list.stream().map(Plan::getAmount).reduce(BigDecimal.ZERO,BigDecimal::add);


int、double、long:

double max = list.stream().mapToDouble(User::getHeight).sum();
     */

   static List<BigDecimal> bl= Arrays.asList(
            new BigDecimal(1.01),
            new BigDecimal(1.02),
            new BigDecimal(1.03)
    );

   static List<Integer> il=Arrays.asList(1,2,3);

    public static void main(String[] args) {
      BigDecimal bi=   bl.stream().map((s)->s).reduce(BigDecimal.ZERO,BigDecimal::add);
        System.out.println(bi.setScale(4, RoundingMode.HALF_UP));
      int i1=  il.stream().mapToInt((s)->s).sum();
        System.out.println(i1);
      int i2=il.stream().map((s)->s).reduce(0,Integer::sum);
        System.out.println(i2);
    }
}
