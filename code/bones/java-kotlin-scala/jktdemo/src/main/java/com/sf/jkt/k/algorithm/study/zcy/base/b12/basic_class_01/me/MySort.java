package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class MySort {
    /***
     * 两两交换，大的沉下去，小的浮上来
     * @param arr
     */
    public static void  sortBubble(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int e=arr.length-1;e>0;e--){
           for(int i=0;i<e;i++){
               if(arr[i]>arr[i+1]){
                   swap1(arr,i,i+1);
               }
           }
        }
    }
    //异或交换数组元素的坑
    public static void swap(int[] arr,int i,int j){
        if(i==j){
            return;
        }
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[j]^arr[i];
        arr[i]=arr[i]^arr[j];
    }

    public static void swap1(int[]arr ,int i,int j){
        int temp=arr[i];
        arr[i]=arr[j];
        arr[j]=temp;
    }

    public static void sortBubble1(int[]arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int e=arr.length-1;e>0;e--){
            for(int i=0;i<e;i++){
                if(arr[i]>arr[i+1]){
                    swap(arr,i,i+1);
                }
            }
        }
    }


    public static void sortInsert(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for (int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static void sortInsert1(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int i=1;i<arr.length;i++){
            for(int j=i-1;j>=0&&arr[j]>arr[j+1];j--){
              swap(arr,j,j+1);
            }
        }
    }

    public static void sortSelect(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }

        for (int i=0;i<arr.length-1;i++){
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
               minIndex=arr[j]<arr[minIndex]?j:minIndex;
            }
            swap(arr,i,minIndex);
        }

    }

    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }

    public static void sortQuick(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
//        sortQuick(arr,0,arr.length-1);
        sortQuick1(arr,0,arr.length-1);
    }

    public static void sortQuick(int[]arr,int l,int r){
       if(l<r){
           //随机一个交换给 r,然后用 r 来 partion
           swap(arr,l+(int)(Math.random()*(r-l+1)),r);
           int[] p=partion(arr,l,r);
           sortQuick(arr,l,p[0]-1);//小于区域递归
           sortQuick(arr,p[1]+1,r);
       }
    }

    /**
     * 根据 r 做 partion,小于放左边，等于在中间，大于在右边
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int[] partion(int[]arr,int l,int r){
        int less=l-1;
        int more=r;
        while (l<more){//l 的增长不能超过 More,l 代表中间区域
            if(arr[l]<arr[r]){
                //less 区域扩大一位,交换给 less 区域 最后一个
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                //从右往左，more 最后一个扩大一位后和当前的 l 交换，如果交换后还是大于 r,则下一次More区域继续扩大
               swap(arr,--more,l);
            }else {
                //相等，则 less 区域和 more 区域保持不变，跳到下一个元素继续判断，中间就是等于区
                l++;
            }
        }
        //由于 r 自身没有参与交换
        //more的左边第一个肯定比 r 大或者等于,把 r 放到等于区
        swap(arr,more,r);
        //此时less 区域加 1即为等于区域,上一步 More 和 r 交换位置，那么等于区域的有边界就包含 r
        return new int[]{less+1,more};
    }
    public static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[] { less + 1, more };
    }


    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
            int[] p = partion(arr, l, r);
            quickSort(arr, l, p[0] - 1);
            quickSort(arr, p[1] + 1, r);
        }
    }

    public static void sortQuick1(int[] arr,int l,int r){
        if(l<r){
            // [L,R]区域含边界,之间的任何一个数跟 r 交换
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            //根据 r 进行分区
            int[]p=partition1(arr,l,r);
            sortQuick1(arr,l,p[0]-1);
            sortQuick1(arr,p[1]+1,r);
        }
    }
    public static int[] partition1(int[] arr,int l,int r){
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

    public static final void sortMerge(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        sortMerge(arr,0,arr.length-1);
    }
    public static void sortMerge(int[]arr,int l,int r){
        if(l==r){
            return;
        }
        int mid=l+((r-1)>>1);
        quickSort(arr,l,mid);
        quickSort(arr,mid+1,r);
        merge(arr,l,mid,r);
    }

    public static void merge(int[]arr,int l,int mid,int r){
        int[] help=new int[r-l+1];
        int i=0;
        int p0=l;
        int p1=mid+1;
        while (p0<=mid&&p1<=r){
           help[i++]=arr[p0]<arr[p1]?arr[p0++]:arr[p1++];
        }
        while (p0<=mid){
            help[i++]=arr[p0++];
        }

        while (p1<=r){
            help[i++]=arr[p1++];
        }

        for(int j=0;j<help.length;j++){
            arr[l+j]=help[j];
        }

    }

    public static void sortMerge1(int[] arr){
       if(arr==null||arr.length<2){
           return;
       }
       sortMerge1(arr,0,arr.length-1);
    }

    public static final void sortMerge1(int[]arr,int l,int r){
        if(l==r){
            return;
        }
        int mid=l+((r-l)>>1);
        sortMerge1(arr,l,mid);
        sortMerge1(arr,mid+1,r);
        merge1(arr,l,mid,r);
    }
    public static void merge1(int[]arr,int l,int mid,int r){
        //[l,mid] [mid+1,r]->[l,r]
        int[] help=new int[r-l+1];
        int i=0;
        int p1=l;
        int p2=mid+1;
        while (p1<=mid&&p2<=r){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        while (p2<=r){
            help[i++]=arr[p2++];
        }
        for(int j=0;j<help.length;j++){
            arr[l+j]=help[j];
        }
    }

    public static void sortHeap(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        //构造堆
        for(int i=0;i<arr.length;i++){
            heapInsert(arr,i);
        }
        //堆顶和堆底换位，再保持堆的性质
        int size=arr.length;
        swap(arr,0,--size);
        while (size>0){
            heapify(arr,0,size);
            swap(arr,0,--size);
        }
    }

    public static void heapInsert(int[]arr,int index){
        while (arr[index]>arr[(index-1)/2]){//子节点比父节点大就要交换
           swap(arr,index,(index-1)/2);
           index=(index-1)/2;//递归子树是否满足大顶堆
        }
    }
    //确保交换后,并且除去最后一个最大元素的其他，继续保持大顶堆的性质
    public static void heapify(int[]arr,int index,int size){
        int left=2*index+1;
        while (left<size){//肯定有一个子树，不确定是否有其他子树
           int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;//最大一个子树
            largest=arr[largest]>arr[index]?largest:index;//index 和 largest 比较再找出最大值
            if(largest==index){
                //说明此时树已经满足最大堆性质，不用交换，也不用继续向下再进行比对，因为之前的子树是符合大顶堆的性质的
                break;
            }
            swap(arr,largest,index);
            index=largest;//继续判断交换后的子树是否符合大顶堆性质
            left=2*index+1;
        }
    }

    public static void sortHeap1(int[]arr){
        if(arr==null||arr.length<2){return;}
        //构造大顶堆
         for(int i=0;i<arr.length;i++){
             heapInsert1(arr,i);
         }
        //交换最后一个
        int size=arr.length;
         swap(arr,0,--size);//最后一个已经是最大值了
        //循环 heapify 和交换最后一个
        while (size>0){//会有--size操作，一直换到剩下最后一个元素为止
            heapify1(arr,0,size);
            swap(arr,0,--size);//
        }
    }
    public static void heapInsert1(int[]arr,int index){
        while (arr[index]>arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index=(index-1)/2;
        }
    }
    public static void heapify1(int[]arr,int index,int size ){
        int left=2*index+1;//子树
        while (left<size){
            int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;
            largest=arr[largest]>arr[index]?largest:index;
            if(largest==index){
                break;
            }
            swap(arr,largest,index);
            index=largest;
            left=2*index+1;
        }
    }

    public static void main(String[] args) {
        System.out.println((int)0.7);
        System.out.println((int)Math.random());//int 取整舍去小数部分
        int l=1;
        int r=2;
        System.out.println(l+(int)(Math.random()*(r-l+1)));//返回[l,r]之间的一个数，不会越界
        int[] arr=new int[]{2,2};
        swap(arr,0,0);
        System.out.println(""+arr[0]+" "+arr[1]);
        Constants.checkSort(MySort::sortHeap1);
//        Constants.checkSort(Code_00_BubbleSort::bubbleSort);
//        Constants.checkSort(MySort::sortBubble1);
//        Constants.checkSort(MySort::sortInsert1);
//        Constants.checkSort(MySort::sortSelect);
//        Constants.checkSort(MySort::selectionSort);
//        Constants.checkSort(Code_02_SelectionSort::selectionSort);
    }
}
