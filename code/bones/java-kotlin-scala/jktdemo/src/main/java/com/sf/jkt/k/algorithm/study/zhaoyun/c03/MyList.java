package com.sf.jkt.k.algorithm.study.zhaoyun.c03;

public interface MyList<E> {
	public void add(E e);
	
	public void remove(int i);
	
	public void remove(Object e);
	
	public E get(int i);
	
	public int size();
	
	public boolean isEmpty();
	
}
