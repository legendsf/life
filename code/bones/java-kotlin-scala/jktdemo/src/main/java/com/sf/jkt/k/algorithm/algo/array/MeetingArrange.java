package com.sf.jkt.k.algorithm.algo.array;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 选一种能安排最多会议的方式
 */
public class MeetingArrange {
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
    @Data
    public static class Meeting {
        int id;
        int start;
        int end;

        public Meeting(int id, int start, int end) {
            this.id = id;
            this.start = start;
            this.end = end;
        }
    }


    public static List<Meeting> arrangeMeeting(List<Meeting> inputs) {
        List<Meeting> result = new ArrayList<>();
        if (inputs == null || inputs.size() < 1) {
            return result;
        }
        Collections.sort(inputs, new Comparator<Meeting>() {
            @Override
            public int compare(Meeting o1, Meeting o2) {
                if (o1.end == o2.end) {
                    return o2.start - o1.start;//结束时间相同就选开始时间最晚的排在前面，以确保会议比较短
                } else {
                    return o1.end - o2.end;//结束时间早的放前面
                }
            }
        });
        final int[] last = {0};
        inputs.stream().forEachOrdered(meeting -> {
            if (meeting.start >= last[0]) {
                result.add(meeting);
                last[0] = meeting.end;
            }
        });
        return result;
    }

    public static List<Meeting> arrangeMeeting1(List<Meeting> inputs){
        List<Meeting> result=new ArrayList<>();
        if(inputs==null||inputs.size()<1){
           return result;
        }
       Collections.sort(inputs, (m1,m2)->{
           //开始时间晚的放在前面，确保会议时间短;结束时间按升序拍（小的放前面）
           return m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
       });
       AtomicInteger last= new AtomicInteger(0);//上一次会议结束的时间
       inputs.stream().forEachOrdered(m->{
          if(m.start>= last.get()){
             result.add(m);
             last.set(m.end);
          }
       });
       return result;
    }

    public static List<Meeting> arrangeMeeting2(List<Meeting> meetings){
        List<Meeting> result=new ArrayList<>();
        if(meetings==null||meetings.size()<1){
            return result;
        }
        Collections.sort(meetings,(m1,m2)->{
            return m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
        });
        int[]last={0};
        meetings.stream().forEachOrdered(m->{
            if(m.start>=last[0]){//当前会议大于上一次会议结束时间，说明能开
               result.add(m);
               last[0]=m.end;//把结束时间置为当前会议的结束时间
            }
        });
        return result;
    }


    public static void main(String[] args) {
        arrangeMeeting2(list).stream().forEachOrdered(System.out::println);
    }
}
