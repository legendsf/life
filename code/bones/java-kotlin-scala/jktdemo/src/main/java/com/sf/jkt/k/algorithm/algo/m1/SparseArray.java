package com.sf.jkt.k.algorithm.algo.m1;

public class SparseArray {

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

    public static int[][]toSparseArray(int[][] inputs){
        if(inputs==null||inputs.length<1||inputs[0].length<1){
            return null;
        }
        int sum=0;
        for(int i=0;i<inputs.length;i++){
            for(int j=0;j<inputs[0].length;j++){
                if(inputs[i][j]!=0){
                    sum++;
                }
            }
        }
        int[][]result=new int[sum+1][3];
        int k=1;
        result[0]=new int[]{inputs.length,inputs[0].length,sum};
        for(int i=0;i<inputs.length;i++){
            for(int j=0;j<inputs[0].length;j++){
                if(inputs[i][j]!=0){
                    result[k][0]=i;
                    result[k][1]=j;
                    result[k][2]=inputs[i][j];
                    k++;
                }
            }
        }
        return result;
    }

    public static int[][]fromSparseArray(int[][] inputs){
        if(inputs==null||inputs.length<1||inputs[0].length<1){
            return null;
        }
        int row=inputs[0][0];
        int col=inputs[0][1];
        int nums=inputs[0][2];
        int[][]result=new int[row][col];
        for(int i=1;i<=nums;i++){
            result[inputs[i][0]][inputs[i][1]]=inputs[i][2];
        }
        return  result;
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


    public static void main(String[] args) {
        int[][] sarr=createArray();
        printArray(sarr);
        int[][] sparr=toSparseArray(sarr);
        printArray(sparr);
        int[][] sarr1=fromSparseArray(sparr);
        printArray(sarr1);

    }
}
