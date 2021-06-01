package com.sf.jkt.k.algorithm.algo.m1.base;

import java.util.ArrayList;
import java.util.List;

public class SparseArray1 {

    public static int[][] toSparseArray1(int[][] arr){
        if (arr==null||arr.length<1||arr[0].length<1){
            return null;
        }
        int sum=0;
        List<int[]> list=new ArrayList<>();
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                if (arr[i][j]!=0){
                    sum++;
                    list.add(new int[]{i,j,arr[i][j]});
                }
            }
        }
        int[][]sparseArray=new int[sum+1][3];
        sparseArray[0]=new int[]{arr.length,arr[0].length,sum};
        for (int i=1;i<=sum;i++){
            sparseArray[i]=list.get(i-1);
        }
        return sparseArray;
    }

    public static int[][] toSparseArray(int[][] arr){
        if (arr==null||arr.length<1||arr[0].length<1){
            return null;
        }
        int sum=0;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr.length;j++){
                if(arr[i][j]!=0){
                    sum++;
                }
            }
        }
        int[][] ans=new int[sum+1][3];
        ans[0]=new int[]{arr.length,arr[0].length,sum};
        int index=1;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                if(arr[i][j]!=0){
                   ans[index++]=new int[]{i,j,arr[i][j]};
                }
            }
        }
        return ans;
    }

    public static int[][] fromSparseArray(int[][] arr){
        if (arr==null||arr.length<1||arr[0].length<3){
            return null;
        }
        int ni=arr[0][0],nj=arr[0][1],sum=arr[0][2];
        int[][] ans=new int[ni][nj];
        for (int i=1;i<=sum;i++){
            ans[arr[i][0]][arr[i][1]]=arr[i][2];
        }
        return ans;
    }

    public static void printArray(int[][] arr){
        if(arr==null||arr.length<1||arr[0].length<1){
            System.out.println("nothing");
        }
        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                System.out.print(arr[i][j]+" ");
            }
            System.out.println();
        }
    }



    public static int[][]createArray(){
        /**
         * 初始化二维数组
         * <p>
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 1 0 0 0 0 0 0 0 0
         *     0 0 0 0 2 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         *     0 0 0 0 0 0 0 0 0 0 0
         * </p>
         */
        //初始化原数组
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][4] = 2;
        for(int[] row : array){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        return  array;
    }


    public static void main(String[] args) {
        int[][] sarr=createArray();
        printArray(sarr);
        int[][] sparr=toSparseArray(sarr);
        printArray(sparr);
        int[][] sarr1=fromSparseArray(sparr);
        printArray(sarr1);

    }

}
