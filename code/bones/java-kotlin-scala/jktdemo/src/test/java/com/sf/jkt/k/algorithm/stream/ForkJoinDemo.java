package com.sf.jkt.k.algorithm.stream;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * https://www.cnblogs.com/linlinismine/p/9295701.html
 * https://blog.csdn.net/befocused/article/details/104443000
 */
public class ForkJoinDemo {
    static class CountTask extends  RecursiveTask<Integer>{
        public static final int THRESHOLD=2;
        int start;
        int end;

        public CountTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum=0;
            boolean canCompute=(end-start)<=THRESHOLD;
            if(canCompute){
                for(int i=start;i<=end;i++){
                    sum += i;
                }
            }else {
                int middle = (start+end)/2;
                CountTask left=new CountTask(start,middle);
                CountTask right=new CountTask(middle+1,end);
                //执行子任务
                left.fork();
                right.fork();
                Integer leftR=left.join();
                Integer rightR=right.join();
                sum=leftR+rightR;
            }
            return sum;
        }
    }

    /***
     *
     * @throws Exception
     */
    @Test
    public void testForkJoin()throws Exception{
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        CountTask countTask=new CountTask(1,200);
        ForkJoinTask<Integer> forkJoinTask=forkJoinPool.submit(countTask);
        System.out.println(forkJoinTask.get());
    }
}
