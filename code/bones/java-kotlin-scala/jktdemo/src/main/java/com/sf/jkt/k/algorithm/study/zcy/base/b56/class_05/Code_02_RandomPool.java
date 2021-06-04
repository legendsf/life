package com.sf.jkt.k.algorithm.study.zcy.base.b56.class_05;

import java.util.HashMap;

public class Code_02_RandomPool {

	public static class Pool<K> {
		private HashMap<K, Integer> keyIndexMap;
		private HashMap<Integer, K> indexKeyMap;
		private int size;

		public Pool() {
			this.keyIndexMap = new HashMap<K, Integer>();
			this.indexKeyMap = new HashMap<Integer, K>();
			this.size = 0;
		}

		public void insert(K key) {
			if (!this.keyIndexMap.containsKey(key)) {
				this.keyIndexMap.put(key, this.size);
				this.indexKeyMap.put(this.size++, key);
			}
		}

		public void delete(K key) {
			if (this.keyIndexMap.containsKey(key)) {
				int deleteIndex = this.keyIndexMap.get(key);
				int lastIndex = --this.size;
				K lastKey = this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(lastKey, deleteIndex);
				this.indexKeyMap.put(deleteIndex, lastKey);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}

		public K getRandom() {
			if (this.size == 0) {
				return null;
			}
			int randomIndex = (int) (Math.random() * this.size); // 0 ~ size -1
			return this.indexKeyMap.get(randomIndex);
		}

	}
	private static class Pool1<K>{
		private HashMap<K,Integer>keyIndexMap;
		private HashMap<Integer, K>indexKeyMap;
		private int size;

		public Pool1() {
			keyIndexMap=new HashMap<>();
			indexKeyMap=new HashMap<>();
			size=0;
		}

		public void insert(K key){
			if(!this.keyIndexMap.containsKey(key)){
				this.keyIndexMap.put(key,this.size);
				this.indexKeyMap.put(this.size++,key);
			}
		}
		public void delete(K key){
			if (this.keyIndexMap.containsKey(key)){
				int deleteIndex=this.keyIndexMap.get(key);
				int lastIndex=--this.size;
				K last=this.indexKeyMap.get(lastIndex);
				this.keyIndexMap.put(last,deleteIndex);
				this.indexKeyMap.put(deleteIndex,last);
				this.keyIndexMap.remove(key);
				this.indexKeyMap.remove(lastIndex);
			}
		}
		public K getRandom(){
			if (this.size==0){
				return null;
			}
			int randomIndex=(int)(Math.random()*this.size);
			return this.indexKeyMap.get(randomIndex);
		}
	}
	private static class Pool2<K>{
		HashMap<K,Integer> keyIndexMap;
		HashMap<Integer, K>indexKeyMap;
		int size;

		public Pool2() {
			keyIndexMap=new HashMap<>();
			indexKeyMap=new HashMap<>();
			size=0;
		}

		public void insert(K key){
			if (!keyIndexMap.containsKey(key)){
				keyIndexMap.put(key,this.size);
				indexKeyMap.put(this.size++,key);
			}
		}
		public void delete(K key){
			if(keyIndexMap.containsKey(key)){
				int deleteIndex=keyIndexMap.get(key);
				int lastIndex=--this.size;
				K last=indexKeyMap.get(lastIndex);
				indexKeyMap.put(deleteIndex,last);
				keyIndexMap.put(last,deleteIndex);
				keyIndexMap.remove(key);
				indexKeyMap.remove(lastIndex);
			}
		}
		public K getRandom(){
			if (this.size==0){
				return null;
			}
			int randomIndex=(int)(Math.random()*this.size);
			return indexKeyMap.get(randomIndex);
		}

	}

	public static void main(String[] args) {
		Pool1<String> pool = new Pool1<String>();
		pool.insert("zuo");
		pool.insert("cheng");
		pool.insert("yun");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println("---");
		pool.delete("zuo");
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());
		System.out.println(pool.getRandom());

	}

}
