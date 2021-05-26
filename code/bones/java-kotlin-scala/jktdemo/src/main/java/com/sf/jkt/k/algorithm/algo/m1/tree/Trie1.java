package com.sf.jkt.k.algorithm.algo.m1.tree;

public class Trie1 {
    private Trie1[] children;
    private boolean isEnd;

    public Trie1() {
        children=new  Trie1[26];
        isEnd=false;
    }

    public void insert(String word){
        Trie1 node=this;
        for(int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                node.children[index]=new Trie1();
            }
            node=node.children[index];
        }
        node.isEnd=true;
    }

    public boolean search(String word){
        Trie1 node=searchPrefix(word);
        return node!=null && node.isEnd;
    }

    public boolean startsWith(String prefix){
        return searchPrefix(prefix)!=null;
    }

    public Trie1 searchPrefix(String prefix){
        Trie1 node=this;
        for(int i=0;i<prefix.length();i++){
            char ch=prefix.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                return null;
            }
            node=node.children[index];
        }
        return node;
    }
}
