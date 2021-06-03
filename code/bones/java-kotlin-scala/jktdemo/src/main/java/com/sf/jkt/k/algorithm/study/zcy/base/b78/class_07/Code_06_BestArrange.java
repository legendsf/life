package com.sf.jkt.k.algorithm.study.zcy.base.b78.class_07;

import java.util.Arrays;
import java.util.Comparator;

public class Code_06_BestArrange {

	public static class Program {
		public int start;
		public int end;

		public Program(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	public static class ProgramComparator implements Comparator<Program> {

		@Override
		public int compare(Program o1, Program o2) {
			return o1.end - o2.end;
		}

	}

	public static int bestArrange(Program[] programs, int start) {
		Arrays.sort(programs, new ProgramComparator());
		int result = 0;
		for (int i = 0; i < programs.length; i++) {
			if (start <= programs[i].start) {
				result++;
				start = programs[i].end;
			}
		}
		return result;
	}

	public static int bestArrange1(Program[] meeting,int start){
		int result=0;
		Arrays.sort(meeting,(m1,m2)->{
			return m1.end==m2.end?m2.start-m1.start:m1.end-m2.end;
		});
		for (int i=0;i<meeting.length;i++){
			if (start<=meeting[i].start){
				result++;
				start=meeting[i].end;
			}
		}
		System.out.println(result);
		return result;
	}

	public static void test1(){
	    Program[] programs=new Program[]{new Program(1,3),new Program(2,4),new Program(3,4),new Program(5,6)};
		System.out.println(bestArrange(programs, 1));
		bestArrange1(programs,1);
	}

	public static void main(String[] args) {
		test1();
	}

}
