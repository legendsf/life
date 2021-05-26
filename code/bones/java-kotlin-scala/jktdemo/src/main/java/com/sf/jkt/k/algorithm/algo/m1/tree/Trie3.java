package com.sf.jkt.k.algorithm.algo.m1.tree;

public class Trie3 {
    public Trie3[] children;
    public boolean isEnd;

    public Trie3() {
        children=new Trie3[26];
        isEnd=false;
    }
    public void insert(String word){
        if(word==null||word.length()<1){
            return;
        }
        Trie3 node=this;
        for (int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                node.children[index]=new Trie3();
            }
            node=node.children[index];
        }
        node.isEnd=true;
    }
    public boolean search(String word){
        Trie3 tmp=searchPrefix(word);
        return tmp!=null&&tmp.isEnd;
    }
    public boolean startWith(String prefix){
       return searchPrefix(prefix)!=null;
    }
    public Trie3 searchPrefix(String prefix){
        if(prefix==null||prefix.length()<1){
            return null;
        }
        Trie3 node=this;
        for (int i=0;i<prefix.length();i++){
            char ch=prefix.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                return null;
            }
            node=node.children[index];
        }
        return node;
    }

    public static void test1(){
        String str="abcdefg";
        Trie3 trie2=new Trie3();
        trie2.insert(str);
        System.out.println(trie2.search("abc"));
        System.out.println(trie2.startWith("abc"));
        System.out.println(trie2.search("abcg"));
        str="abcde";
        trie2.insert(str);
        System.out.println(trie2.search("abcde"));
    }

    public static void main(String[] args) {
        test1();
    }
}
