package com.sf.jkt.k.algorithm.algo.m1.algo.type;

import java.util.Arrays;

/**
 * 贪心算法小结
 * https://www.pianshen.com/article/192366823/
 */
public class Greedy {
    /***
     * A = [2,3,1,1,4]
     * https://blog.csdn.net/baidu_33604078/article/details/78935679
     * @param nums
     * @return
     */
 public static    int jump(int[] nums)
    {
        if(nums==null) {
            return 0;
        }
        // 使用cur来记录当前步的最远到达位置，使用pre来记录上一步最远到达的位置
        int cur = 0, pre = 0;
        // res为需要走的步数
        int res = 0;
        int i=0;
        while(cur < nums.length-1)
        {
            pre = cur;
            while(i <= pre)
            {
                // 寻找这一步中最远可以到达的位置
                cur = cur < nums[i] +i ? nums[i] +i : cur;
                ++i;
            }
            // 步数+1
            ++res;
            // 加上pre==cur判断，如果上一步和这一步走的最远位置一样，说明不能到达更远了，结束了。
            if(pre == cur) {
                return -1;
            }
        }

        return res;
    }

    public static int jump1(int[] arr){
        if(arr==null||arr.length<1){
            return 0;
        }
        int prev=0,cur=0,res=0,i=0;
        while (cur<arr.length-1){
            prev=cur;
            while (i<=prev){
                cur=cur<arr[i]+i?arr[i]+i:cur;
                i++;
            }
            res++;
            if(prev==cur){
                return -1;
            }
        }
        return res;
    }

    /**
     * 找零钱
     * @param m
     * @param n
     * @return
     */
    public static int[] chargeMoney(int[]m,int n){
        Integer[]marr=new Integer[m.length];
        int k=m.length;
        for(int i=0;i<k;i++){
            marr[i]=m[i];
        }
        Arrays.sort(marr,(m1,m2)->{
            return m2-m2;
        });
        int[]res=new int[k];
        for(int i=0;i<k;i++){
            res[i]=n/marr[i];
            n %=marr[i];
        }
        return res;
    }

    /**
     * 分饼干
     * https://blog.csdn.net/zhulianwei1999/article/details/104478479
     * @return
     */
    public static int findChildren(int[]children,int[]cookies){
        Arrays.sort(children);
        Arrays.sort(cookies);
        int child=0,cookie=0;
        while (child<children.length&&cookie<cookies.length){
            if(children[child]<=cookies[cookie]){
                child++;
            }
            cookie++;
        }
        return child;
    }

    public static int findChildren1(int[] children,int[]cookies){
        Arrays.sort(children);
        Arrays.sort(cookies);
        int child=0,cookie=0;
        while (child<children.length&&cookie<cookies.length){
            if (children[child]<=cookies[cookie]){
                child++;
            }
            cookie++;
        }
        return child;
    }



    /**
     * 区间覆盖
     * @param sz
     * @param k
     * @return
     */
    public static int line(int[]sz,int k ){
        Arrays.sort(sz);
        int number=1;
        int temp=sz[0];
        for(int i=0;i<sz.length;i++)
        {
            if(sz[i]-temp>k)
            {
                temp=sz[i];
                number++;
            }
        }
        System.out.println(number);
        return number;
    }

    public static int jump2(int[] nums){
        if (nums==null||nums.length<1){
            return 0;
        }
        int cur=0,pre=0,res=0,i=0;
        while (cur<nums.length-1){
            pre=cur;
            while (i<=pre){
                cur=cur<nums[i]+i?nums[i]+i:cur;
                i++;
            }
            ++res;
            if (pre==cur){
                return -1;
            }
        }
        return res;
    }

    public static int jump3(int[] arr){
        int res=0,i=0,pre=0,cur=0;
        while (cur<arr.length-1){
            pre=cur;
            while (i<=pre){
                cur=cur<arr[i]+i?arr[i]+i:cur;
                i++;
            }
            ++res;
            if (pre==cur){
                return -1;
            }
        }
        return res;
    }

    public static void test1(){
       int[] arr={2,3,1,2,4};
         arr=new int[]{2,3,1,0,4};
//       arr=new int[]{2,2,1,2,4};
        System.out.println(jump1(arr));
        System.out.println(jump2(arr));
    }
    public static void testCharge(){
        int[] m={25,10,5,1};
        int[] res=chargeMoney(m,99);
        System.out.println(Arrays.toString(res));
    }


    public static void test2(){
        int[] children={1,2,3};
        int[]cookies={1,1};
        System.out.println(findChildren(children, cookies));//1
        cookies=new int[]{1,2};
        System.out.println(findChildren(children, cookies));//2

    }
    public static void test3(){
        int[] sz={1,2 ,3, 4 ,5, -7, 100};
        int k=3;
        line(sz,3);
    }

    public static void main(String[] args) {
        test1();
    }

}
