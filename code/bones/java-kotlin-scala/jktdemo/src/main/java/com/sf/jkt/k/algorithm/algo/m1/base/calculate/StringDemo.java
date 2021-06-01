package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

/**
 * https://leetcode-cn.com/problems/add-strings/solution/zi-fu-chuan-xiang-jia-by-leetcode-solution/
 */
public class StringDemo {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        // 计算完以后的答案需要翻转过来
        ans.reverse();
        return ans.toString();
    }


    public static String addStrings1(String s1,String s2){
        int i=s1.length()-1,j=s2.length()-1,add=0;
        StringBuilder sbd=new StringBuilder();
        while (i>=0||j>=0||add!=0){
           int x=i>=0?s1.charAt(i)-'0':0;
           int y=j>=0?s2.charAt(j)-'0':0;
           int result=x+y+add;
//           sbd.append(result%10);//
            sbd.insert(0,result%10);
            add=result/10;
            i--;
            j--;
        }
//        sbd.reverse();
        return  sbd.toString();
    }


    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int m = num1.length(), n = num2.length();
        int[] ansArr = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {
            int x = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int y = num2.charAt(j) - '0';
                ansArr[i + j + 1] += x * y;
            }
        }
        for (int i = m + n - 1; i > 0; i--) {
            ansArr[i - 1] += ansArr[i] / 10;
            ansArr[i] %= 10;
        }
        int index = ansArr[0] == 0 ? 1 : 0;
        StringBuffer ans = new StringBuffer();
        while (index < m + n) {
            ans.append(ansArr[index]);
            index++;
        }
        return ans.toString();
    }

    public static String mutiplyString(String num1,String num2){
        if(num1==null||num2==null||num2.equals("0")||num2.equals("0")){
            return "0";
        }
        int m=num1.length(),n=num2.length();
        int[] ansArr=new int[m+n];
        for(int i=m-1;i>=0;i--){
            int x=num1.charAt(i)-'0';
           for(int j=n-1;j>=0;j--){
              int y=num2.charAt(j) -'0';
              ansArr[i+j+1] += x*y;
           }
        }
        for(int i=m+n-1;i>0;i--){
            ansArr[i-1] += ansArr[i]/10;
            ansArr[i] %= 10;
        }
        int index=ansArr[0]==0?1:0;
        StringBuffer sbd=new StringBuffer();
        while (index<m+n){
            sbd.append(ansArr[index++]);
        }
        return sbd.toString();
    }

    public static String addStrings2(String num1,String num2){
       if(num1==null||num1.length()<1){
           return num2;
       }
       if(num2==null||num2.length()<1){
           return num1;
       }
       int i=num1.length()-1,j=num2.length()-1,add=0;
       StringBuilder sbd=new StringBuilder();
       while (i>=0 || j>=0 ||add!=0){
           int x=i>=0?num1.charAt(i--)-'0':0;
           int y=j>=0?num2.charAt(j--)-'0':0;
           int result=x+y+add;
           sbd.insert(0,result%10);
           add = result/10;
       }
       return  sbd.toString();
    }

    public static String addStrings3(String num1,String num2){
        if(num1==null||num1.length()<1){
            return num2;
        }
        if(num2==null||num2.length()<1){
            return num1;
        }
        int i=num1.length()-1,j=num2.length()-1,add=0;
        StringBuilder sbd=new StringBuilder();
        while (i>=0 || j>=0 || add!=0){
            int x=i>=0?num1.charAt(i--)-'0':0;
            int y=j>=0?num2.charAt(j--)-'0':0;
            int result=x+y+add;
            sbd.insert(0,result%10);
            add = result/10;
        }
        return  sbd.toString();
    }

    public static String mutiplyString1(String num1,String num2){
        if(num1==null||num1.equals("0")||num2==null||num2.equals("0")){
            return "0";
        }
        int m=num1.length(),n=num2.length();
        int[] ansArr=new int[m+n];
        for(int i=m-1;i>=0;i--){
            int x=num1.charAt(i)-'0';
            for(int j=n-1;j>=0;j--){
               int y=num2.charAt(j)-'0';
               ansArr[i+j+1] += x*y;
            }
        }
        for(int i=m+n-1;i>0;i--){
            ansArr[i-1] += ansArr[i]/10;//jinwei
            ansArr[i]=ansArr[i]%10;
        }
        StringBuilder sbd=new StringBuilder();
        int index=ansArr[0]==0?1:0;
        while (index<m+n){
            sbd.append(ansArr[index++]);
        }
        return sbd.toString();
    }

    public static void test(){
        String s1="99999999999";
        String s2="88888888";
        System.out.println(addStrings1(s1,s2));
        System.out.println(addStrings2(s1,s2));
        System.out.println(addStrings3(s1,s2));
        System.out.println(multiply(s1,s2));
        System.out.println(mutiplyString(s1,s2));
        System.out.println(mutiplyString1(s1,s2));
    }

    public static void main(String[] args) {
        test();
    }


}
