package com.sf.jkt.k.algorithm.study.zcy.base.b12.basic_class_01.me;

import com.sf.jkt.k.algorithm.study.zcy.Constants;

public class Mysort7 {
    //冒泡
    public static void sortBubble(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        for(int e=arr.length-1;e>0;e--){
            for(int j=0;j<e;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }
    //插入
        public static void sortInsert(int[] arr){
            if(arr==null||arr.length<2){return;}
            for(int i=1;i<arr.length;i++){
                for(int j=i-1;j>=0;j--){
                    if(arr[j+1]<arr[j]){
                        swap(arr,j,j+1);
                    }
                }
            }
        }
    //选择
        public static void sortSelect(int[] arr){
            if(arr==null||arr.length<2){return;}
            for(int i=0;i<arr.length-1;i++){
                int minIndex=i;
                for (int j=i+1;j<arr.length;j++){
                   minIndex=arr[j]<arr[minIndex]?j:minIndex;
                }
                swap(arr,minIndex,i);
            }
        }
    //快排
        public static final void sortQuick(int[] arr){
           if(arr==null||arr.length<2){return;}
           sortQuick(arr,0,arr.length-1);
        }
        public static void sortQuick(int[] arr,int l,int r){
            if(l<r) {
                //交换 [l,r] r
                swap(arr, r, l + (int) (Math.random() * (r - l + 1)));
                //分区 等于区域
                int[] eq = partition(arr, l, r);
                //递归
                sortQuick(arr, l, eq[0] - 1);
                sortQuick(arr, eq[1] + 1, r);
            }
        }
        public static int[] partition(int[] arr,int l,int r){
            int less=l-1;
            int more=r;
            while (l<more){
                if(arr[l]<arr[r]){
                    swap(arr,++less,l++);//less区域扩充 1 个 l 往右移动
                }else if(arr[l]>arr[r]){
                    swap(arr,--more,l);//l(等于区）不扩大，more 区域往左移动
                }else {
                    l++;//等于区域扩大
                }
            }
            swap(arr,more,r);
            return new int[]{less+1,more};
        }
    //heap
        public static void sortHeap(int[] arr){
            if(arr==null||arr.length<2){return;}
            //构建堆
            for(int i=0;i<arr.length;i++){
               heapInsert(arr,i);
            }
            int size=arr.length;
            swap(arr,0,--size);
            //依次交换顶和最后一个,保持大根堆
            while (size>0){
                heapify(arr,0,size);//从根往叶子保持大根堆
                swap(arr,0,--size);//每次都把最后一个换到根，最后一次那个必然是最小值
            }
        }
        public static void heapInsert(int[] arr,int index){
            while (arr[index]>arr[(index-1)/2]){
                swap(arr,index,(index-1)/2);
                index=(index-1)/2;
            }
        }
        public static void heapify(int[] arr,int index,int size){
            int left=index*2+1;
            while (left<size){
                int largest=left+1<size&&arr[left+1]>arr[left]?left+1:left;//左右中最大一个
                largest=arr[largest]>arr[index]?largest:index;//和 index 想比较最大一个
                if(largest==index){
                    //已经满足大根堆性质不再比较了
                    break;
                }
                swap(arr,largest,index);
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
            int mid = l+((r-l)>>1);
            sortMerge(arr,l,mid);
            sortMerge(arr,mid+1,r);
            merge(arr,l,mid,r);
        }
        public static void merge(int[] arr,int l,int mid,int r){
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
            for(int i=0;i< help.length;i++){
                arr[l+i]= help[i];
            }
        }
    //计数
        public static void sortBuckets(int[] arr){
            if(arr==null||arr.length<2){return;}
            int max=arr[0];
            for (int i=0;i<arr.length;i++){
                max=Math.max(max,arr[i]);
            }
            int[] buckets = new int[max+1];//最大的数作为桶的最大长度+1,为什么要加一 因为从 [0...max]
            for(int i=0;i<arr.length;i++){
                buckets[arr[i]]++;//桶里面存的个数
            }
            int index=0;
            for (int i=0;i<buckets.length;i++){
                while (buckets[i]-->0){
                    arr[index++]=i;
                }
            }
        }
    //基数
        public static void sortRadix(int[] arr){
            if(arr==null||arr.length<2){return;}
            int buckets[][]=new int[10][arr.length];//10 层，每一层都是 arr.len 大小
            int counts[]= new int[10];//存有效数字
            int max=arr[0];
            for(int i=1;i<arr.length;i++){
                max=Math.max(max,arr[i]);
            }
            int weishu=0;
            while (max!=0){
               weishu++;
               max /= 10;
            }
            for(int i=0,n=1;i<weishu;i++,n *= 10){//个、十 百 千 。。。 分别 塞桶和出桶，总体就是按照 千百 10 个排序
               for(int j=0;j<arr.length;j++){//每个数要找到桶的位置
                  int a= arr[j]/n % 10;//该放到哪个桶里面
                  buckets[a][counts[a]]=arr[j];
                  counts[a]++;//有效值加 1 个，说明该位置有几个数
               }
               int index=0;
               for(int j=0;j<buckets.length;j++){
                   for(int k=0;k<counts[j];k++){// 几个有效数
                      arr[index++]=buckets[j][k];//第 j层第 k 个数
                   }
                   counts[j]=0;
               }


            }

        }


    public static void swap(int[] arr,int i,int j){
        if(i==j){return;}
        arr[i]=arr[i]^arr[j];
        arr[j]=arr[i]^arr[j];
        arr[i]=arr[i]^arr[j];
    }

    public static void main(String[] args) {
//        Constants.checkSort(Mysort7::sortBubble);
//        Constants.checkSort(Mysort7::sortInsert);
//        Constants.checkSort(Mysort7::sortSelect);
//        Constants.checkSort(Mysort7::sortQuick);
//        Constants.checkSort(Mysort7::sortHeap);
//        Constants.checkSort(Mysort7::sortMerge);
//        Constants.checkSortPositive(Mysort7::sortBuckets);
        Constants.checkSortPositive(Mysort7::sortRadix);
    }
}
