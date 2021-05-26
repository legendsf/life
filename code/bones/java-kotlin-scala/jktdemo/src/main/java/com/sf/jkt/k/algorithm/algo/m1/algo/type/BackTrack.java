package com.sf.jkt.k.algorithm.algo.m1.algo.type;

import com.sf.jkt.j.Solution;

import java.util.*;

public class BackTrack {

    public static void arrange(int[] a,int k){
        if(k==a.length-1){
            display(a);
            return;
        }
        for(int i=k;i<a.length;i++){
            int temp=a[i];
            a[i]=a[k];
            a[k]=temp;
            arrange(a,k+1);
            temp=a[i]; //恢复，以供下一次循环
            a[i]=a[k];
            a[k]=temp;
        }
    }
    public static void display(int[] arr){
        System.out.println(Arrays.toString(arr));
    }


    public static List<List<Integer>> permute(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        if(nums==null||nums.length<1){
            return  res;
        }
       int len=nums.length;
        boolean[] used=new boolean[len];
        Deque<Integer> path=new ArrayDeque<>();
        dfs(nums,len,0,path,used,res);
        return res;
    }

    public static void  dfs(int[] nums,int len,int depth,Deque<Integer>path,boolean[] used,
                            List<List<Integer>> res){
       if(depth==len){
           res.add(new ArrayList<>(path));
           return;
       }
       for (int i=0;i<len;i++){
           System.out.println("dfs__________");
           if(!used[i]){
               path.add(nums[i]);
               used[i]=true;
               System.out.println(" before=> "+path);
               dfs(nums,len,depth+1,path,used,res);
               used[i]=false;
               path.removeLast();
               System.out.println(" after=> "+path);
           }
       }
        System.out.println("dfs------");
    }

    public static List<List<Integer>> permute1(int[] nums){
        List<List<Integer>> res=new ArrayList<>();
        if(nums==null||nums.length<1){
            return res;
        }
        boolean[] used=new boolean[nums.length];
        Deque<Integer> path=new ArrayDeque<>();
        dfs(nums,nums.length,0,path,used,res);
        return  res;
    }
    public static void dfs1(int[]nums,int len,int depth,Deque<Integer> path,boolean[] used,List<List<Integer>> res){
        if(depth==len){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=0;i<len;i++){
            if(!used[i]){
                path.add(nums[i]);
                used[i]=true;
                System.out.println(" before=>"+path);
                dfs(nums,len,depth+1,path,used,res);
                used[i]=false;
                path.removeLast();
                System.out.println(" after=>"+path);
            }
        }
    }


    public List<List<Integer>> permute2(int[] nums) {
        // 首先是特判
        int len = nums.length;
        // 使用一个动态数组保存所有可能的全排列
        List<List<Integer>> res = new ArrayList<>();

        if (len == 0) {
            return res;
        }

        boolean[] used = new boolean[len];
        List<Integer> path = new ArrayList<>();

        dfs2(nums, len, 0, path, used, res);
        return res;
    }

    private void dfs2(int[] nums, int len, int depth,
                     List<Integer> path, boolean[] used,
                     List<List<Integer>> res) {
        if (depth == len) {
            // 3、不用拷贝，因为每一层传递下来的 path 变量都是新建的
            res.add(path);
            return;
        }

        for (int i = 0; i < len; i++) {
            if (!used[i]) {
                // 1、每一次尝试都创建新的变量表示当前的"状态"
                Deque<Integer> newPath = new ArrayDeque<>(path);
                newPath.add(nums[i]);

                boolean[] newUsed = new boolean[len];
                System.arraycopy(used, 0, newUsed, 0, len);
                newUsed[i] = true;

                dfs(nums, len, depth + 1,  newPath, newUsed, res);
                // 2、无需回溯
            }
        }
    }



    public static void test1(){
        int[] a = new int[] { 1, 2, 3, 4, 5 };
        arrange(a,0);
    }
    public static void test2(){
        int[] nums = {1, 2,3};
        Solution solution = new Solution();
        List<List<Integer>> lists = permute1(nums);
        System.out.println(lists);

    }

    public static void main(String[] args) {
        test2();

    }
}
