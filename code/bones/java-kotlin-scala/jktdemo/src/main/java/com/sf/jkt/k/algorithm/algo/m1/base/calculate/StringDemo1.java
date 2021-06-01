package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

public class StringDemo1 {
   public static String addString(String num1,String num2){
       int i=num1.length()-1,j=num2.length()-1,add=0;
       StringBuilder sbd=new StringBuilder();
       while (i>=0||j>=0||add!=0){
           int x=i>=0?num1.charAt(i)-'0':0;
           int y=j>=0?num2.charAt(j)-'0':0;
           int result=x+y+add;
           sbd.insert(0,result%10);
           add=result/10;
           i--;
           j--;
       }
       return sbd.toString();
   }

   public static String mutiplyString(String num1,String num2){
       if(num1==null||num1.length()<1||num2==null||num2.length()<1){
           return "";
       }
       if(num1.equals("0")||num2.equals("0")){
           return "0";
       }
       int m=num1.length(),n=num2.length();
       int[]ans=new int[m+n];
       for (int i=m-1;i>=0;i--){
           int x=num1.charAt(i)-'0';
           for (int j=n-1;j>=0;j--){
              int y=num2.charAt(j)-'0';
              ans[i+j+1]+=x*y;
           }
       }
       for (int i=m+n-1;i>0;i--){
           ans[i-1] +=ans[i]/10;
           ans[i] %=10;
       }
       int index=ans[0]==0?1:0;
       StringBuilder sbd=new StringBuilder();
       while (index<m+n){
           sbd.append(ans[index]);
           index++;
       }
       return sbd.toString();
   }

   public static String addString1(String num1,String num2){
       int i=num1.length()-1,j=num2.length()-1,add=0;
       StringBuilder sbd=new StringBuilder();
       while (i>=0||j>=0||add!=0){
           int x=i>=0?num1.charAt(i)-'0':0;
           int y=j>=0?num2.charAt(j)-'0':0;
           int result=x+y+add;
           sbd.insert(0,result%10);
           add =result/10;
           i--;
           j--;
       }
       return sbd.toString();
   }

   public static String addString2(String num1,String num2){
      if(num1==null||num1.length()<1||num2==null||num2.length()<1){
          return "";
      }
      if(num1.equals("0")||num2.equals("0")){
          return "0";
      }
      int i=num1.length()-1,j=num2.length()-1,add=0;
      StringBuilder sbd=new StringBuilder();
      while (i>=0||j>=0||add!=0){
          int x=i>=0?num1.charAt(i)-'0':0;
          int y=j>=0?num2.charAt(j)-'0':0;
          int result=x+y+add;
          sbd.insert(0,result%10);
          add=result/10;
          i--;
          j--;
      }
      return sbd.toString();
   }

   public static String mutiplyString1(String num1,String num2){
       if(num1==null||num1.length()<1||num2==null||num2.length()<1){
           return "";
       }
       if(num1.equals("0")||num2.equals("0")){
           return "0";
       }
       int m=num1.length(),n=num2.length();
       int[]ans=new int[m+n];
       for (int i=m-1;i>=0;i--){
           int x=num1.charAt(i)-'0';
           for (int j=n-1;j>=0;j--){
              int y=num2.charAt(j)-'0';
              ans[i+j+1] +=x*y;
           }
       }
       for (int i=m+n-1;i>0;i--){
          ans[i-1] +=ans[i]/10;
          ans[i] %=10;
       }
       StringBuilder sbd=new StringBuilder();
       int index=ans[0]==0?1:0;
       while (index<m+n){
           sbd.append(ans[index++]);
       }
       return sbd.toString();
   }

    public static void test(){
        String s1="99999999999";
        String s2="88888888";
        System.out.println(addString(s1,s2));
        System.out.println(addString1(s1,s2));
        System.out.println(addString2(s1,s2));
        System.out.println(mutiplyString(s1,s2));
        System.out.println(mutiplyString1(s1,s2));
    }

    public static void main(String[] args) {
        test();
    }

}
