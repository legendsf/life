package com.sf.jkt.k.algorithm.study.zcy.gaoji.s34.advanced_class_03;

import java.util.ArrayList;
import java.util.Iterator;

public class Code_02_SkipList1 {

    public static class SkipListNode {
        public Integer value;
        public ArrayList<SkipListNode> nextNodes;//10层,存放的都是下一个结点是啥

        public SkipListNode(Integer value) {
            this.value = value;
            nextNodes = new ArrayList<SkipListNode>();
            nextNodes.add(null);
        }

        @Override
        public String toString() {
            return "SkipListNode{" +
                    "value=" + value +
                    '}';
        }
    }

    public static class SkipListIterator implements Iterator<Integer> {
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList list) {
            this.list = list;
            this.current = list.getHead();
        }

        public boolean hasNext() {
            return current.nextNodes.get(0) != null;
        }

        public Integer next() {
            current = current.nextNodes.get(0);
            return current.value;
        }
    }

    public static class SkipList {
        private SkipListNode head;//巨小，所有层数最大值的层数
        private int maxLevel;//所有数据roll出来的最大层
        private int size;//加进来了多少key
        private static final double PROBABILITY = 0.5;//我以多少概率出来0

        public SkipList() {
            size = 0;
            maxLevel = 0;
            head = new SkipListNode(null);//默认最小值
//            head.nextNodes.add(null);//第0层丢弃不用，设置为null
        }

        public SkipListNode getHead() {
            return head;
        }

        public void add(Integer newValue) {
            if (!contains(newValue)) {
                size++;
                int level = 0;
                while (Math.random() < PROBABILITY) {
                    level++;
                }
                while (level > maxLevel) {//头一定要增加层数
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                SkipListNode newNode = new SkipListNode(newValue);//新的值
                SkipListNode current = head;//当前head 总是从头开始找
                int levelAll = maxLevel;
                do {
                    current = findNext(newValue, current, levelAll)[1];//当前结点新的值，在第level层找
                    if (level >= levelAll) {
                        newNode.nextNodes.add(0, current.nextNodes.get(level));//前插，先把 17设置到0位置，再把15设置到0位置
                        current.nextNodes.set(level, newNode);//current第level层的引用让他指向newNode
                        level--;
                    }
                } while (levelAll-- > 0);//一定会找到第一层
            }
        }

        public void delete(Integer deleteValue) {
            if (contains(deleteValue)) {
               SkipListNode deleteNode = find(deleteValue);
                size--;
                int level = maxLevel;
                SkipListNode prev=null;
               SkipListNode current = head;
                do {
                    //todo 错误，应该从最高层开始找
                    SkipListNode[] skipListNodes= findNext(deleteNode.value, current, level);
                    current =skipListNodes[1];
                    prev=skipListNodes[0];
                    if (deleteNode.nextNodes.size() > level) {
                        prev.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }

        // Returns the skiplist node with greatest value <= e
        private SkipListNode find(Integer e) {
            return find(e, head, maxLevel);
        }

        // Returns the skiplist node with greatest value <= e
        // Starts at node start and level
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level)[1];
            } while (level-- > 0);
            return current;
        }

        // Returns the node at a given level with highest value less than e
        private SkipListNode[] findNext(Integer e, SkipListNode current, int level) {
            SkipListNode next = current.nextNodes.get(level);//第七层的下一个结点怎么得到
            SkipListNode prev=current;
            while (next != null) {
                Integer value = next.value;
                if (lessThan(e, value)) { // e < value //找到对应结点，
                    break;
                }
                prev=current;
                current = next;//current 在这一层里面最后一个小于当前数的
                next = current.nextNodes.get(level);//同一层下一个值比当前小就往右边移动
            }
            return new SkipListNode[]{prev,current};
        }

        public int size() {
            return size;
        }

        public boolean contains(Integer value) {
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }

        /******************************************************************************
         * Utility Functions *
         ******************************************************************************/

        private boolean lessThan(Integer a, Integer b) {
            return a.compareTo(b) < 0;
        }

        private boolean equalTo(Integer a, Integer b) {
            return a.compareTo(b) == 0;
        }

    }

    public static void test1() {
        while (true) {
            SkipList skipList = new SkipList();
            skipList.add(1);
            skipList.add(19);
            skipList.add(11);
            skipList.add(7);

            System.out.println(skipList.find(19).value);
            skipList.delete(1);
            skipList.delete(3);
            System.out.println(skipList.find(1).value);
            System.out.println(skipList.contains(1));
            if (skipList.contains(1)){
                System.out.println("不应该包含1");
                break;
            }
        }
    }

    public static void main(String[] args) {
        test1();
    }

}
