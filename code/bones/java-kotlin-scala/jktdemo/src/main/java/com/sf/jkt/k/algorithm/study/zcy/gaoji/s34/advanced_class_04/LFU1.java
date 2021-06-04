package com.sf.jkt.k.algorithm.study.zcy.gaoji.s34.advanced_class_04;

import java.util.HashMap;

public class LFU1 {
    private static class Node {
        Integer key, value, times;
        Node up, down;

        public Node(Integer key, Integer value, Integer times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache {
        private static class NodeList {
            Node head, tail;
            NodeList last, next;

            public NodeList(Node node) {
                head = node;
                tail = node;
            }

            public void addNodeFromHead(Node newHead) {
                newHead.down = head;
                head.up = newHead;
                head=newHead;//check
            }

            public boolean isEmpty() {
                return head == null;
            }

            public void deleteNode(Node node) {
                if (head == tail) {
                    head = null;
                    tail = null;
                } else {
                    if (node == head) {
                        head = node.down;
                        head.up = null;
                    } else if (node == tail) {
                        tail = node.up;
                        tail.down = null;
                    } else {
                        node.up.down = node.down;
                        node.down.up = node.up;
                    }
                }
            }

        }

        private int capacity;
        private int size;
        private HashMap<Integer, Node> records;
        private HashMap<Node, NodeList> heads;
        private NodeList headList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size=0;
            records=new HashMap<>();
            heads=new HashMap<>();
            headList=null;
        }

        private boolean modifyHeadList(NodeList nodeList){
           if (nodeList.isEmpty()){//需要删除
                if(headList==nodeList){//是头结点，那么头结点往下移动
                    headList=nodeList.next;
                    if (headList!=null){
                        headList.last=null;
                    }
                }else {//nodeList不是头结点，不是头结点那么,前后重连
                    nodeList.last.next=nodeList.next;
                    if (nodeList.next!=null){
                        nodeList.next.last=nodeList.last;
                    }
                }
                return true;
           }
           return false;
        }

        private void move(Node node,NodeList oldNodeList){
            oldNodeList.deleteNode(node);//老的删除
            //需要删除那么前一个结点就是old的前一个，不需要删除的话那么就是old
            NodeList preList=modifyHeadList(oldNodeList)?oldNodeList.last:oldNodeList;
            NodeList nextList=oldNodeList.next;
            if(nextList==null){//词频最高,比最大的词频还大，所以末尾为空
               NodeList newList=new NodeList(node);
               if (preList!=null){
                   preList.next=newList;
               }
               newList.last=preList;
               if (headList==null){
                   headList=newList;
               }
               heads.put(node,newList);
            }else {
                if(nextList.head.times.equals(node.times)){//下一个词频等于当前node
                   nextList.addNodeFromHead(node);
                   heads.put(node,nextList);
                }else {//下一个词频大于当前node
                    NodeList newList=new NodeList(node);
                    if(preList!=null){
                       preList.next=newList;
                    }
                    newList.last=preList;
                    newList.next=nextList;
                    if(headList==newList){
                        headList=newList;
                    }
                    heads.put(node,newList);
                }
            }
        }

        public void set(int key,int value){
            if (records.containsKey(key)){//重新设置值
                Node node=records.get(key);
                node.value=value;
                node.times++;
                NodeList curNodeList=heads.get(node);
                move(node,curNodeList);
            }else {//要新建结点
               if (size==capacity){//马上要超出容量了，先删除再加入
                  Node node=headList.tail;
                  headList.deleteNode(node);
                  modifyHeadList(headList);
                  records.remove(node.key);
                  size--;
               }
               Node node = new Node(key,value,1);
               if (headList==null){//链表为空
                   headList=new NodeList(node);
               }else {
                   if (headList.head.times.equals(node.times)){
                       //词频为1的结点存在
                       headList.addNodeFromHead(node);
                   }else {
                      NodeList newList=new NodeList(node);
                      newList.next=headList;
                      headList.last=newList;
                      headList=newList;
                   }
               }
               records.put(key,node);
               heads.put(node,headList);
               size++;
            }
        }
        public int get(int key){
            if (!records.containsKey(key)){
                System.out.println("null");
                return -1;
            }
            Node node=records.get(key);
            node.times++;
            NodeList curNodeList=heads.get(node);
            move(node,curNodeList);
            System.out.println(node.value);
            return node.value;
        }
    }


    /**
     1
     null
     3
     null
     3
     4
     */
    public static void test1(){
       LFUCache cache=new LFUCache(2);
        cache.set(1,1);
        cache.set(2,2);
        cache.get(1);
        cache.set(3,3);
        cache.get(2);
        cache.get(3);
        cache.set(4,4);
        cache.get(1);
        cache.get(3);
        cache.get(4);

    }

    public static void main(String[] args) {
        test1();
    }

}
