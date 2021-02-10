package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class MySort5 {
    public static void sortQuick(int[] arr){
       if(arr==null||arr.length<2){return;}

       sortQuick(arr,0,arr.length-1);
    }
    public static void sortQuick(int[] arr,int l,int r){
        if(l<r){
            //[l,r] swap r
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            //根据 r 做分区 <=>
            int[] p=partition(arr,l,r);
            sortQuick(arr,l,p[0]-1);
            sortQuick(arr,p[1]+1,r);
        }
    }

    public static int[] partition(int[]arr,int l,int r){
        int less=l-1;//less 区域在左边
        int more=r;//more 区域在右边
        while (l<more){//less 区域往右移动，如果碰到等于 r 的就是 l要往右移动,l 就是当前索引的数
          if(arr[l]<arr[r]){
              swap(arr,++less,l++);//less 向右移动，l 向右移动
          }else if(arr[l]>arr[r]){
              swap(arr,--more,l);//l区域右边靠 more来向左推动
          }else {
              l++;//相等则 l右移
          }
        }
        swap(arr,more,r);//原来的 r 放到 more区域第一个（从左往右数）
        return new int[]{less+1,more};
    }

    public static void sortMerge(int[] arr){
        if(arr==null||arr.length<2){return;}
        sortMerge(arr,0,arr.length-1);
    }
    public static void sortMerge(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int mid=l+ ((r-l)>>1);//(r+1)/2 可能越界
        sortMerge(arr,l,mid);
        sortMerge(arr,mid+1,r);
        merge(arr,l,mid,r);

    }

    /***
     *
     * @param arr
     * @param l
     * @param mid
     * @param r
     */
    public static void merge(int[] arr,int l,int mid,int r){
       int[]help = new int[r-l+1];
       int index=0;
       int p1=l;
       int p2=mid+1;
       while (p1<=mid&&p2<=r){
           help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
       }
       while (p1<=mid){
           help[index++]=arr[p1++];
       }
       while (p2<=r){
           help[index++]=arr[p2++];
       }
       for(int j=0;j< help.length;j++){
           arr[l+j]=help[j];
       }
    }



    public static void sortHeap(int[] arr){
        if(arr==null||arr.length<2){return;}
        //构建堆，大顶
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        //交换顶底
        int size=arr.length;
        swap(arr,0,--size);
        //循环 heapify 和交换顶底
        while (size>0){//=0栈顶最小
            heapify(arr,0,size);//[1,arr.len-2] 之间需要调整，每次调整 size减少一个
            swap(arr,0,--size);
        }

    }

    public static void heapInsert(int[] arr,int index){
        //因为是从后往前调整，所以要找到 index 的父亲,因为要保证一条路线的性质，所以用循环
        while (arr[index]>arr[(index-1)/2]){
            //比父亲大则一直交换
            swap(arr,index,(index-1)/2);
            //顺着这条路继续往上判断是否符合大根堆性质
            index=(index-1)/2;
        }
    }

    public static void heapify(int[]arr,int index,int size){
        //每次从 0 到 size 判断是否符合大顶堆，不符合就进行调整
        int left=2*index+1;
        while (left<size){//调整至多不能超过 size 的大小,否则终止
           //找到左右子树中最大的，有可能只存在一个,left 也有可能是左子树，右子树，树也有可能只包含一个
            int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;
            largest=arr[largest]>arr[index]?largest:index;//子树最大的和父节点再比对
            if(largest==index){
                //如果目前最大的就是父节点，说明不用进行调整了,从叶子到头结点都符合最大堆
                break;
            }
            swap(arr,index,largest);//交换最大的到堆顶
            //继续判断交换后的子树是否符合大顶堆
            index=largest;//目前 largest是新的父节点
            left=2*index+1;//新的子树
        }
    }

    public static void swap(int[] arr,int i,int j){
        if(i==j){return;}
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }

    public static void sortBubble(int[] arr){
        //大的交换到后面，小的浮起到头部
        for(int end=arr.length-1;end>0;end--){
            for(int i=0;i<end;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }

    public static void sortInsert(int[] arr){
       //在前面拍好顺序的序列里面从后面插入一个，所谓插入就是交换
        for(int i=1;i<arr.length;i++){//第 O 位排好序，现在要从[1,arr.len-1]往第 0 位置插入（交换）
            for (int j = i-1; j >=0&& arr[j]>arr[j+1] ; j--) {//从后往前插入，如果前一个比后一个大，那么要进行交换
               swap(arr,j,j+1);
            }

        }
    }

    public static void sortSelect(int[] arr){
        //从前往后，每次选择最小的一个 index 和最小区域进行交换，一直扩大最小区域，直到最后一个元素
        for(int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for (int j=i+1;j<arr.length;j++){
                //找到 i之后的最小值的 index
                minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            //最小值和 i 交换，然后最小区域 下一次循环扩大一位
            swap(arr,i,minIndex);
        }
    }

    public static void sortBucket(int[] arr){
        if(arr==null||arr.length<2){return;}
        //找到数组中最大值，根据最大值构造桶
        int max=Integer.MIN_VALUE;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
        }
        int[] buckets = new int[max+1];
        //把 arr的数填充到桶里面
        for (int i=0;i<arr.length;i++){
            buckets[arr[i]]++;
        }
        int i=0;
        for(int j=0;j<buckets.length;j++){
            while (buckets[j]-->0){
                arr[i++]=j;
            }
        }
    }

    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int[] bucket = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j;
            }
        }


    }

    public static void sortRadix(int[] arr){
        if(arr==null||arr.length<2){return;}
        int[][] buckets=new int[10][arr.length];
        int[] counts=new int[10];//存有效数字
        //N位数最大值是几位
        int max=0;
        for(int i=0;i<arr.length;i++){
            max=Math.max(max,arr[i]);
        }
        int weishu=(""+max).length();//N位数
        //最多几位数循环几轮，如果 N=4,那么第一次根据 个位排序，然后收集到 arr
        //再把个位排序后的 arr 按照 十位排序
        //再把arr按照百位
        //再按照千位排序，这样数组总体就有顺序了
        for(int i=0,n=1;i<weishu;i++,n *= 10){
            for(int j=0;j<arr.length;j++){
                //对每个数求出该位（个、十百千）数字
                int a=arr[j]/n%10;
                buckets[a][counts[a]]=arr[j];
                counts[a]++;
            }
            int index=0;
            for (int j=0;j<buckets.length;j++){
                for (int k=0;k<counts[j];k++){//k个有效数字
                   arr[index++]=buckets[j][k];
                }
                counts[j]=0;//收集完成后该位为空，等待下一轮收集
            }
        }

    }

    public static void sortRadix1(int[] arr){
       if(arr==null||arr.length<2){
          return;
       }
       int[] counts = new int[10];
       int[][] buckets = new int[10][arr.length];
       int max=arr[0];
       for(int i=0;i<arr.length;i++){
          max=Math.max(max,arr[i]);
       }
       int weishu=(""+max).length();
       for(int i=0,n=1;i<weishu;i++,n *=10){
           for(int j=0;j<arr.length;j++){
               int a=arr[j]/n%10;
               buckets[a][counts[a]]=arr[j];
               counts[a]++;
           }
           int index=0;
           for(int j=0;j<buckets.length;j++){
               for(int k=0;k<counts[j];k++){
                   arr[index++]=buckets[j][k];
               }
               counts[j]=0;
           }
       }
    }

    public static void main(String[] args) {
//        Constants.checkSort(MySort5::sortQuick);
//        Constants.checkSort(MySort5::sortMerge);
//        Constants.checkSort(MySort5::sortHeap);
//        Constants.checkSort(MySort5::sortBubble);
//        Constants.checkSort(MySort5::sortInsert);
//        Constants.checkSort(MySort5::sortSelect);
//        Constants.checkSortPositive(MySort5::sortBucket);
//        Constants.checkSortPositive(RadixSort::radixSort);
        Constants.checkSortPositive(MySort5::sortRadix);

    }
}
