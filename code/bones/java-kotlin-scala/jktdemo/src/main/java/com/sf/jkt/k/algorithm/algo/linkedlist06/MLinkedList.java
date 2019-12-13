package com.sf.jkt.k.algorithm.algo.linkedlist06;

import java.util.Stack;

public class MLinkedList {
    Node head = null;

    static class Node {
        Comparable data;
        Node next;

        public Node(Comparable data) {
            this(data, null);
        }

        public Node(Comparable data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Object getData() {
            return this.data;
        }

        public void printAll() {
            System.out.println(data);
            Node temp = next;
            while (temp != null) {
                System.out.println(temp.data);
                temp = temp.next;
            }
        }
    }

    public Node findByValue(Object value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            pos++;
        }
        return p;
    }

    public void insertIntoHead(Comparable value) {
        Node node = new Node(value, null);
        insertIntoHead(node);
    }

    public void insertIntoHead(Node value) {
        if (head == null) {
            head = value;
        } else {
            value.next = head;
            head = value;
        }
    }

    public void insertIntoTail(Comparable value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = node;
        }
    }

    public void insertAfter(Node node, Node newValue) {
        newValue.next = node.next;
        node.next = newValue;
    }

    public void insertBefore(Node node, Node newValue) {
        if (node == null) {
            return;
        }

        if (node == head) {
            insertIntoHead(newValue);
            return;
        }

        Node p = head;

        while (p != null && p.next != node) {
            p = p.next;
        }
        if (p == null) {
            return;
        }
        newValue.next = p.next;
        p.next = newValue;

    }

    public void deleteByNode(Node p) {
        if (p == null || head == null) {
            return;
        }
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            p = p.next;
        }
        if (p == null) {
            return;
        }
        p.next = p.next.next;

    }

    public void deleteByValue(Object value) {
        if (head == null) return;
        Node p = head;
        Node q = null;
        while (p != null && p.data != value) {
            q = p;//保存前驱结点
            p = p.next;
        }
        if (p == null) {
            return;
        }
        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }

//        if (head != null && head.data == value) {
//            head = head.next;
//        }
//
//        Node pNode = head;
//        while (pNode != null) {
//            if (pNode.next.data == value) {
//                pNode.next = pNode.next.next;
//                continue;
//            }
//            pNode = pNode.next;
//        }


    }

    /***
     * 两个链表是否相等
     * @param left
     * @param right
     * @return
     */
    public boolean trueFalseTest(Node left, Node right) {
        if (left == null && right == null) {
            return true;
        } else if (left != null && right != null) {
            boolean flag = true;
            Node l = left;
            Node r = right;
            while (l != null && r != null) {
                if (l.data == r.data) {
                    l = l.next;
                    r = r.next;
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            return flag;
        } else {
            return false;
        }
    }

    public static boolean palindrome(int[] arr) {
        Stack<Integer> stack = new Stack();
        for (int i = 0; i < arr.length; i++) {
            stack.push(arr[i]);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != stack.pop()) {
                return false;
            }
        }
        return true;
    }

    public static boolean palindrome1(int[] arr) {
        return false;
    }

    /**
     * 判断单链表是否有环
     *
     * @param head
     * @return
     */
    public boolean hasCycle(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;

    }

    /**
     * 找到回文入口点
     *
     * @param head
     * @return
     */
    public Node entryNodeOfLoop(Node head) {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                //有回文,此结点为碰撞点
                slow = head;
                while (slow != head) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }

    /**
     * 链表倒置
     *
     * @param head
     * @return
     */
    public Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node temp = head.next;
        Node newTempNode = reverse(head);
        temp.next = head;
        head.next = null;
        return newTempNode;
    }

    public static Node reverse2(Node head) {

        Node pre = null;
        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            temp.next = pre;
            pre = temp;
            temp = next;
        }
        return pre;
    }


    public static Node reverse3(Node node) {
        Node cur = node, pre = null;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    /***
     * 合并两个有序链表
     * @param l1
     * @param l2
     * @return
     */
    public static Node mergeSort(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        Node head = null;
        if (l1.data.compareTo(l2.data) <= 0) {
            head = l1;
            head.next = mergeSort(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeSort(l1, l2.next);
        }
        return head;
    }

    /***
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，
     * 返回链表头指针。 例如，链表1->2->3->3->4->4->5处理后为 1->2->5
     * @param pHead
     * @return
     */
    public static Node deleteDuplicat(Node pHead) {
        if (pHead == null) {
            return pHead;
        }
        Node now = new Node(-1, null);
        now.next = pHead;
        Node pre = now;
        Node cur = pHead;
        Node n = pHead.next;
        while (cur != null && n != null) {
            if (cur.data == n.data) {
                //找到重复的 cur 和 next 重复
//                Node before=null;
                while (n != null && n.data == cur.data) {
//                    before=n;
                    n = n.next;
                }
                //n.data!=cur.data
                pre.next = n;//跳过重复结点
                cur = pre.next;
                if (n != null) {
                    n = n.next;
                }
            } else {
                pre = cur;
                cur = n;
                n = n.next;//确保能够到达终止条件
            }
        }
        return now.next;
    }


    public Node deleteDuplication(Node pHead) {
        if (pHead == null)
            return pHead;
        Node now = new Node(-1);
        now.next = pHead;
        Node pre = now;
        Node cur = pHead;
        Node n = pHead.next;
        while (cur != null && n != null) {
            if (cur.data == n.data) {
                while (n != null && n.data == cur.data)
                    n = n.next;
                pre.next = n;
                cur = pre.next;
                if (n != null)
                    n = n.next;
            } else {
                pre = cur;
                cur = n;
                n = n.next;
            }
        }
        return now.next;
    }

    /***
     * 找到倒数第k个Node
     * @param node
     * @param k
     * @return
     */
    public static Node findLastK(Node node, int k) {
        if (node == null) {
            return node;
        }
        Node cur = node;
        Node curK = node;
        for (int i = 0; i < k; i++) {
            curK = curK.next;
            if (curK == null) {
                //链表长度小于k
                return null;
            }
        }
        while (curK != null) {
            cur = cur.next;
            curK = curK.next;
        }
        return cur;
    }

    public static void main(String[] args) {
        testDelete();

    }

    public static void testPalindrome() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 3, 2, 1};
        System.out.println(palindrome(arr));
        arr = new int[]{1, 2, 3, 2, 1};
        System.out.println(palindrome(arr));
        arr = new int[]{1, 2, 3, 3, 2, 1};
        System.out.println(palindrome(arr));
    }

    public static void testDelete() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(2);
        Node n4 = new Node(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        Node result = deleteDuplicat(n1);
        result.printAll();
    }

}
