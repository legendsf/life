package com.sf.jkt.k.algorithm.algo.m1.tree;


import java.util.*;

public class HuffmenTree1 {
    HfmNode1 root;

    List<HfmNode1> leafs;
    Map<Character, Integer> weights;

    public HuffmenTree1(Map<Character, Integer> weights) {
        this.weights = weights;
        leafs = new ArrayList<HfmNode1>();
    }

    public Map<Character, String> code() {
        Map<Character, String> map = new HashMap<>();
        for (HfmNode1 node : leafs) {
            String code = "";
            Character ch = node.chars.charAt(0);
            HfmNode1 current = node;
            do {
                if (current.parent != null && current == current.parent.left) {
                    code = "0" + code;
                } else {
                    code = "1" + code;
                }
                current=current.parent;
            } while (current.parent != null);
            map.put(ch,code);
            System.out.println(ch + ":" + code);
        }
        return map;
    }

    public Map<Character, String> code1() {

        Map<Character, String> map = new HashMap<Character, String>();
        for (HfmNode1 node : leafs) {
            String code = "";
            Character c = new Character(node.chars.charAt(0)); // 叶子节点肯定只有一个字符
            HfmNode1 current = node; // 只有一个点
            do {
                if (current.parent != null && current == current.parent.left) { // 说明当前点是左边
                    code = "0" + code;
                } else {
                    code = "1" + code;
                }
                current = current.parent;
            } while (current.parent != null); // parent == null就表示到了根节点
            map.put(c, code);
            System.out.println(c + ":" + code);
        }
        return map;

    }

    public void creatTree1() {
        Character keys[] = weights.keySet().toArray(new Character[0]); // 拿出所有的点
        PriorityQueue<HfmNode1> priorityQueue = new PriorityQueue<HfmNode1>(); // jdk底层的优先队列
        for (Character c : keys) {
            HfmNode1 hfmNode = new HfmNode1();
            hfmNode.chars = c.toString();
            hfmNode.fre = weights.get(c); // 权重
            priorityQueue.add(hfmNode); // 首先把我们的优先队列初始化进去
            leafs.add(hfmNode);
        }

        int len = priorityQueue.size();
        for (int i = 1; i <= len - 1; i++) { // 每次找最小的两个点合并
            HfmNode1 n1 = priorityQueue.poll(); //
            HfmNode1 n2 = priorityQueue.poll(); // 每次取优先队列的前面两个 就一定是两个最小的

            HfmNode1 newNode = new HfmNode1();
            newNode.chars = n1.chars + n2.chars; // 我们把值赋值一下，也可以不复制
            newNode.fre = n1.fre + n2.fre; // 把权重相加

            // 维护出树的结构
            newNode.left = n1;
            newNode.right = n2;
            n1.parent = newNode;
            n2.parent = newNode;

            priorityQueue.add(newNode);
        }
        root = priorityQueue.poll(); // 最后这个点就是我们的根节点
        System.out.println("构建完成");
    }

    public void creatTree(){
        PriorityQueue<HfmNode1> queue=new PriorityQueue<>();
        for (Map.Entry<Character,Integer> ent:weights.entrySet()){
           HfmNode1 node=new HfmNode1();
           node.fre=ent.getValue();
           node.chars=ent.getKey().toString();
           queue.add(node);
           leafs.add(node);
        }
        int len=queue.size();


        for (int i=1;i<=len-1;i++){
            HfmNode1 n1=queue.poll();
            HfmNode1 n2=queue.poll();

            HfmNode1 newNode=new HfmNode1();
            newNode.chars=n1.chars+n2.chars;
            newNode.fre=n1.fre+n2.fre;

            newNode.left=n1;
            newNode.right=n2;
            n1.parent=newNode;
            n2.parent=newNode;
            queue.add(newNode);
        }
        root=queue.poll();
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

        HuffmenTree1 huffmenTree = new HuffmenTree1(weights);
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
