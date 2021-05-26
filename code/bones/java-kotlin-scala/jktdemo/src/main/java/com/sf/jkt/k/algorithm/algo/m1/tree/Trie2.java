package com.sf.jkt.k.algorithm.algo.m1.tree;

public class Trie2 {
    public Trie2[] children;
    public boolean isEnd;

    public Trie2() {
        children=new  Trie2[26];
        isEnd=false;
    }

    public void insert(String word){
        Trie2 node=this;
        for (int i=0;i<word.length();i++){
            char ch=word.charAt(i);
            int index=ch-'a';
            if(node.children[index]==null){
                node.children[index]=new Trie2();
            }
            node=node.children[index];
        }
        node.isEnd=true;
    }

    public boolean search(String word){
        Trie2 node=searchPrefix(word);
        return node!=null&&node.isEnd;
    }
    public boolean startWith(String prefix){
        return searchPrefix(prefix)!=null;
    }
    public Trie2 searchPrefix(String prefix){
       Trie2 node=this;
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
        Trie2 trie2=new Trie2();
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
