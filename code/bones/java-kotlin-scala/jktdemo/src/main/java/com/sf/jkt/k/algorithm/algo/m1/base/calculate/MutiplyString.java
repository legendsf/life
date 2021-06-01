package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

public class MutiplyString {


    public static String multiply1(String num1,String num2){
        if(num1.equals("0")||num2.equals("0")){
            return "0";
        }
        String res="0";
        for (int i=num2.length()-1;i>=0;i--){
            int carry=0;
            StringBuilder sbd=new StringBuilder();
            for (int j=0;j<num2.length()-1-i;j++){
                sbd.append("0");
            }
            int n2=num2.charAt(i)-'0';
            for (int j=num1.length()-1;j>=0||carry!=0;j--){
                int n1=j<0?0:num1.charAt(j)-'0';
                int product=(n1*n2+carry)%10;
                sbd.insert(0,product);
                carry=(n1*n2+carry)/10;
            }
            res=addStrings(res,sbd.toString());
        }
        return res;
    }

    /**
     * 计算形式
     *    num1
     *  x num2
     *  ------
     *  result
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        // 保存计算结果
        String res = "0";

        // num2 逐位与 num1 相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            // 保存 num2 第i位数字与 num1 相乘的结果
            StringBuilder temp = new StringBuilder();
            // 补 0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';

            // num2 的第 i 位数字 n2 与 num1 相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry) / 10;
            }
            // 将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    /**
     * 对两个字符串数字进行相加，返回字符串形式的和
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder builder = new StringBuilder();
        int carry = 0;
        for (int i = num1.length() - 1, j = num2.length() - 1;
             i >= 0 || j >= 0 || carry != 0;
             i--, j--) {
            int x = i < 0 ? 0 : num1.charAt(i) - '0';
            int y = j < 0 ? 0 : num2.charAt(j) - '0';
            int sum = (x + y + carry) % 10;
            builder.append(sum);
            carry = (x + y + carry) / 10;
        }
        return builder.reverse().toString();
    }

    public static String multiply2(String num1,String num2){
        if("0".equals(num1)||"0".equals(num2)){
            return "0";
        }
        String res="0";
        for (int i=num2.length()-1;i>=0;i--){
            int carry=0;
            StringBuilder sbd=new StringBuilder();
            for (int j=0;j<num2.length()-1-i;j++){
               sbd.insert(0,"0");
            }
            int n1=num2.charAt(i)-'0';
            for (int j=num1.length()-1;j>=0||carry!=0;j--){
                int n2= j<0?0:num1.charAt(j)-'0';
                int tmp=n1*n2+carry;
                sbd.insert(0,tmp%10);
                carry=tmp/10;
            }
            res=addStrings(res,sbd.toString());
        }
        return res;
    }

    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }
    public int add2(int a, int b) {
        if (b == 0) {
            return a;
        }

        // 转换成非进位和 + 进位
        return add(a ^ b, (a & b) << 1);
    }

    public int add1(int a,int b){
        while (b!=0){
            int c=(a&b)<<1;
            a^=b;
            b=c;
        }
        return a;
    }

    public static void test(){
        String s1="99999999999";
        String s2="88888888";
        s1="123";
        s2="45";
        System.out.println(multiply(s1,s2));
        System.out.println(multiply1(s1,s2));
        System.out.println(multiply2(s1,s2));
    }

    public static void main(String[] args) {
        test();
    }

}
