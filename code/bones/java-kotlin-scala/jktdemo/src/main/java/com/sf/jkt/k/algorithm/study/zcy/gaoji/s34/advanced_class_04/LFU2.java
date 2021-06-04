package com.sf.jkt.k.algorithm.study.zcy.gaoji.s34.advanced_class_04;



import java.util.HashMap;

public class LFU2 {
    private static class Node{
        Integer key,value,times;
        Node up,down;

        public Node(int key, int value, int times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }


    public static class LFUCache{

        private static class NodeList{
            Node head,tail;
            NodeList last,next;

            public NodeList(Node node) {
                head=node;
                tail=node;
            }
            public void addNodeFromHead(Node node){
               node.down=head;
               head.up  =node;
               head=node;
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
                node.up = null;
                node.down = null;

            }
                public void deleteNode1(Node node){
                if (head==tail){
                    head=null;
                    tail=null;
                }else {
                    if (head==node){
                       head=node.down;
                       head.up =null;
                    }else if (tail==node){
                       tail=tail.up;
                       tail.down=null;
                    }else {
                        node.up.down=node.down;
                        node.down.up=node.up;
                    }
                }
                node.up=null;//check 加快释放
                node.down=null;//check 加快释放
            }

            public boolean isEmpty(){
                return head==null;
            }
        }

        int capacity,size;
        HashMap<Integer, Node> records;
        HashMap<Node,NodeList> heads;
        NodeList headList;

        public LFUCache(int capacity) {
            this.capacity = capacity;
            this.size=0;
            records=new HashMap<>();
            heads=new HashMap<>();
            headList=null;
        }
        private boolean modifyHeadList(NodeList nodeList) {
            if (nodeList.isEmpty()) {
                if (headList == nodeList) {
                    headList = nodeList.next;
                    if (headList != null) {
                        headList.last = null;
                    }
                } else {
                    nodeList.last.next = nodeList.next;
                    if (nodeList.next != null) {
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }
            return false;
        }

        private boolean modifyHeadList1(NodeList nodeList){
            if (nodeList.isEmpty()){
               //需要删除
               if (headList==nodeList){//是头部结点
                   headList=headList.next;
                   if (headList!=null){
                       headList.last=null;
                   }
               }else {
                    nodeList.last.next=nodeList.next;
                    if (nodeList.next!=null){
                        nodeList.next.last=nodeList.last;
                    }
               }
               return true;
            }
            return false;//不需要删除
        }


        private void move(Node node,NodeList oldNodeList){
            oldNodeList.deleteNode(node);//oldList要先删除一下node
            NodeList preList=modifyHeadList(oldNodeList)?oldNodeList.last:oldNodeList;
            NodeList nextList=oldNodeList.next;
            if (nextList==null){//最高词频
               NodeList newList=new NodeList(node);
               if (preList!=null){
                   preList.next=newList;//check
               }
               newList.last=preList;
               if (headList==null){
                   headList=newList;
               }
               heads.put(node,newList);//check
            }else {
               if (nextList.head.times.equals(node.times)){
                   nextList.addNodeFromHead(node);
                   heads.put(node,nextList);
               }else {
                  NodeList newList=new NodeList(node);
                  if (preList!=null){
                      preList.next=newList;
                  }
                  newList.last=preList;
                  newList.next=nextList;
                  nextList.last=newList;
                  if (headList==newList){
                      headList=newList;
                  }
                  heads.put(node,newList);
               }
            }
        }

        public void set(int key, int value) {
            if (records.containsKey(key)) {
                Node node = records.get(key);
                node.value = value;
                node.times++;
                NodeList curNodeList = heads.get(node);
                move(node, curNodeList);
            } else {
                if (size == capacity) {
                    Node node = headList.tail;
                    headList.deleteNode(node);
                    modifyHeadList(headList);
                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }
                Node node = new Node(key, value, 1);
                if (headList == null) {
                    headList = new NodeList(node);
                } else {
                    if (headList.head.times.equals(node.times)) {
                        headList.addNodeFromHead(node);
                    } else {
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last = newList;
                        headList = newList;
                    }
                }
                records.put(key, node);
                heads.put(node, headList);
                size++;
            }
        }

        public void set1(int key,int value){
            if(records.containsKey(key)){
               Node node=records.get(key);
               node.value=value;
               node.times++;
               NodeList curNodeList=heads.get(node);
               move(node,curNodeList);
            }else{
                if (size==capacity){
                   Node node=headList.tail;
                   headList.deleteNode(node);
                   modifyHeadList(headList);
                   records.remove(node.key);
                   heads.remove(node.key);
                   size--;
                }
                Node node=new Node(key,value,1);
                if(headList==null){
                    headList=new NodeList(node);
                }else {
                    if(headList.head.times.equals(node.times)){
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
        public int get(int key) {
            if (!records.containsKey(key)) {
                System.out.println("null");
                return -1;
            }
            Node node = records.get(key);
            node.times++;
            NodeList curNodeList = heads.get(node);
            move(node, curNodeList);
            System.out.println(node.value);
            return node.value;
        }

        public int get1(int key){
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
