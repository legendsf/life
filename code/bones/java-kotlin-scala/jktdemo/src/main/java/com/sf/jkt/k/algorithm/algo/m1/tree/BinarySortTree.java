package com.sf.jkt.k.algorithm.algo.m1.tree;

/**
 * @Author WYMY
 * https://www.cnblogs.com/hu-yewen/p/5542591.html
 *
 */
public class BinarySortTree {
    //根节点
    Node root;

    /**
     * 添加节点
     *
     * @param node
     */
    void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    /**
     * 中序遍历
     */
    public void midSort() {
        midSort(root);
    }

    public void midSort(Node node) {
        if (node == null) {
            return;
        }
        midSort(node.left);
        //根据count输出重复节点
        for (int i = 0; i <= node.count; i++) {
            System.out.print(node.value+" ");
        }
        midSort(node.right);
    }

    /**
     * 根据值查询节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (null == root) {
            return null;
        }
        return root.search(value);
    }

    /**
     * 删除节点
     *
     * @param i
     */
    public void delete(int i) {
        if (root == null) {
            return;
        }
        Node target = search(i);
        if (target == null) {
            return;
        }
        Node parent = searchParent(i);
        //如果根节点没有子节点（只有根节点的树）
        if (parent == null && target.right == null && target.left == null) {
            root = null;
            return;
        }
        if (target.right == null && target.left == null) {
            if (target.value == parent.left.value) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (target.right != null && target.left != null) {
            //删除节点有两个子节点
            Node node = deleteMin(target.right);
            //替换目标节点内容
            target.value = node.value;
            target.count = node.count;

            //需要删除的节点只有一个子节点
        } else {
            //目标节点左节点不为空
            if (target.left != null) {
                if (target.value == parent.left.value) {
                    parent.left = target.left;
                } else {
                    parent.right = target.left;
                }
            } else {//目标节点右节点不为空
                if (target.value == parent.left.value) {
                    parent.left = target.right;
                } else {
                    parent.right = target.right;
                }
            }
        }

        // root.delete(i);
    }

    /**
     * 删除树中最小节点
     *
     * @param node
     * @return
     */
    private Node deleteMin(Node node) {
        Node target = node;
        //找到最小节点
        while (target.left != null) {
            target = target.left;
        }
        //如果目标节点有右节点
        delete(target.value);
        return target;
    }

    /**
     * 查询父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    public static void test1(){
        BinarySortTree bst=new BinarySortTree();
        bst.add(new Node(100));
        bst.add(new Node(80));
        bst.add(new Node(120));
        bst.add(new Node(60));
        bst.add(new Node(90));
        bst.add(new Node(85));
        bst.levelTravel();
    }

    public void levelTravel(){
        root.levelTravel();
    }

    public static void main(String[] args) {
        test1();
    }
}
