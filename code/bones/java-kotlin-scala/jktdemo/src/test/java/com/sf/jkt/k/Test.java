package com.sf.jkt.k;

public class Test {

    public int[] mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
       return   mergeSort(arr,0,arr.length-1);
    }

    public int[] mergeSort(int[] arr,int left,int end){
        int mid= left +((end-left)>>1);
        if(left>mid||left>end){
            return new int[]{arr[left]};
        }
       int[] leftArr=  mergeSort(arr,left,mid);
       int[] rightArr=  mergeSort(arr,mid+1,end);
       return merge(leftArr,rightArr);
    }

    public int[] merge(int[] leftArr,int[] rightArr){
        int[] help=new int[leftArr.length+rightArr.length];
        int i=0;
        int j=0;
        int k=0;
        while (i<leftArr.length&& j<rightArr.length){
            help[k++]= leftArr[i]<rightArr[j]?leftArr[i++]:rightArr[j++];
        }
        while (i<leftArr.length){
            help[k++]=leftArr[i++];
        }
        while (j<leftArr.length){
            help[k++]=rightArr[j++];
        }
        return help;
    }

    public static void main(String[] args) {
         int[] arr= new int[]{ 0,9,3,5,8};
         int[] result= new Test().mergeSort(arr);
         for(int i=0;i<result.length;i++){
             System.out.println(result[i]);
         }
    }
}
