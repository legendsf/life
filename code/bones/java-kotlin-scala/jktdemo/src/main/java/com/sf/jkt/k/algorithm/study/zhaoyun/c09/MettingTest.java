package com.sf.jkt.k.algorithm.study.zhaoyun.c09;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Metting implements Comparable<Metting> {

	int meNum; // 编号
	int startTime; // 开始时间
	int endTime; // 结束时间
	
	public Metting(int meNum, int startTime, int endTime) {
		super();
		this.meNum = meNum;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int compareTo(Metting o) {
		if (this.endTime > o.endTime)
			return 1;
		return -1;
	}

	@Override
	public String toString() {
		return "Metting [meNum=" + meNum + ", startTime=" + startTime
				+ ", endTime=" + endTime + "]";
	}

}

public class MettingTest {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		List<Metting> mettings = new ArrayList<Metting>();
		int n = cin.nextInt();	//n个会议
		for (int i = 0 ;i < n; i++){
			int start = cin.nextInt();
			int end = cin.nextInt();
			Metting metting = new Metting(i+1, start, end);
			mettings.add(metting);
		}

		mettings.sort(null);
		int curTime = 0;		//当前的时间,从一天的0点开始,如果领导要求从8点开始 那curTime=8
		for(int i = 0 ; i < n; i ++){
			Metting metting = mettings.get(i);
			if(metting.startTime >= curTime){		//会议的开始时间比我们当前的要大 表示可以开
				System.out.println(metting.toString());
				curTime = metting.endTime;
			}
		}
	}
}
