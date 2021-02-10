package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class MySort6 {
    //快排
    public static void sortQuick(int[] arr){
       if(arr==null||arr.length<2){
           return;
       }
       sortQuick(arr,0,arr.length-1);
    }
    public static void sortQuick(int[]arr,int l,int r){
        if(l<r){
            //[l,r]和 r 进行交换
           swap(arr,l+(int)((r-l+1)*Math.random()),r);
           //用 r 作为分组依据<=>
           int[] equalArea= partition(arr,l,r);
           sortQuick(arr,l,equalArea[0]-1);
           sortQuick(arr,equalArea[1]+1,r);
        }
    }
    public static int[] partition(int[] arr,int l,int r){
        int less=l-1;
        int more=r;
        while (l<more){
            if(arr[l]<arr[r]){
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                swap(arr,--more,l);
            }else {
                l++;
            }
        }
        swap(arr,more,r);
        return new int[]{less+1,more};
    }


    //堆排
    public static void sortHeap(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        //构建大顶堆
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        int size=arr.length;
        swap(arr,0,--size);//根和最后一个交换
        //循环保持堆和交换
        while (size>0){//size=1时候是最后一次交换
           heapify(arr,0,size);
           swap(arr,0,--size);
        }
    }

    public static void heapInsert1(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void heapify1(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if (largest == index) {
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr,int index){
        //从后往前保持大根堆性质
        while (arr[index]>arr[(index-1)/2]){//index=0会自动终止 arr[0] !> arr[0]
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void heapify(int[] arr,int index,int size){
        //从堆顶到叶结点判断是否满足大顶堆，不满足则调整
        int left=index*2+1;
        while (left<size){
            int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;
            largest=arr[largest]>arr[index]?largest:index;
            if(largest==index){
                //已经满足大根堆
                break;
            }
            swap(arr,index,largest);
            //下一次循环
            index=largest;
            left=index*2+1;
        }
    }

    //归并
    public static void sortMerge(int[] arr){
      if(arr==null||arr.length<2){return;}
      sortMerge(arr,0,arr.length-1);
    }
    public static void sortMerge(int[] arr,int l,int r){
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        sortMerge(arr,l,mid);
        sortMerge(arr,mid+1,r);
        merge(arr,l,mid,r);
    }
    public static void merge(int[] arr,int l,int mid,int r){
       //[l,mid] [mid+1,r]
        int[] help = new int[r-l+1];
        int p1=l;
        int p2=mid+1;
        int index=0;
        while (p1<=mid&&p2<=r){
            help[index++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[index++]=arr[p1++];
        }
        while (p2<=r){
            help[index++]=arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[l+j]=help[j];
        }
    }

    //基数
        public static void sortRadix(int[] arr){
            if(arr==null||arr.length<2){return;}
            int max=arr[0];
            for(int i=1;i<arr.length;i++){
                max=Math.max(max,arr[i]);
            }
            int weishu=0;
            while (max!=0){
                weishu++;
                max /= 10;
            }
            int[][] buckets=new int[10][arr.length];
            int[] counts=new int[10];
            //存数据
            for(int i=0,n=1;i<weishu;i++,n *= 10){//第一轮按照个位数排序
                for(int j=0;j<arr.length;j++){
                        int a = arr[j] / n % 10;//分离出要按照此位置的数来进桶
                        buckets[a][counts[a]] = arr[j];//
                        counts[a]++;
                }

                //取数据
                int index=0;
                for(int j=0;j<buckets.length;j++){
                    for(int k=0;k<counts[j];k++){//
                        arr[index++] = buckets[j][k];//第 j 行第 K个值
                    }
                    counts[j]=0;//清空有效数
                }

            }
        }


    //计数 positive
        public static void sortCount(int[] arr){
            if(arr==null||arr.length<2){
                return;
            }
           //计算桶的大小
            int max=Integer.MIN_VALUE;
            for(int i=0;i<arr.length;i++){
                max=Math.max(max,arr[i]);
            }
            int[] buckets = new int[max+1];
            //存数据
            for(int i=0;i<arr.length;i++){
                buckets[arr[i]]++;
            }

            //取数据
            int index=0;
            for(int i=0;i<buckets.length;i++){
                while (buckets[i]-->0){
                    arr[index++]=i;
                }
            }
        }
    //冒泡
        public static void sortBubble(int[] arr){
            if(arr==null||arr.length<2){
                return;
            }
            for(int end=arr.length-1;end>0;end--){
                for(int j=0;j<end;j++){
                    if(arr[j]>arr[j+1]){
                        swap(arr,j,j+1);
                    }
                }
            }
        }
    //插入
        public static void sortInsert(int[] arr){
            if(arr==null||arr.length<2){
                return;
            }
            for(int i=1;i<arr.length;i++){
                for(int j=i-1;j>=0;j--){
                   if(arr[j]>arr[j+1]){
                       swap(arr,j,j+1);
                   }
                }
            }
        }
    //选择
    public static void sortSelect(int[] arr){
       if(arr==null||arr.length<2){
           return;
       }
       for(int i=0;i<arr.length-1;i++){
           int minIndex=i;
           for(int j=i+1;j<arr.length;j++){
               minIndex=arr[minIndex]<arr[j]?minIndex:j;
           }
           swap(arr,minIndex,i);
       }
    }


    public static final void swap(int[] arr,int i,int j){
        if(i==j){return;}
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }


    public static void main(String[] args) {
//        Constants.checkSort(MySort6::sortQuick);
//        Constants.checkSort(MySort6::sortHeap);
//        Constants.checkSort(MySort6::sortMerge);
//        Constants.checkSort(MySort6::sortBubble);
//        Constants.checkSort(MySort6::sortInsert);
//        Constants.checkSort(MySort6::sortSelect);
//        Constants.checkSortPositive(MySort6::sortCount);
        Constants.checkSortPositive(MySort6::sortRadix);

    }

}
