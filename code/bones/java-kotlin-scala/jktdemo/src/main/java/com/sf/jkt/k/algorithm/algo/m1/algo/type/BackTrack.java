package com.sf.jkt.k.algorithm.algo.m1.algo.type;

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
        System.out.print(Arrays.toString(arr)+"   ");
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

    private  static void dfs2(int[] nums, int len, int depth,
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


    public static void arrange1(int[]a,int k){
        if (k==a.length){
            display(a);
            return;
        }
        for (int i=k;i<a.length;i++){
            swap(a,i,k);
            arrange(a,k+1);
            swap(a,i,k);
        }
    }
    public static void arrange2(int[]arr,int k){
        if (k==arr.length){
            display(arr);
            return;
        }
        for (int i=k;i<arr.length;i++){
            swap(arr,k,i);
            arrange2(arr,k+1);
            swap(arr,k,i);
        }
    }
    public static List<List<Integer>> permute3(int[] nums){
        int len=nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len==0){
            return res;
        }
        boolean[] used=new boolean[len];
        List<Integer> path=new ArrayList<>();
        dfs3(nums,len,0,path,used,res);
        return res;
    }
    public static void dfs3(int[] nums,int len,int depth,List<Integer> path,boolean[] used,List<List<Integer>> res){
        if (depth==len){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i=0;i<len;i++){
            if (!used[i]){
                path.add(nums[i]);
                used[i]=true;
                dfs3(nums,len,depth+1,path,used,res);
                used[i]=false;
                path.remove(path.size()-1);
            }
        }
    }

    public static List<List<Integer>> permute4(int[] arr){
        List<List<Integer>> ans=new ArrayList<>();
        if (arr==null||arr.length<1){
            return ans;
        }
        dfs4(arr,arr.length,0,new ArrayList<Integer>(),new boolean[arr.length],ans);
        return ans;
    }
    public static void dfs4(int[]arr,int len,int depth,List<Integer> path,boolean[]used,List<List<Integer>> ans){
       if (len==depth){
           ans.add(new ArrayList<>(path));
           return;
       }
       for (int i=0;i<arr.length;i++){
          if (!used[i]){
              path.add(arr[i]);
              used[i]=true;
              dfs4(arr,len,depth+1,path,used,ans);
              used[i]=false;
              path.remove(path.size()-1);
          }
       }
    }

    public static void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i]=arr[i]^arr[j];
            arr[j]=arr[i]^arr[j];
            arr[i]=arr[i]^arr[j];
        }
    }

    public static void test1(){
        int[] a = new int[] { 1, 2, 3, 4, 5 };
        arrange(a,0);
    }
    public static void test2(){
        System.out.println(permute1(new int[]{1, 2, 3}));
        System.out.println(permute3(new int[]{1, 2, 3}));
        System.out.println(permute4(new int[]{1, 2, 3}));
        arrange1(new int[]{1,2,3},0);
        System.out.println();
        arrange2(new int[]{1,2,3},0);
    }

    public static void main(String[] args) {
        test2();

    }
}
