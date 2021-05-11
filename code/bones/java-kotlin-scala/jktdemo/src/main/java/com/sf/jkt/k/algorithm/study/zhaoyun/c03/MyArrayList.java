package com.sf.jkt.k.algorithm.study.zhaoyun.c03;


public class MyArrayList<E> implements MyList<E> {

	private static final int DEFAULT_SIZE = 10;
	private Object data[];
	private int index;
	private int size;

	public MyArrayList() {
		this.data = new Object[DEFAULT_SIZE];
		this.size = DEFAULT_SIZE;
	}

	@Override
	public void add(E e) {
		data[index++] = e;
		if (index == size) { // ����
			this.size = this.size * 2 + 1;
			Object newData[] = new Object[this.size];
			for (int i = 0; i < data.length; i++) {
				newData[i] = data[i];
			}
			this.data = newData;
		}
	}

	@Override
	public void remove(int i) {
		if (i >= 0 && i < index) {
			for (int j = i; j < this.data.length - 1; j++) {
				data[j] = data[j + 1];
			}
			this.index--;
		}
	}

	@Override
	public E get(int i) {
		if (i >= 0 && i < index) {
			return (E) this.data[i];
		}
		return null;
	}

	@Override
	public int size() {
		return index;
	}

	@Override
	public boolean isEmpty() {
		if(index <= 0) return true;
		return false;
	}
	@Override
	public void remove(Object e) {
		if(!isEmpty()) {
			for(int i = 0 ; i < this.data.length ; i ++) {
				if(data[i].equals(e)) {
					remove(i);
					break;
				}
			}
		}
	}

	public static void main(String[] args) {
		MyArrayList<Integer> list = new MyArrayList<>();
		list.add(1);
		list.add(5);
		list.add(4);
		System.out.println(list.size());
		System.out.println(list.isEmpty());
		System.out.println(list.get(0));
		System.out.println(list.get(1));
		Integer integer = new Integer(5);
		list.remove(integer);
		System.out.println(list.get(1));
	}
	
}
