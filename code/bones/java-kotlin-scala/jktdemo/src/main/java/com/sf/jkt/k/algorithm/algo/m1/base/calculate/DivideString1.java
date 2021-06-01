package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

public class DivideString1 {
    public static String divideString(String num1,String num2){
        if(isLess(num1,num2)){
            return "0";
        }
        String prevCount="1",prevSum=num2,count="1",sum=num2;
        while (!isLess(num1,sum)){
            prevCount=count;
            prevSum=sum;
            count=mutiplyString(count,"2");
            sum=mutiplyString(sum,"2");
        }
        String other=divideString(substractString(num1,prevSum),num2);
        return addString(prevCount,other);
    }
    public static boolean isLess(String num1,String num2){
        if(num1.length()==num2.length()){
            return num1.compareTo(num2)<0;
        }
        return num1.length()<num2.length();
    }
    public static String addString(String num1,String num2){
       int i=num1.length()-1,j=num2.length()-1,carry=0;
       StringBuilder sbd=new StringBuilder();
       while (i>=0||j>=0||carry!=0){
           int x=i<0?0:num1.charAt(i)-'0';
           int y=j<0?0:num2.charAt(j)-'0';
           int sum=x+y+carry;
           sbd.insert(0,sum%10);
           carry=sum/10;
           i--;
           j--;
       }
       return sbd.toString();
    }
    public static String substractString(String num1,String num2){
        if(isLess(num1,num2)){
            return "-"+substractString(num2,num1);
        }
        int i=num1.length()-1,j=num2.length()-1,borrow=0;
        StringBuilder sbd=new StringBuilder();
        while (i>=0||j>=0){
            int x=i<0?0:num1.charAt(i)-'0';
            int y=j<0?0:num2.charAt(j)-'0';
            int z=(x-y-borrow+10)%10;
            sbd.insert(0,z);
            borrow=x-y-borrow<0?1:0;
            i--;
            j--;
        }
        int pos=0;
        for (int k=0;k<sbd.length();k++){
            if (sbd.charAt(k)!='0'){
                break;
            }
            pos++;
        }
        return sbd.substring(pos);
    }
    public static String mutiplyString(String num1,String num2){
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        int m=num1.length(),n=num2.length();
        int[]ans=new int[m+n];
        for (int i=m-1;i>=0;i--){
            int x=num1.charAt(i)-'0';
            for (int j=n-1;j>=0;j--){
               int y=num2.charAt(j)-'0';
               ans[i+j+1] += x*y;
            }
        }
        for (int i=m+n-1;i>0;i--){
          ans[i-1] += ans[i]/10;
          ans[i] %=10;
        }
        StringBuilder sbd=new StringBuilder();
        int index=ans[0]==0?1:0;
        while (index<m+n){
            sbd.append(ans[index++]);
        }
        return sbd.toString();
    }

    public static void test1(){
        System.out.println(addString("111", "2222"));
        System.out.println(addString("2222", "111"));
        System.out.println(addString("111", "0"));
        System.out.println(addString("0", "111"));
        System.out.println(addString("0", "0"));
        System.out.println("---------");
        System.out.println(substractString("111", "2222"));
        System.out.println(substractString("2222", "111"));
        System.out.println(substractString("2222", "1111"));
        System.out.println(substractString("10000", "9999"));
        System.out.println("--------------");
        System.out.println(mutiplyString("111", "2222"));
        System.out.println(mutiplyString("0", "2222"));
        System.out.println(mutiplyString("0", "0"));
        System.out.println(mutiplyString("222", "0"));
        System.out.println("-------");
        System.out.println(divideString("1", "2"));
        System.out.println(divideString("0", "2"));
    }
    public static void test2(){
        System.out.println(divideString("8887", "2"));
    }

    public static void main(String[] args) {
       test2();
    }
}
