package com.sf.jkt.k.algorithm.algo.m1.base.calculate;

/**
 * https://leetcode-cn.com/problems/divide-two-integers/solution/po-su-de-xiang-fa-mei-you-wei-yun-suan-mei-you-yi-/
 *  60/8 = (60-32)/8 + 4 = (60-32-16)/8 + 2 + 4 = 1 + 2 + 4 = 7
 */
public class DivideString {
    public static int divide(int dividend, int divisor) {
        // 当除数为1，直接返回被除数
        if (divisor == 1) {
            return dividend;
        }
        // 当除数为-1且被除数为Integer.MIN_VALUE时，将会溢出，返回Integer.MAX_VALUE
        if (divisor == -1 && dividend == Integer.MIN_VALUE) {
            return Integer.MAX_VALUE;
        }

        // 把被除数与除数调整为正数,为防止被除数Integer.MIN_VALUE转换为正数会溢出，使用long类型保存参数
        if (dividend < 0 && divisor < 0) {
            return divide(-(long) dividend, -(long) divisor);
        } else if (dividend < 0 || divisor < 0) {
            return -divide(Math.abs((long) dividend), Math.abs((long) divisor));
        } else {
            return divide((long) dividend, (long) divisor);
        }
    }

    public static int divide(long dividend, long divisor) {
        // 如果被除数小于除数，结果明显为0
        if (dividend < divisor) {
            return 0;
        }
        long sum = divisor; // 记录用了count个divisor的和
        int count = 1; // 使用了多少个divisor
        while (dividend >= sum) {
            // 每次翻倍
            sum <<= 1;
            count <<= 1;
        }

        // 此时dividend < sum
        sum >>>= 1;
        count >>>= 1;

        // 此时dividend >= sum
        // 将count个divisor从dividend消耗掉，剩下的还需要多少个divisor交由递归函数处理
        return count + divide(dividend - sum, divisor);
    }

    public static int divide1(int dividend,int divisor){
        if(divisor==1){
            return dividend;
        }
        if(dividend==Integer.MIN_VALUE&&divisor==-1){
            return Integer.MAX_VALUE;
        }
        if(dividend<0&&divisor<0){
            return divide1(-((long)dividend),-((long)divisor));
        }else if(dividend<0||divisor<0) {
            return -divide1(Math.abs((long)dividend),Math.abs((long)divisor));
        }else {
            return divide1((long)dividend,(long)divisor);
        }
    }
    public static int divide1(long dividend,long divisor){
        if(dividend<divisor){
            return 0;
        }
        long sum=divisor;
        int count=1;
        while (dividend>=sum){
            sum<<=1;
            count<<=1;
        }
        sum>>>=1;
        count>>>=1;
        return count+divide1(dividend-sum,divisor);
    }

    public static int divide2(int dividend,int divisor){
        if(divisor==0){
            throw new IllegalArgumentException("divisor must not be zero!");
        }
        if(dividend==0){
            return 0;
        }
       if(divisor==1){
           return dividend;
       }
       if(divisor==-1&&dividend==Integer.MIN_VALUE){
          return Integer.MAX_VALUE;
       }
       if(dividend<0&&divisor<0){
         return divide2(-(long)dividend,-(long)divisor);
       }else if(dividend<0||divisor<0){
           return -divide2(Math.abs((long)dividend),Math.abs((long)divisor));
       }else {
           return divide2((long)dividend,(long)divisor);
       }
    }
    private static int divide3(long dividend,long divisor){
        if(dividend<divisor){
            return 0;
        }
       long sum=divisor;
       int count=1;
       while (dividend>=sum){
           sum<<=1;
           count<<=1;
       }
       sum>>>=1;
       count>>>=1;
       return count+divide2(dividend-sum,divisor);
    }

    public static int divide2(long dividend,long divisor){
        if(dividend<divisor){
            return 0;
        }
       long sum=divisor;
       int count=1;
       while (dividend>=sum){
           sum  <<=1;
           count<<=1;
       }
       sum  >>>=1;
       count>>>=1;
       return count+divide2(dividend-sum,divisor);
    }

    public static String divideString(String num1,String num2){
        if(isLess(num1,num2)){
            return "0";
        }
        /**
            不考虑正负号

         */
        return divString(num1,num2);
    }

    private static String divString(String num1,String num2){//a.compareTo(b)>0
        if(num1.compareTo(num2)<0){
            return "";
        }
        if(num2.equals("0")){
            return "";
        }
        if(num1.equals("0")){
            return "0";
        }
        String prevSum=num2;
        String sum=num2;
        String prevCount="1";
        String count="1";
        while (!isLess(num1, sum)){
            prevSum=sum;
            prevCount=count;
            sum=mutiplyString(sum,"2");
            count=mutiplyString(count,"2");
        }
        return addString(prevCount,divideString(substractString(num1,prevSum),num2));
    }

    public static String substractString(String num1,String num2){
        if(isLess(num1,num2)){
            return "-"+sub(num2,num1);
        }
        return sub(num1,num2);
    }
    public static String sub(String a,String b){//a>=b
      StringBuilder ans=new StringBuilder();
      int borrow=0;
      int i=a.length()-1,j=b.length()-1;
      while (i>=0||j>=0){
          int x=i<0?0:a.charAt(i)-'0';
          int y=j<0?0:b.charAt(j)-'0';
          int z=(x-y-borrow+10)%10;
          ans.insert(0,z);
          borrow=x-y-borrow<0?1:0;
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

    public static boolean isLess(String a,String b){
        if(a.length()==b.length()){
            return a.compareTo(b)<0;
        }
        return a.length()<b.length();
    }

    public static String mutiplyString(String num1,String num2){
       if(num1.equals("0")||num2.equals("0")){
           return "0";
       }
       int m=num1.length(),n=num2.length();
       int[] ans=new int[m+n];
       for (int i=m-1;i>=0;i--){
           int x=num1.charAt(i)-'0';
           for (int j=n-1;j>=0;j--){
                int y=num2.charAt(j)-'0';
                ans[i+j+1] += x*y;
           }
       }
       for (int i=m+n-1;i>0;i--){
           ans[i-1] += ans[i]/10;
           ans[i]=ans[i]%10;
       }
       StringBuilder sbd=new StringBuilder();
       int index=ans[0]==0?1:0;
       while (index<m+n){
          sbd.append(ans[index++]);
       }
       return sbd.toString();
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

    public static void test1(){
        System.out.println(Integer.MAX_VALUE>>>1);
        System.out.println(Integer.MIN_VALUE>>>1);
        System.out.println(-2147483647/-1);
        System.out.println(-(-2147483648)/-(-1));
        System.out.println(divide(1, 3));
        System.out.println(divide1(1, 3));
        System.out.println(divide(3, 1));
        System.out.println(divide1(3, 1));
        System.out.println(divide(3, 2));
        System.out.println(divide1(3, 2));
        System.out.println(divide(3, -1));
        System.out.println(divide1(3, -1));
        System.out.println(divide2(3, -1));
        System.out.println(divide2(3, -1));
    }

    public static void testSubString(){
        System.out.println("012".substring(0));
        System.out.println("012".substring(0,2));
        System.out.println("012".substring(0,3));
        System.out.println();
        System.out.println(substractString("1", "99"));
        System.out.println(substractString("10090", "99"));
    }

    public static void testMutiplyString(){
        System.out.println(mutiplyString("11", "222"));
    }

    public static void testAddString(){
        System.out.println(addString("111", "9999"));
        System.out.println(addString("0", "9999"));
    }

    public static void testDivideString(){
        System.out.println(divideString("111", "222"));
        System.out.println(divideString("333", "222"));
        System.out.println(divideString("444", "222"));
    }

    public static void test2(){
       testSubString();
       testMutiplyString();
       testAddString();
       testDivideString();
    }

    public static void main(String[] args) {
        test2();
    }
}
