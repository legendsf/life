package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class Msort {
    public  static int[] bubbleSort(int[] arr){
       if(arr==null||arr.length<2){
           return arr;
       }

       for(int end=arr.length-1;end>0;end--){
           for(int i=0;i<end;i++){
               if(arr[i]>arr[i+1]){
                   Constants.swap(arr,i,i+1);
               }
           }
       }

       return arr;
    }

    public static int[] selectionSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                minIndex=arr[minIndex]<arr[j]?minIndex:j;
            }
            Constants.swap(arr,i, minIndex);
        }
        return arr;
    }



    public static int[] bubbleSortM1(int[] arr){
       for(int end=arr.length-1;end>0;end--){
           for(int i=0;i<end;i++){
               if(arr[i]>arr[i+1]){
                   Constants.swap(arr,i,i+1);
               }
           }
       }
       return arr;
    }

    public static int[] selectSort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
               minIndex=arr[j]>arr[minIndex]?minIndex:j;
            }
            Constants.swap(arr,minIndex,i);
        }
        return arr;
    }

    public static int[] insertSort(int[] arr){
       for(int i=1;i<arr.length;i++){
          for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
              Constants.swap(arr,j,j+1);
          }
       }
       return arr;
    }


    public static int[] mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return arr;
        }
        return mergeSort(arr,0,arr.length-1);
    }

    public static int[] mergeSort(int[] arr,int l,int r){
        if(l==r){
            return arr;
        }
        int mid=l+((r-l)>>1);
        mergeSort(arr,l,mid);
        mergeSort(arr,mid+1,r);
        return   merge(arr,l, mid, r);
    }

    public static int[] merge(int[] arr,int l,int mid,int r){
      int[] help=new int[r-l+1];
      int i=0;
      int p0=l;
      int p2=mid+1;
      while (p0<=mid&&p2<=r){
        help[i++]=arr[p0]>arr[p2]?arr[p2++]:arr[p0++];
      }
      while (p0<=mid){
          help[i++]=arr[p0++];
      }
      while (p2<=r){
          help[i++]=arr[p2++];
      }
      for(i=0;i<help.length;i++){
          arr[l+i]=help[i];
      }
      return arr;
    }

    public static void   quickSort(int[] arr){
       if(arr==null||arr.length<2){
           return ;
       }
       quickSort(arr,0,arr.length-1);
    }

    public static void   quickSort(int[] arr,int l ,int r){
        if(l<r){
           Constants.swap(arr,l+(int)(Math.random()*(r-l+1)),r);
           int[] partion=partion(arr,l,r);
           quickSort(arr,l,partion[0]-1);
           quickSort(arr,partion[1]+1,r);
        }
    }

    public static int[] partion(int[] arr,int l,int r){
        int tmp=arr[r];
        int less=l-1;
        int more=r+1;
        while (l<more){
            if(arr[l]<tmp){
                Constants.swap(arr,++less,l++);
            }else if(arr[l]>tmp){
                Constants.swap(arr,--more,l);
            }else {
                l++;
            }
        }
        return new int[]{l+1,more-1};
    }

    public static int[] partionByEnd(int[] arr,int l,int r){
        int less=l-1;
        int more=r;
        while (l<more){
            if(arr[l]<arr[r]){
                Constants.swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                Constants.swap(arr,--more,l);
            }else {
                l++;
            }
        }
        Constants.swap(arr,more,r);
        return new int[]{l+1,more};
    }





        public static void main(String[] args) {
        int[] arr = new int[]{1,7,9,8};
        Constants.swap(arr,3,1);
//        Constants.printArray(arr);
//        Constants.checkSort(Msort::bubbleSort);
//            Constants.checkSort(Msort::bubbleSortM1);
//            Constants.checkSort(Msort::selectionSort);
//            Constants.checkSort(Msort::insertSort);
//            Constants.checkSort(Msort::mergeSort);
            Constants.checkSort(Msort::quickSort);

        }
}
