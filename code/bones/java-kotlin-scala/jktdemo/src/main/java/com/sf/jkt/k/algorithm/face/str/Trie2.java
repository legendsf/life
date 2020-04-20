package com.sf.jkt.k.algorithm.face.str;

import java.util.ArrayList;
import java.util.List;

public class Trie2 {


	public class Node {
		boolean isWord = false;
		Node[] child = new Node[26];//0-25：a:b
		List<String> pos = new ArrayList<String>();
	}

	private Node root;
	Trie2(){
		root = new Node();
	}
	public void addWord(String word,String pos){
		int len = word.length();
		Node s = root;
		for(int i =0;i<len;i++){
			int ch = word.charAt(i)-97;//c2 h7
			if(s.child[ch] !=null){//有节点了
				s = s.child[ch];//后移
				
			}else{//没节点
			 Node child = new Node();
			  if(i==len-1){//最后一个字符
				 child.isWord = true;
				 child.pos.add(pos);
			  }
			  s.child[ch] = child;//挂上节点
			  s = child;//后移
			}
		}
	}
    public void findWord(String word){
    	int len = word.length();
    	Node s = root;
		for(int i =0;i<len;i++){
			int ch = word.charAt(i)-97;
			if(s.child[ch]!=null){//节点存在
				s = s.child[ch];//后移
				if(i == len -1){
					for(String pos :s.pos){
						System.out.println(pos);
					}
				}
				
			}else{
				System.out.println("不存在这个单词");
				return ;
			}
			
		}
    }
	
	public static void main(String[] args) {
		Trie2 trie = new Trie2();
		trie.addWord("silence", "1");
		trie.addWord("hello", "2");
		trie.addWord("word", "3");
		trie.addWord("a","4");
		
		trie.findWord("silence");
		trie.findWord("a");
		
	}
 
}

