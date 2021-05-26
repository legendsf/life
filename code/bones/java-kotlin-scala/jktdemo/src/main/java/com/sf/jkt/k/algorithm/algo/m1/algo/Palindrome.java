package com.sf.jkt.k.algorithm.algo.m1.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://blog.csdn.net/weixin_43857345/article/details/107768292
 * 示例 :
 * 输入: “A man, a plan, a canal: Panama”
 * 输出: true
 *
 */
public class Palindrome {
    static String STR="A man, a plan, a canal: Panama";

    public static int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }

    public static int countSubstrings1(String s ){
        int n=s.length(),ans=0;
        for(int i=0;i<2*n-1;i++){
            int l= i/2;int r=l+ i%2;
            while (l>=0 && r<n &&s.charAt(l)==s.charAt(r)){
                --l;
                r++;
                ans++;
            }
        }
        return ans;
    }

    /**
     * 双指针往中间逼近
     * @param str
     * @return
     */
    public static boolean isPalindrome(String str){
        if(str==null){
            return true;
        }
        StringBuilder sbd=new StringBuilder();
        for(int i=0;i<str.length();i++){
            Character ch=str.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sbd.append(ch);
            }
        }
        String newStr=sbd.toString().toLowerCase();
        int l=0;
        int r=newStr.length()-1;
        while (l<r){
            if(newStr.charAt(l)!=newStr.charAt(r)){
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
    public static int countSubstrings2(String s){
        int n=s.length(),ans=0;
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+ i%2;
            while (l>=0 && r< n && s.charAt(l)==s.charAt(r)){
                --l;
                r++;
                ans++;
            }
        }
        return ans;
    }

    public static List<String > countSubstrings3(String s){
        List<String> result=new ArrayList<>();
        if(s==null||s.length()<1){
            return  result;
        }
        int n=s.length(),ans=0;
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while (l>=0 && r<n &&s.charAt(l)==s.charAt(r)){
                String temp=s.substring(l,r+1);
                result.add(temp);
                --l;
                r++;
                ans++;

            }
        }
        System.out.println("ans:"+ans);
        return result;
    }

    public static int countSubstrings4(String s){
          int len = s.length();
        int count = 0;
        boolean[]dp=new boolean[len];

        for (int j = 0; j < len; j++) {
            for (int i = 0; i <= j; i++) {
                if (j == i) {
                    dp[i] = true;
                    count++;
                } else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i] = true;
                    count++;
                } else if (j - i > 1 && s.charAt(i) == s.charAt(j) && dp[i + 1]) {
                    dp[i] = true;
                    count++;
                } else {
                    dp[i] = false;
                }
            }
        }
        return count;
    }

    public static int countSubstrings5(String s){
        int len=s.length(),count=0;
        boolean[]dp=new boolean[len];
        for(int j=0;j<len;j++){
            for(int i=0;i<=j;i++){
                if(i==j){
                    count++;
                    dp[i]=true;
                }else if(j-i==1&& s.charAt(i)==s.charAt(j) ){
                    count++;
                    dp[i]=true;
                }else if(j-i>1 && s.charAt(i)==s.charAt(j)&&dp[i+1]){
                    count++;
                    dp[i]=true;
                }else {
                    dp[i]=false;
                }
            }
        }
        return count;
    }

    public static int countSubstrings6(String s){
        int len=s.length(),count=0;
        boolean[][]dp=new boolean[len][len];
        for(int j=0;j<len;j++){
            for(int i=0;i<=j;i++){
                if(i==j){
                    count++;
                    dp[i][j]=true;
                }else if(j-i==1&& s.charAt(i)==s.charAt(j)){
                    count++;
                    dp[i][j]=true;
                }else if(j-i>1 && s.charAt(i)==s.charAt(j) && dp[i+1][j-1]){
                    count++;
                    dp[i][j]=true;
                }
//                else {//不用重置
//                    dp[i][j]=false;
//                }
            }
        }
        return count;
    }

    public static int countSubstring7(String s){
       return -1;
    }

    public static String manacher(String s)
    {
        /*改造字符串*/
        String res="$#";
        for(int i=0;i<s.length();++i)
        {
            res+=s.charAt(i);
            res+="#";
        }
        res+="@";

        /*数组*/
        int[] P=new int[res.length()];
        int mi=0,right=0;   //mi为最大回文串对应的中心点，right为该回文串能达到的最右端的值
        int maxLen=0,maxPoint=0;    //maxLen为最大回文串的长度，maxPoint为记录中心点

        for(int i=1;i<res.length()-1;++i)
        {
            P[i]=right>i ?Math.min(P[2*mi-i],right-i):1;     //关键句，文中对这句以详细讲解

            while(res.charAt(i+P[i])==res.charAt(i-P[i])){
                ++P[i];
            }

            if(right<i+P[i])    //超过之前的最右端，则改变中心点和对应的最右端
            {
                right=i+P[i];
                mi=i;
            }

            if(maxLen<P[i])     //更新最大回文串的长度，并记下此时的点
            {
                maxLen=P[i];
                maxPoint=i;
            }
        }
        return s.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }

    public static String manacher1(String s){
        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<s.length();i++){
            sbd.append(s.charAt(i)).append("#");
            if(s.length()-i==1){
                sbd.append("@");
            }
        }
        String res=sbd.toString().toLowerCase();
        int[] p=new int[res.length()];

        int mi=0,right=0,maxPoint=0,maxLen=0;
        for(int i=1;i<res.length()-1;i++){
           p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
           while (res.charAt(i-p[i])==res.charAt(i+p[i])){
               ++p[i];
           }
           if(right<i+p[i]){
               right=i+p[i];
               mi=i;
           }
           if(maxLen<p[i]){
               maxLen=p[i];
               maxPoint=i;
           }
        }

        return s.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }

    public static String manacher2(String s){
        if(s==null||s.length()<1){
            return null;
        }
        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<s.length();i++){
            sbd.append(s.charAt(i)).append("#");
            if(i==s.length()-1){
                sbd.append("@");
            }
        }
        String res=sbd.toString().toLowerCase();
        int[]p=new int[res.length()];
        int mi=0,right=0,maxPoint=0,maxLen=0;
        for(int i=1;i<res.length()-1;i++){
            p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
            while (res.charAt(i-p[i])==res.charAt(i+p[i])){
                ++p[i];
            }
            if(right<i+p[i]){
                mi=i;
                right=i+p[i];
            }
            if(maxLen<p[i]){
                maxLen=p[i];
                maxPoint=i;
            }
        }
        Arrays.stream(p).forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return s.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }


    public static String manacher4(String s){
        if(s==null||s.length()<1){
            return null;
        }
        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<s.length();i++){
            sbd.append(s.charAt(i)).append("#");
            if(i==s.length()-1){
                sbd.append("@");
            }
        }
        String res=sbd.toString().toLowerCase();
        int[]p=new int[res.length()];
        List<String> subList=new ArrayList<>();
        int mi=0,right=0,maxPoint=0,maxLen=0,ans=0;
        for(int i=1;i<res.length()-1;i++){
            p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
            while (res.charAt(i-p[i])==res.charAt(i+p[i])){
                ++p[i];
            }
            if(right<i+p[i]){
                mi=i;
                right=i+p[i];
            }
            if(maxLen<p[i]){
                maxLen=p[i];
                maxPoint=i;
            }
            int half=p[i]/2;
            ans += half;
            if(half>0){//不是#之类的
               for(int k=0;k<half;k++){
                   int sourceStartIndex=i/2-1;
                   String temp= s.substring(sourceStartIndex,sourceStartIndex+k+1);
                   subList.add(temp);
               }
            }
        }
        Arrays.stream(p).forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        subList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        System.out.println("ans:"+ans);
        return s.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }

    public static String manacher5(String str){
        if(str==null||str.length()<1){
            return "";
        }
        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<str.length();i++){
            sbd.append(str.charAt(i)).append("#");
            if(i==str.length()-1){
                sbd.append("@");
            }
        }
        String nstr=sbd.toString().toLowerCase();
        int mi=0,right=0,ans=0,maxLen=0,maxPoint=0,n=nstr.length();
        int[]p=new int[n];
        List<String> subList=new ArrayList<>();
        for(int i=1;i<nstr.length()-1;i++){
            p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
            while (nstr.charAt(i-p[i])==nstr.charAt(i+p[i])){
                ++p[i];
            }
            if(right<i+p[i]){
                mi=i;
                right=i+p[i];
            }
            if(maxLen<p[i]){
                maxLen=p[i];
                maxPoint=i;
            }
            int half=p[i]/2;
            ans += half;
            if(half>0){
                for(int k=0;k<half;k++){
                    String temp=str.substring(i/2-1,i/2+k);
                    subList.add(temp);
                }
            }
        }
        System.out.println("ans:"+ans);
        subList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return str.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }

    public static int countSubstrings8(String s){

        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<s.length();i++){
            sbd.append(s.charAt(i)).append("#");
            if(i==s.length()-1){
                sbd.append("@");
            }
        }
        String res=sbd.toString().toLowerCase();
        int[]p=new int[res.length()];
        int mi=0,right=0,maxPoint=0,maxLen=0,ans=0;
        for(int i=1;i<res.length()-1;i++){
            p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
            while (res.charAt(i-p[i])==res.charAt(i+p[i])){
                ++p[i];
            }
            if(right<i+p[i]){
                mi=i;
                right=i+p[i];
            }
            if(maxLen<p[i]){
                maxLen=p[i];
                maxPoint=i;
            }
            ans += p[i]/2;
        }
        return  ans;
    }

    public static void test1(){
        String str="aba";
        str="amanaplanacanalpanama";
        System.out.println(str.substring(0,3));
        int c=countSubstrings(str);
        System.out.println(c);
        System.out.println(countSubstrings1(str));
        System.out.println(countSubstrings2(str));
        System.out.println(countSubstrings4(str));
        System.out.println(countSubstrings5(str));
        System.out.println(countSubstrings6(str));
        System.out.println(countSubstrings5("aa"));
        System.out.println(countSubstrings6("aa"));
        countSubstrings3(str).stream().forEachOrdered(System.out::println);
        System.out.println(manacher("aba"));
        System.out.println(manacher1("a"));
        System.out.println(manacher1("aba"));
        System.out.println(manacher1(str));
        System.out.println(manacher("aabcbaa"));
        System.out.println(manacher("aabbbcc"));
        System.out.println(manacher("abcabaa"));
//        System.out.println(manacher1("abcabaa"));
    }

    /**
     * 中心拓展的意思
     *  l--
     *  r++
     * @param str
     * @return
     */
    public static int countSubstrings9(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int n=str.length(), ans=0;
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while (l>=0 && r<n && str.charAt(l)== str.charAt(r)){
                ans++;
                l--;
                r++;
            }
        }
        return ans;
    }

    public static int countSubstrings10(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int n=str.length(),ans=0;
        for (int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while (l>=0 && r<n && str.charAt(l--)==str.charAt(r++)){
                ans++;
            }
        }
        return ans;
    }

    public static int countSubstrings11(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int n=str.length(),ans=0;
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while (l>=0&&r<n&& str.charAt(l--)==str.charAt(r++)){
                ans++;
            }
        }
        return ans;
    }

    public static boolean isPalindrome2(String str){
        if(str==null||str.length()<1){
            return true;
        }
        StringBuilder sbd=new StringBuilder();
        for(int i=0;i<str.length();i++){
            char ch=str.charAt(i);
            if(Character.isLetterOrDigit(ch)){
                sbd.append(ch);
            }
        }
        String newStr=sbd.toString().toLowerCase();
        int l=0,r=newStr.length()-1;
        while (l<r){
            if(newStr.charAt(l++)!=newStr.charAt(r--)){
                return false;
            }
        }
        return true;
    }

    public static int countSubstrings12(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int n=str.length(),ans=0;
        List<String> subList=new ArrayList<>();
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while (l>=0&&r<n&&str.charAt(l)==str.charAt(r)){
                ans++;
                subList.add(str.substring(l,r+1));
                l--;
                r++;
            }
        }
        subList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return ans;
    }

    public static int manacher3(String str){
       if(str==null||str.length()<1){
           return 0;
       }
       StringBuilder sbd=new StringBuilder("$#");
       for(int i=0;i<str.length();i++){
           sbd.append(str.charAt(i)).append("#");
           if(i==str.length()-1){
               sbd.append("@");
           }
       }
       return -1;

    }

    public static String manacher6(String str){
        if(str==null||str.length()<1){
            return "";
        }
        StringBuilder sbd=new StringBuilder("$#");
        for(int i=0;i<str.length();i++){
            sbd.append(str.charAt(i)).append("#");
            if(i==str.length()-1){
                sbd.append("@");
            }
        }
        String nstr=sbd.toString().toLowerCase();
        int mi=0,right=0,maxPoint=0,maxLen=0,ans=0,n=nstr.length();
        int count2=0;
        int[]p=new int[n];//半径
        List<String> subList=new ArrayList<>();
        for(int i=1;i<n-1;i++){
            p[i]=right>i?Math.min(right-i,p[2*mi-i]):1;
            while (nstr.charAt(i-p[i])==nstr.charAt(i+p[i])){
                p[i]++;
                count2++;
            }
            if(right<i+p[i]){
                mi=i;
                right=i+p[i];
            }
            if(maxLen<p[i]){
                maxLen=p[i];
                maxPoint=i;
            }
            int half=p[i]/2;
            ans += half;
//            if(half>0){//有效的回文
////                if( nstr.charAt(i)=='#'&& p[i]%2!=0){//#这个位置是奇数，要加1
////                   half+=1;
////                }
//                for(int k=0;k<half;k++){
//                    String temp=str.substring(Math.max(i/2-1-k,0),i/2+k);
//                    subList.add(temp);
//                }
//            }
        }
        System.out.println("ans:"+ans);
        System.out.printf("count2:"+count2);
        subList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        return str.substring((maxPoint-maxLen)/2,(maxPoint+maxLen)/2-1);
    }

    /***
     * dp[i][j] 定义为 i,j 是否构成回文
     *      basecase i==j
     *          i=j-1；
     *
     * @param str
     * @return
     */
    public static int dpCountSubStrings(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int len=str.length(),count=0;
        boolean[][]dp=new  boolean[len][len];
        for(int j=0;j<len;j++){
            for(int i=0;i<=j;i++){
               if(i==j){
                   count++;
                   dp[i][j]=true;
               }else if(j-i==1 && str.charAt(i)==str.charAt(j)){
                   count++;
                   dp[i][j]=true;
               }else if(j-i>1 && str.charAt(i)==str.charAt(j) && dp[i+1][j-1]){
                   count++;
                   dp[i][j]=true;
               }
            }
        }
        return count;
    }

    public static int dpCountSubStrings1(String str){
        if(str==null||str.length()<1){
            return 0;
        }
        int len=str.length(),count=0;
        boolean[]dp=new boolean[len];
        for(int j=0;j<len;j++){
            for(int i=0;i<=j;i++){
                if(i==j){
                    count++;
                    dp[i]=true;
                }else if(j-i==1 && str.charAt(i)== str.charAt(j)){
                    count++;
                    dp[i]=true;
                }else if(j-i>1 && str.charAt(i)==str.charAt(j) && dp[i+1]){
                    count++;
                    dp[i]=true;
                }else {
                    dp[i]=false;
                }
            }
        }
        return count;
    }

    public static void test(){
        System.out.println(manacher("abcabaa"));
        System.out.println(manacher1("abcabaa"));
        System.out.println(manacher2("abcabaa"));
        System.out.println(countSubstrings8("abcabaa"));
        System.out.println(countSubstrings("abcabaa"));
        System.out.println(countSubstrings8("a"));
        System.out.println(countSubstrings8("aa"));
        System.out.println(countSubstrings8("aba"));
        System.out.println(countSubstrings9("abcabaa"));
        System.out.println(countSubstrings10("abcabaa"));
        System.out.println(countSubstrings11("abcabaa"));
        System.out.println(isPalindrome(STR));
        System.out.println(isPalindrome2(STR));
        System.out.println(isPalindrome("ac"));
        System.out.println(isPalindrome2("ac"));
        System.out.println(countSubstrings12("abcabaa"));
    }
    public static void test2(){

        System.out.println(countSubstrings12("aba"));
        System.out.println(manacher2("aba"));
        System.out.println(manacher4("aba"));
        System.out.println(countSubstrings12("abcabaa"));
        System.out.println(manacher5("abcabaa"));
        System.out.println(manacher6("abcabaa"));
    }
    public static void test3(){

        System.out.println(countSubstrings12("aaa"));
        System.out.println(manacher6("aaa"));
        System.out.println(countSubstrings12("aa"));
        System.out.println(manacher6("aa"));
        System.out.println(countSubstrings12("aba"));
        System.out.println(manacher6("aba"));
    }
    public static void test4(){
        System.out.println(countSubstrings12("aaaa"));
        System.out.println(manacher6("aaaa"));
    }
    public static void main(String[] args) {
        System.out.println(countSubstrings12("aaaa"));
        System.out.println(dpCountSubStrings("aaaa"));
        System.out.println(dpCountSubStrings1("aaaa"));

    }
}
