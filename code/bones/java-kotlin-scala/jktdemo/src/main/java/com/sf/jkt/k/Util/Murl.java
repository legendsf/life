package com.sf.jkt.k.Util;

public class Murl {
    public static void main(String[] args) {
       new Murl().testURL();
    }

    public void testURL(){
      String fileInResources=  this.getClass().getResource("/README.txt").toString();
        System.out.println(fileInResources);
        this.getClass().getResource("file1.txt").toString();
    }
    public void run(){
        System.out.println("测试========》");
        String s1=this.getClass().getResource("").getPath();
        String s2=this.getClass().getResource("/").getPath();
        System.out.println(s1);
        System.out.println(s2);
    }
}
