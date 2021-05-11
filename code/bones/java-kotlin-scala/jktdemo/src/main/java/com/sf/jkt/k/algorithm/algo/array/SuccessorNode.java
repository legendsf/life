package com.sf.jkt.k.algorithm.algo.array;

/**
 * 寻找一个节点的后继节点和前驱节点
 */
public class SuccessorNode {
    private static class Node {
        private Integer value;
        private Node left;
        private Node right;
        private Node parent;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }

    }

    /**
     * 传入一个node，返回该node的后继节点
     *
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right != null) {
            return getMostLeft(node.right);
        } else {
            Node parent = node.parent;
            while (parent != null && node != parent.left) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 返回node为根节点的最左边节点
     *
     * @param node
     * @return
     */
    private static Node getMostLeft(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 返回node节点的前驱节点
     *
     * @param node
     * @return
     */
    public static Node getPioneerNode(Node node) {
        if (node == null) {
            return node;
        }
        if (node.left != null) {
            return getMostRight(node.left);
        } else {
            Node parent = node.parent;
            //找到parent=null 或者 parent 右边为当前结点，那么parent就是前驱
            while (parent != null && node != parent.right) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        }
    }

    /**
     * 返回以node为根节点的最右边节点
     *
     * @param node
     * @return
     */
    private static Node getMostRight(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    /***
     *             6
     *      3            9
     *
     *   1    4       8   10
     *
     *     2     5  7
     *
     */

    public static void test1() {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        System.out.println(getPioneerNode(head.left.left));
        System.out.println(getSuccessorNode(head.left.left).value);

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }

    /***
     *        1
     *      2
     *     3
     *    4  6
     *   5     7
     */
    public static Node buildTree() {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        n1.left = n2;
        n2.left = n3;
        n3.left = n4;
        n3.right = n6;
        n4.left = n5;
        n6.right = n7;

        n7.parent=n6;
        n5.parent=n4;
        n6.parent=n3;
        n3.parent=n2;
        n2.parent=n1;
        System.out.println(getPioneerNode(n5));
        System.out.println(getSuccessorNode(n5));
        return n1;
    }

    public static void main(String[] args) {
        buildTree();
    }

}