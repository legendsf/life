package com.sf.jkt.k.algorithm.algo.m1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greedy {
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

    public static void main(String[] args) {
        arrangeMeeting(list).stream().forEachOrdered(System.out::println);
    }
}
