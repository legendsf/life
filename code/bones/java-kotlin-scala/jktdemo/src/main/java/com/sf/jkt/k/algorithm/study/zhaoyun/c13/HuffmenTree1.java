package com.sf.jkt.k.algorithm.study.zhaoyun.c13;

import java.util.*;

public class HuffmenTree1 {
    HfmNode1 root;
    List<HfmNode1> leafs;
    Map<Character,Integer> weights;

    public HuffmenTree1(Map<Character, Integer> weights) {
        this.weights = weights;
        leafs =new ArrayList<HfmNode1>();
        createTree();
        code();
    }

    public void decode(){

    }
    public void encode(){

    }

    private void createTree(){
       Character keys[]=weights.keySet().toArray(new Character[0]);
       PriorityQueue<HfmNode1> priorityQueue=new PriorityQueue<HfmNode1>();
       for (Character c: keys){
           HfmNode1 hfmNode=new HfmNode1();
           hfmNode.chars=c.toString();
           hfmNode.fre=weights.get(c);
           priorityQueue.add(hfmNode);
           leafs.add(hfmNode);
       }
       int len=priorityQueue.size();
       for (int i=1;i<len;i++){
           HfmNode1 n1=priorityQueue.poll();
           HfmNode1 n2=priorityQueue.poll();
           HfmNode1 newNode= new HfmNode1();
           newNode.chars=n1.chars+n2.chars;
           newNode.fre=n1.fre+n2.fre;
           newNode.left=n1;
           newNode.right=n2;
           n1.parent=newNode;
           n2.parent=newNode;
           priorityQueue.add(newNode);
       }
       root=priorityQueue.poll();
        System.out.println("构建完成");
    }

    private Map<Character, String> code(){
        Map<Character, String> map=new HashMap<Character, String>();
        for (HfmNode1 node:leafs){
            String code="";
            Character c = new Character(node.chars.charAt(0));
            HfmNode1 current=node;
            do{
                if(current.parent!=null && current==current.parent.left){
                    code="0"+code;
                }else {
                    code="1"+code;
                }
                current=current.parent;
            }while (current.parent!=null);
            map.put(c,code);
            System.out.println(c+":"+code);
        }
        return map;
    }

    public static void main(String[] args) {
        // a:3 b:24 c:6 d:20 e:34 f:4 g:12
        Map<Character, Integer> weights = new HashMap<Character, Integer>();
        //一般来说：动态的加密，最开始是不知道里面有什么内容的。我们需要一个密码本，往往就是某个字典。如果是英文就用英文字典，统计次数。
        //换密码本
        //静态的文件。针对性的做编码.图像加密,没有特性的。hash加密（MD5）
        weights.put('a', 3);
        weights.put('b', 24);
        weights.put('c', 6);
        weights.put('d', 1);
        weights.put('e', 34);
        weights.put('f', 4);
        weights.put('g', 12);

        HuffmenTree1 huffmenTree = new HuffmenTree1(weights);
        String str = "aceg";
        System.out.println("编码后的：");
        char s[] = str.toCharArray();
    }
/*
 a:10110
b:01
c:1010
d:00
e:11
f:10111
g:100

 * *
 */
}
