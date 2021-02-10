package com.sf.jkt.k.algorithm.Collection;

import cn.hutool.core.lang.Assert;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;



public class ArraysDemo {
    String[] sarr=new String[]{"a","c","a","b","g","d"};

    @Test
    public void testArray3(){
        //复制
        System.out.println(Arrays.copyOf(sarr, sarr.length));
        //翻转
        final ArrayList<String> es = Lists.newArrayList(sarr);
        Collections.reverse(es);
        String[] arr2=new String[sarr.length];
        Arrays.asList(arr2);
        System.out.println(arr2);
        //排序
        Arrays.sort(sarr,Comparator.reverseOrder());
        //洗牌
        Arrays.fill(sarr,"me");
        System.out.println(Arrays.toString(sarr));
        System.arraycopy(sarr,0,arr2,0,arr2.length);
        System.out.println(Arrays.toString(arr2));
    }

    @Test
    public void testArray2(){
        String [] str1= new String[]{"1","2","3"};
        String [] str2= new String[]{"1","2","3"};
        Assert.isFalse(str1.equals(str2));
        Assert.isTrue(Arrays.deepEquals(str1,str2));
        System.out.println(Arrays.equals(str1,str2));
        System.out.println(Arrays.deepHashCode(str1));
        System.out.println(Arrays.deepToString(str1));
    }

    @Test
    public void testNothing()throws Exception{
        System.out.println(Arrays.toString(sarr));
        Arrays.sort(sarr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(sarr));
        String [] arr2 = new String[sarr.length];
        System.arraycopy(sarr,0,arr2,0,sarr.length);
        System.out.println(Arrays.equals(arr2, sarr));
        final String[] arr3 = Arrays.copyOf(arr2, arr2.length);
        System.out.println(Arrays.equals(arr3,sarr));
        final String[] arr4= Arrays.copyOfRange(arr3, 1, 4);
        System.out.println(Arrays.toString(arr4));
    }
}
