package com.sf.jkt.k.algorithm.algo.m1.algo.type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Gready1 {
    static Meeting m0 = new Meeting(0, 3, 10);
    static Meeting m1 = new Meeting(1, 6, 10);
    static Meeting m2 = new Meeting(2, 5, 15);
    static Meeting m3 = new Meeting(3, 15, 20);
    static List<Meeting> list = new ArrayList<>();

    static{
        list.add(m0);
        list.add(m1);
        list.add(m2);
        list.add(m3);
    }
    public static class Meeting{
        public int id;
        public int start;
        public int end;

        public Meeting(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "id=" + id +
                    ", start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
    
    public static List<Meeting> arrangeMeeting(List<Meeting> meetingList ){
        List<Meeting> result=new ArrayList<>(); 
        if(meetingList==null||meetingList.size()<1){
            return result;
        }
        Collections.sort(result,(m1,m2)->{
            return  m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
        });
        int[]last={0};
        meetingList.stream().forEachOrdered(meeting -> {
            if(meeting.start>=last[0]){
                result.add(meeting);
                last[0]=meeting.end;
            }
        });
        return result;
    }

    public static List<Meeting> arrangeMeeting1(List<Meeting> meetings){
       List<Meeting> ans =new ArrayList<>();
       if (meetings==null||meetings.size()<1){
           return ans;
       }
       Collections.sort(meetings,(m1,m2)->{
           return m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
       });
       int[] last={0};
       meetings.stream().forEachOrdered(x->{
           if (x.start>=last[0]){
               ans.add(x);
               last[0]=x.end;
           }
       });
      return ans;
    }

    public static List<Meeting> arrangeMeeting2(List<Meeting> meetings){
        List<Meeting> ans=new ArrayList<>();
        if (meetings==null||meetings.size()<1){
            return ans;
        }
        Collections.sort(meetings,(m1,m2)->{
            return m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
        });
        int last[]={0};
        meetings.stream().forEachOrdered(m->{
           if (m.start>=last[0]){
               ans.add(m);
               last[0]=m.end;
           }
        });
        return ans;
    }

    /**
     * https://www.cnblogs.com/cxmhy/p/4491009.html
     * 贪心算法----正整数分解问题 和相同，乘积最大
     * 数字不能重复,贪心策略是尽可能多
     * @param input
     */
    public static void partNumbers(int input){
        int k=2;
        int[] in=new int[100];
        int i=0;
        while (input>=k){
            in[i++]=k;
            input-=k;
            k++;
        }
        if(input!=0){
            if(input==in[i-1]){
                in[i-1]++;
                input--;
            }
            for (int j=0;j<input;j++){
                in[i-1-j]++;
            }
        }
        int result=1;
        for(int j=0;j<=i-1;j++){
            result*=in[j];
        }
        System.out.println(result);
    }

    public  static void partNumbers1(int input){
        int[] b=new int[100];
        int i=0;
        while (input!=2&&input!=4){
            b[i++]=3;
            input -=3;
        }
        while (input!=0){
            b[i++]=2;
            input-=2;
        }
        int result=1;
        for (int j=0;j<i;j++){
            result*=b[j];
        }
        System.out.println(result);
    }

    /**
     * 下楼梯
     * https://www.cnblogs.com/cxmhy/p/4474841.html
     * @param n
     * @return
     */
    public static int upstairs(int n){
        if(n==1){
            return 1;
        }else if(n==2){
            return 2;
        }else if(n==3){
            return 4;
        }else {
            return upstairs(n-1)+upstairs(n-2)+upstairs(n-3);
        }
    }

    public static int upstairs1(int n){
        int[] state=new int[100];
        return  upstairs1(n,state);
    }
    public static int upstairs1(int n,int[] state){
        int num;
        if(state[n]>0){
            return state[n];
        }
        if(n==1){
            num=1;
        }else if(n==2){
            num=2;
        }else if(n==3){
            num=4;
        }else {
            num=upstairs1(n-1)+upstairs1(n-2)+upstairs1(n-3);
        }
        state[n]=num;
        return num;
    }

    public static int upstairs(int n,int[] state){
        int num;
        if (state[n]>0){
            return state[n];
        }
        if (n==1){
            num=1;
        }else if (n==2){
            num=2;
        }else if(n==3){
            num=4;
        }else {
            num= upstairs(n-1)+upstairs(n-2)+upstairs(n-3);
        }
        state[n]=num;
        return num;
    }

    /**
     * 滚动数组
     *
     * @param n
     * @return
     */
    public static int upstairs2(int n){
        int p=0,q=0,r=1;
        for(int i=1;i<=n;i++){
            p=q;
            q=r;
            r=p+q;
        }
        return r;
    }

    public static int upstairs4(int n){
        int p=0,q=0,r=1;
        for (int i=1;i<=n;i++){
            p=q;
            q=r;
            r=p+q;
        }
        return r;
    }
    public static void partNumbers2(int input){
        int k=2;
        int[] in=new  int[100];
        int i=0;
        while (input>=k){
            in[i++]=k;
            input-=k;
            k++;
        }
        if (input!=0){
            if (input==in[i-1]){
                in[i-1]++;
                input--;
            }
            for (int j=0;j<input;j++){
                in[i-1-j]++;
            }
        }
        int result=1;
        for (int j=0;j<=i-1;j++){
            result*=in[j];
        }
        System.out.println(result);
    }

    public static void partNumbers3(int input){
        int[]b=new int[100];
        int i=0;
        while (input!=2&&input!=4){
            b[i++]=3;
            input-=3;
        }
        while (input!=0){
            b[i++]=2;
            input-=2;
        }
        int result=1;
        for (int j=0;j<i;j++){
            result*=b[j];
        }
        System.out.println(result);
    }

    public static void partNumbers5(int input){
       int[]bi=new int[100];
       int i=0,k=2;
       while (input>=k){
           bi[i++]=k;
           input-=k++;
       }
       if (input!=0){
          if (input==bi[i-1]){
              bi[i-1]++;
              input--;
          }
          for (int j=0;j<input;j++){
             bi[i-1-j]++;
          }
       }
       int result=1;
       for (int j=0;j<i;j++){
           result*=bi[j];
       }
        System.out.println(result);
    }
    public static void partNumbers6(int input){
        int[]bi=new int[100];
        int i=0;
        while (input!=2&&input!=4){
            bi[i++]=3;
            input-=3;
        }
        while (input!=0){
            bi[i++]=2;
            input-=2;
        }
        int result=1;
        for (int j=0;j<i;j++){
            result*=bi[j];
        }
        System.out.println(result);
    }
    public static void test1(){
        partNumbers(13);
        partNumbers1(13);
        partNumbers2(13);
        partNumbers3(13);
        partNumbers5(13);
        partNumbers6(13);
    }
    public static void test2(){

        arrangeMeeting(list).stream().forEachOrdered(System.out::println);
        arrangeMeeting1(list).stream().forEachOrdered(System.out::println);
        arrangeMeeting2(list).stream().forEachOrdered(System.out::println);
    }

    public static void main(String[] args) {
        test1();
    }
}
