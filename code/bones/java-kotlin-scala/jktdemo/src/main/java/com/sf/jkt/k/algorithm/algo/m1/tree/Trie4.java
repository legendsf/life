package com.sf.jkt.k.algorithm.algo.m1.tree;

public class Trie4 {
    Trie4[] children;
    boolean isEnd;

    public Trie4() {
        children = new Trie4[26];
        isEnd = false;
    }

    public void insert(String word) {
        if (word == null || word.length() < 1) {
            return;
        }
        Trie4 cur = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            cur.children[index] = new Trie4();
            cur = cur.children[index];
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        Trie4 trie4 = searchPrefix(word);
        return trie4 != null && trie4.isEnd;
    }

    public boolean startWith(String pefix) {
        return searchPrefix(pefix) != null;
    }

    public Trie4 searchPrefix(String prefix) {
        if (prefix == null || prefix.length() < 1) {
            return null;
        }
        Trie4 cur = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (cur.children[i] == null) {
                return null;
            }
            cur = cur.children[i];
        }
        return cur;
    }

    public static void test1() {
        String str = "abcdefg";
        Trie4 trie2 = new Trie4();
        trie2.insert(str);
        System.out.println(trie2.search("abc"));//false
        System.out.println(trie2.startWith("abc"));//true
        System.out.println(trie2.search("abcg"));//false
        str = "abcde";
        trie2.insert(str);
        System.out.println(trie2.search("abcde"));//true
    }

    public static void main(String[] args) {
        test1();

    }
}