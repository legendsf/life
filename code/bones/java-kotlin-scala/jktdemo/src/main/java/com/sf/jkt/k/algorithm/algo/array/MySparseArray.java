package com.sf.jkt.k.algorithm.algo.array;

public class MySparseArray {

    public static int[][] sourceArray(){
        //初始化原数组
        int[][] array = new int[11][11];
        array[1][2] = 1;
        array[2][4] = 2;
        for(int[] row : array){
            for(int item : row){
                System.out.printf("%d\t",item);
            }
            System.out.println();
        };
        return array;
    }

    public static int[][] toSparseArray(int[][] sources){
        int sum=0;
        for(int i=0;i<sources.length;i++){
            for(int j=0;j<sources[0].length;j++){
                if(sources[i][j]!=0){
                    sum ++;
                }
            }
        }
        int[][] sparseArray=new int[sum+1][3];
        sparseArray[0]= new int[]{sources.length,sources[0].length,sum};
        int count=0;
        for(int i=0;i<sources.length;i++){
            for (int j=0;j<sources[0].length;j++){
                if(sources[i][j]!=0){
                    count++;
                    sparseArray[count][0]=i;
                    sparseArray[count][1]=j;
                    sparseArray[count][2]=sources[i][j];
                }
            }
        }
        return sparseArray;
    }
    public static int[][] fromSparseArray(int[][] sparseArray){
        if(sparseArray==null){
            return null;
        }
        int[][] old=new int[sparseArray[0][0]][sparseArray[0][1]];
        for(int i=1;i<=sparseArray[0][2];i++){
            old[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }
        return old;
    }

    public static void printArray(int[][] arr){
        if(arr==null||arr.length<1){
            return;
        }
        for(int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                System.out.printf("%d\t",arr[i][j]);
            }
            System.out.println();
        }
    }


    public static void testSparseArray(){
        int[][] sourceArray=sourceArray();
        int[][] spareArray=toSparseArray(sourceArray);
        int[][] source2=fromSparseArray(spareArray);
        System.out.println("-----");
        printArray(source2);
        if(!isEqual(sourceArray,source2)){
            System.out.println("not equals");
        }
    }

    public static boolean isEqual(int[][] arr1,int[][] arr2){
        if(arr1==null&&arr2==null){
            return true;
        }
        if((arr1!=null&&arr2==null)||(arr1==null&&arr2!=null)){
            return false;
        }
        if(arr1.length!=arr2.length || arr1[0].length!=arr2[0].length){
            return false;
        }
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr1[0].length;j++){
                if((arr1[i][j]^arr2[i][j])!=0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        testSparseArray();
    }

}
