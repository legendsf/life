package com.sf.jkt.k.algorithm.algo.m1.tree;

import org.jetbrains.annotations.NotNull;

import java.util.*;

public class HuffmenTree2 {
    private static class HfmNode2 implements Comparable<HfmNode2>{
        String chars;
        int fre;
        HfmNode2 left;
        HfmNode2 right;
        HfmNode2 parent;

        public HfmNode2(String chars, int fre) {
            this.chars = chars;
            this.fre = fre;
        }

        @Override
        public int compareTo(@NotNull HfmNode2 o) {
           return this.fre- o.fre;
        }
    }
    HfmNode2 root;
    List<HfmNode2> leafs;
    Map<Character,Integer> weights;

    public HuffmenTree2(Map<Character, Integer> weights) {
        this.weights = weights;
        leafs=new ArrayList<>(weights.size());
    }
    public void creatTree(){
       PriorityQueue<HfmNode2> queue=new PriorityQueue<>(); 
       for (Map.Entry<Character,Integer> ent:weights.entrySet()){
           HfmNode2 node=new HfmNode2(ent.getKey().toString(),ent.getValue());
           queue.add(node);
           leafs.add(node);
       }
       int len=queue.size();
       for (int i=1;i<=len-1;i++){
           HfmNode2 n1= queue.poll(),n2=queue.poll();
           HfmNode2 newNode=new HfmNode2(n1.chars+n2.chars,n1.fre+n2.fre);
           newNode.left=n1;
           newNode.right=n2;
           n1.parent=newNode;
           n2.parent=newNode;
           queue.add(newNode);
       }
       root=queue.poll();
    }
    public Map<Character, String> code(){
        Map<Character, String> codes=new HashMap<>();
        for (HfmNode2 node:leafs){
            HfmNode2 current=node;
            String code="";
            char ch=node.chars.charAt(0);
            do {
                if(current.parent!=null&&current==current.parent.left){
                    code="0"+code;
                }else {
                   code="1"+code; 
                }
                current=current.parent;    
            }while (current.parent!=null);
            codes.put(ch,code);
            System.out.println(ch+":"+code);
        }
        return codes;
    }

    public static void test1(){
        // a:3 b:24 c:6 d:20 e:34 f:4 g:12
        Map<Character, Integer> weights = new HashMap<Character, Integer>();
        //一般来说：动态的加密，最开始是不知道里面有什么内容的。我们需要一个密码本，往往就是某个字典。如果是英文就用英文字典，统计次数。
        //换密码本
        //静态的文件。针对性的做编码.图像加密,没有特性的。hash加密（MD5）
        weights.put('a', 3);
        weights.put('b', 24);
        weights.put('c', 6);
        weights.put('d', 20);
        weights.put('e', 34);
        weights.put('f', 4);
        weights.put('g', 12);

        HuffmenTree2 huffmenTree = new HuffmenTree2(weights);
        huffmenTree.creatTree();
        Map<Character, String> code = huffmenTree.code();
        String str = "aceg";
        System.out.println("编码后的：");
        char s[] = str.toCharArray();
    }

    public static void main(String[] args) {
        test1();
    }
}
