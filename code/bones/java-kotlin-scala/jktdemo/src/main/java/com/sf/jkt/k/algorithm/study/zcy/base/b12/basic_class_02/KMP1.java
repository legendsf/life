package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_02;

public class KMP1 {
    /**
     * 查找 s包含m的位置
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s,String m){
        if (s==null||m==null||s.length()<1||m.length()<1||s.length()<m.length()){
            return -1;
        }
        char[]ss=s.toCharArray();
        char[]ms=m.toCharArray();
        int si=0;
        int mi=0;
        /***
            index 位置上放的是之前的字符串
            最大的前缀等于后缀的长度（规定：前缀不包含最后一个，后缀不包含第一个）
            0位置上规定为 -1
            1位置上规定 0

        **/
        int[]next=getNextArray(ms);
        while (si<ss.length&&mi<ms.length){
            if(ss[si]==ms[mi]){//相等都++
                si++;
                mi++;
            }else if(next[mi]==-1){//乙已经跳到开头了还是没有匹配上的
                /**
                 *  否认了从 0到j之间的位置能够配出来string2
                 */
                si++;//甲从下一个位置看看能不能配出来乙
            }else {
                mi=next[mi];//往前跳到前缀的下一个字符
            }
        }
        //乙已经划过所有的string2，那么说明已经找到整个string2了
        return mi==ms.length?si-mi:-1;
    }
    public static int[] getNextArray(char[] ms){
        if (ms.length==1){
            return new int[]{-1};
        }
        int[] next=new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int pos=2;
        int cn=0;//我跳到的位置
        while (pos<next.length){
            if(ms[pos-1]==ms[cn]){//pos前一个字符和跳到的cn字符相等，说明pos位置的值等于跳到位置的值加1,最长前缀长度加1
               next[pos++]=++cn;
            }else if (cn>0){//不等
                //cn 还可以往前跳,跳到的位置是next[cn]
                //当前cn=1，最后一次
               cn=next[cn];//next[cn]=0
            }else {//不等
                //不能往前跳，cn不大于0了,cn=0时候
                next[pos++]=0;
            }
        }
        return next;
    }

    public static int getIndexOf1(String s,String m){
        if (s==null||m==null||s.length()<1||m.length()<1||s.length()<m.length()){
            return -1;
        }
        char[] ss=s.toCharArray();
        char[] ms=m.toCharArray();
        int si=0;
        int mi=0;
        int[] next=getNextArray1(ms);
        while (si<s.length()&&mi<m.length()){
            if (ss[si]==ms[mi]){
                si++;
                mi++;
            }else if (next[mi]==-1){
              //不匹配，且mi已经跳到开头,那么si++，从下一个位置看能否匹配出完整的ms
                si++;
            }else {
                //不匹配,且能往前跳
                mi=next[mi];
            }
        }
        //mi划过所有的ms说明匹配上完整的m字符串
        return mi==m.length()?si-mi:-1;
    }

    public static int[]getNextArray1(char[] ms){
        if (ms.length==1){
            return new int[]{-1};
        }
        int[]next=new int[ms.length];
        next[0]=-1;
        next[1]=0;
        int pos=2;
        int cn=0;
        while (pos<ms.length){
            if (ms[pos-1]==ms[cn]){
                //当前pos前一个位置字符等于跳到的位置的字符说明，当前的最大前缀长度可以扩1个
                next[pos++]=++cn;
            }else if (cn>0){//最多只能跳到cn=1，cn等于1时，next[1]=0;
               cn=next[cn];
               //最后一次出来cn=0
            }else {
                //不匹配，并且cn=0，那么说明从当前位置往前算没有最长前缀长度
                next[pos++]=0;
            }
        }
        return next;
    }

    public static int getIndexOf2(String s,String m){
        if (s==null||m==null||s.length()<1||m.length()<1||s.length()<m.length()){
            return -1;
        }
        char[]ss=s.toCharArray();
        char[]ms=m.toCharArray();
        int si=0;
        int mi=0;
        int[] next=getNextArray2(ms);
        while (si<s.length()&&mi<m.length()){
            if (ss[si]==ms[mi]){
                si++;
                mi++;
            }else if (next[mi]==-1){
               si++;
            }else {
                mi=next[mi];
            }
        }
        return mi==m.length()?si-mi:-1;
    }

    public static int[] getNextArray2(char[] ms){
       if (ms.length==1){
           return new int[]{-1};
       }
       int[]next=new int[ms.length];
       next[0]=-1;
       next[1]=0;
       int pos=2;
       int cn=0;
       while (pos<ms.length){
           if (ms[pos-1]==ms[cn]){
               next[pos++]=++cn;
           }else if (cn>0){//最后cn等于1,这个if走完后cn为-1 next[cn]=-1
               //可以往前跳
               cn=next[cn];
           }else {
               //不能往前跳，并且最长前缀为0
              next[pos++]=0;
           }
       }
       return next;
    }

    public static void test1(){
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
        System.out.println(getIndexOf1(str, match));
        System.out.println(getIndexOf2(str, match));
    }

    public static void main(String[] args) {
       test1();
    }
}
