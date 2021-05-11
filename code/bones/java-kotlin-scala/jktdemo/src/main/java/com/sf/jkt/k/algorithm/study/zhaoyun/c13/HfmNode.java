package com.sf.jkt.k.algorithm.study.zhaoyun.c13;

public class HfmNode implements Comparable<HfmNode>{		//优先队列,小的我把你优先级调高
	
	String chars;		//节点里面的字符
	int fre;		//表示是频率
	HfmNode left;
	HfmNode right;
	HfmNode parent;	//用来找上层的
	
	@Override
	public int compareTo(HfmNode o) {
		return this.fre - o.fre;
	}
	
}
