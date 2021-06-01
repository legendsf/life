package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

/***
 * https://leetcode-cn.com/circle/discuss/zVtfxd/view/hidbrX/
 */
public class Substract {
    private static String sub(String a, String b) {

        StringBuilder ans = new StringBuilder();
        int borrow = 0;
        int i = a.length() - 1, j = b.length() - 1;
        while (i >= 0 || j >= 0) {
            int x = i >= 0 ? a.charAt(i) - '0' : 0;
            int y = j >= 0 ? b.charAt(j) - '0' : 0;
            int z = (x - borrow - y + 10) % 10;
            ans.insert(0, z);
            borrow = x - borrow - y < 0 ? 1 : 0;
            i--;
            j--;
        }
        int pos=0;
        for (int k=0;k<ans.length();k++){//去掉前导零
            if(ans.charAt(k)!='0'){
                break;
            }
            pos++;
        }
        return ans.substring(pos);
    }

    public static boolean isLess(String a, String b) {
        if (a.length() == b.length()) {
            return a.compareTo(b) < 0;
        }
        return a.length() < b.length();
    }

    public static String subStrings(String num1, String num2) {
        StringBuilder res;
        if (isLess(num1, num2)) {
            res = new StringBuilder(sub(num2, num1));
            res.insert(0, "-");
        } else {
            res = new StringBuilder(sub(num1, num2));
        }
        return res.toString();
    }

    public static String subStrings1(String num1, String num2) {
        if (isLess(num1, num2)) {
            return "-" + sub1(num2, num1);
        }
        return sub1(num1, num2);
    }

    public static boolean isLess1(String num1, String num2) {
        if (num1.length() == num2.length()) {
            return num1.compareTo(num2) < 0;
        }
        return num1.length() < num2.length();
    }

    public static String sub1(String big, String small) {
       StringBuilder ans=new StringBuilder();
       int borrow=0;
       int i=big.length()-1,j=small.length()-1;
       while (i>=0||j>=0){
           int x=i>=0?big.charAt(i)-'0':0;
           int y=j>=0?small.charAt(j)-'0':0;
           int z=(x-y-borrow+10)%10;
           ans.insert(0,z);
           borrow=x-borrow-y<0?1:0;
           i--;
           j--;
       }
        int pos=0;
        for (int k=0;k<ans.length();k++){//去掉前导零
            if(ans.charAt(k)!='0'){
                break;
            }
            pos++;
        }
        return ans.substring(pos);
    }

    public static String subStrings2(String num1,String num2){
        if(isLess(num1,num2)){
            return "-"+sub2(num2,num1);
        }
        return sub(num1,num2);
    }
    public static String sub2(String a,String b){
       int i=a.length()-1,j=b.length()-1;
       int borrow=0;
       StringBuilder ans=new StringBuilder();
       while (i>=0||j>=0){
                int x=i<0?0:a.charAt(i)-'0';
                int y=j<0?0:b.charAt(j)-'0';
                int z=(x-y-borrow+10)%10;
                ans.insert(0,z);
                borrow=x-y-borrow<0?1:0;
                i--;
                j--;
       }
       return ans.toString();
    }
    public static boolean isLess2(String a,String b){
        if(a.length()==b.length()){
            return a.compareTo(b)<0;
        }
        return a.length()<b.length();
    }

    public static void test1() {
        System.out.println(subStrings("101080", "99"));
        System.out.println(subStrings1("101080", "99"));
        System.out.println(subStrings2("101080", "99"));
        System.out.println(subStrings("101080", "0"));
        System.out.println(subStrings2("101080", "0"));
        System.out.println(subStrings2("1", "99"));
        System.out.println(isLess("9", "8"));
        System.out.println(isLess("7", "8"));
    }

    public static void test2(){
        System.out.println(subStrings1("22", "11"));
    }

    public static void main(String[] args) {
        test1();
    }

}
