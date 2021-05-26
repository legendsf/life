package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

class RBTree {
    private TreeNode root;
    private boolean leftFirst = false;

    RBTree(TreeNode root) {
        this.root = root;
    }

    void setLeftFirst(boolean leftFirst) {
        this.leftFirst = leftFirst;
    }

    private static class TreeNode {
        private static final int BLACK = 0;
        private static final int RED = 1;

        private int value;
        private int color = BLACK;
        private TreeNode parent;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(int value) {
            this.value = value;
        }
    }

    /*******************************************************************************************************************
     * 插入新结点
     *******************************************************************************************************************/
    void insert(int value) {
        TreeNode cur = new TreeNode(value);

        TreeNode p = root;
        TreeNode g = p;
        while (p != null) {
            g = p;
            if (cur.value < p.value) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        if (g == null) {
            root = cur;
        } else if (cur.value < g.value) {
            g.left = cur;
        } else {
            g.right = cur;
        }
        cur.parent = g;
        cur.color = TreeNode.RED;

        // 插入完后，修正红黑树
        // 可能违背：
        // 第4点：如果一个节点是红色的，则它的子节点必须是黑色的
        // 第2点：新插入结点就是根节点，因此，需要着色为黑色
        fixedInsert(cur);
    }

    /*******************************************************************************************************************
     *   g: 祖父节点
     *   u：叔叔节点
     *   p：父节点
     * cur：新插入节点
     *******************************************************************************************************************/
    private void fixedInsert(TreeNode cur) {
        TreeNode g, u;
        TreeNode p;
        while ((p = cur.parent) != null && p.color == TreeNode.RED) {
            g = p.parent;

            /**********************************************************************************
             * 【左、右】算法互为镜像
             **********************************************************************************/
            if (g != null) {

                /******************************************************************************
                 * 叔叔节点且红色：
                 * 1. 修改：p & u  为黑色；
                 * 2. 修改：g      为红色；
                 ******************************************************************************/
                u = (p == g.left ? g.right : g.left);
                if (u != null && u.color == TreeNode.RED) {
                    u.color = TreeNode.BLACK;
                    p.color = TreeNode.BLACK;
                    g.color = TreeNode.RED;
                    cur = g;
                    continue;
                }

                /******************************************************************************
                 * 左子树
                 ******************************************************************************/
                if (p == g.left) {
                    /**************************************************************************
                     *     g            g            g
                     *    /            /            /
                     *   p     =>    cur     =>    p
                     *    \          /            /
                     *     cur      p            cur
                     **************************************************************************/
                    if (cur == p.right) {
                        rotateLeft(p);
                        TreeNode temp = cur;
                        cur = p;
                        p = temp;
                    }

                    /**************************************************************************
                     * 1. 叔叔节点为黑色
                     * 2. 叔叔节点不存在，即叶子结点NIL也是黑色
                     *
                     *       g          p
                     *      /          / \
                     *     p   =>    cur  g
                     *    /
                     *  cur
                     **************************************************************************/
                    p.color = TreeNode.BLACK;
                    g.color = TreeNode.RED;
                    rotateRight(g);

                } else {
                    /**************************************************************************
                     *     g             g            g
                     *      \            \            \
                     *       p     =>    cur     =>    p
                     *      /             \            \
                     *     cur             p           cur
                     *************************************************************************/
                    if (cur == p.left) {
                        rotateRight(p);
                        TreeNode temp = cur;
                        cur = p;
                        p = temp;
                    }

                    /**************************************************************************
                     * 1. 叔叔节点为黑色
                     * 2. 叔叔节点不存在，即叶子结点NIL也是黑色
                     *
                     *     g                p
                     *     \               / \
                     *     p       =>     g  cur
                     *      \
                     *      cur
                     **************************************************************************/
                    p.color = TreeNode.BLACK;
                    g.color = TreeNode.RED;
                    rotateLeft(g);
                }
            }
        }

        // 最后，必需确保满足根节点必需是黑色
        // 因为我们的调整，到根节点（当 g 点为 null 时，表明 parent 已经为树根）
        root.color = TreeNode.BLACK;
    }

    /*******************************************************************************************************************
     * 删除指定结点
     *******************************************************************************************************************/
    void remove(int value) {
        if (root == null) {
            return;
        }

        /**************************************************************************************
         * 二分法，先查找需要删除的节点
         **************************************************************************************/
        TreeNode p = root;
        while (p != null) {
            if (p.value == value) {
                break;
            } else if (p.value > value) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        // 没有找到指定值的节点
        if (p == null) {
            return;
        }

        p = findPreOrNextNode(p);
        System.out.println("--- 最终删除节点 => | " + p.value + " |---");

        fixedRemove(p);
        if (p == p.parent.left) {
            p.parent.left = null;
        } else {
            p.parent.right = null;
        }
    }

    /*******************************************************************************************************************
     *  查找后继或前驱节点，最终转化为删除（含有一个 or 零个）的子节点
     * 【返回最终要删除的子节点】
     *******************************************************************************************************************/
    private TreeNode findPreOrNextNode(TreeNode p) {
        if (p.left != null || p.right != null) {
            // g 为指向删除的节点
            TreeNode g = p;

            /**********************************************************************************
             * 查找
             * 1. 后继：仅比待删除节点次大的节点
             * or
             * 2. 前驱：仅比待删除节点次小的节点
             **********************************************************************************/
            if (p.right != null && !leftFirst) {
                // 查找后继节点
                p = p.right;
                while (p.left != null) {
                    p = p.left;
                }
            } else {
                // 查找前驱节点
                p = p.left;
                while (p.right != null) {
                    p = p.right;
                }
            }

            /**********************************************************************************
             * 交换【待删除节点】与【后继/前驱】的值，改为删除【后继/前驱】节点
             **********************************************************************************/
            g.value = p.value;

            p = findPreOrNextNode(p);
        } else {
            /**********************************************************************************
             * 待删除节点没有左、右儿子，就是删除自己
             **********************************************************************************/
        }
        return p;
    }

    /*******************************************************************************************************************
     * cur为新的删除节点，需要考虑：
     * 1、cur 没有儿子：
     *    1.1、cur 为红色节点，则直接删除；
     *    1.2、cur 为黑色节点，需要考虑其兄弟节点；
     * 2、cur 有一个儿子：
     *    2.1、交互 cur 与其儿子节点的值，改为删除儿子节点；
     *    2.2、重复第1步；
     * 3、cur 不可能有两个儿子：
     *    3.1、根据 remove，cur 要么为后继、要么为前驱、要么没有儿子；
     *    3.2、所以只存在上述1、2；
     *    3.3、且第2步最终也转化为第1步需要考虑的 1.1 或 1.2 情况；
     *******************************************************************************************************************/
    private void fixedRemove(TreeNode cur) {
        while (cur != root && cur.color == TreeNode.BLACK) {
            /***********************************************************************************************************
             * cur 为待删除节点
             * b 为兄弟节点
             * p 为父节点
             *
             * 【以下分析基于 cur 为左节点，若为右节点，则条件相反】
             * 因为 cur 节点存在且为黑色，所以，其兄弟节点一定存在：
             *
             * 1、若 b 节点为红色，则满足红黑树条件的情况下，b 的儿子节点一定存在，且有两个为黑色的儿子：
             *    设 b 左儿子为红色，b 为黑色，对 p 向左旋转；
             *
             * 2、若 b 节点为黑色：
             *    2.1、b 有一个右儿子（一定是红色），将 p 的颜色给 b，p 和 b 的右儿子设为黑色，对 p 向左旋转；
             *    2.2、b 有一个左儿子（一定是红色），将儿子设为黑色，b设为红色，b 是右节点，对 b 向右旋转；（此时情况同 2.1）
             *    2.3、b 有两个儿子（一定是红色），此时，处理同 2.1；
             *
             *    2.4、b 没有儿子，则设 b 为红色，cur 指向 p，继续向上递归至根节点，或遇到红色节点为止；
             *        之所以要向上递归，是因为 p 可红可黑，b 从黑色改变为红色，此时就少了一个黑色节点，条件5可能不满足，
             *        需要检查 p 节点颜色及其兄弟；
             ***********************************************************************************************************/

            TreeNode p = cur.parent;
            TreeNode b;

            if (cur == p.left) {
                /*********************************************************************************
                 * 待删除节点为左节点
                 *********************************************************************************/
                b = p.right;

                // 1
                if (b.color == TreeNode.RED) {
                    b.left.color = TreeNode.RED;
                    b.color = TreeNode.BLACK;
                    rotateLeft(p);
                    break;
                }

                // 2.4
                if (b.left == null && b.right == null) {
                    b.color = TreeNode.RED;
                    cur = p;
                    continue;
                }

                // 2.2
                if (b.left != null && b.right == null) {
                    b.left.color = TreeNode.BLACK;
                    b.color = TreeNode.RED;
                    rotateRight(b);
                }

                // 2.1、2.3 以及 2.2 -> 2.1
                b.color = p.color;
                p.color = b.right.color = TreeNode.BLACK;
                rotateLeft(p);
                break;

            } else {
                /******************************************************************************
                 * 待删除节点为右节点
                 ******************************************************************************/
                b = p.left;

                // 1
                if (b.color == TreeNode.RED) {
                    b.right.color = TreeNode.RED;
                    b.color = TreeNode.BLACK;
                    rotateLeft(p);
                    break;
                }

                // 2.4
                if (b.left == null && b.right == null) {
                    b.color = TreeNode.RED;
                    cur = p;
                    continue;
                }

                // 2.2
                if (b.right != null && b.left == null) {
                    b.right.color = TreeNode.BLACK;
                    b.color = TreeNode.RED;
                    rotateLeft(b);
                }

                // 2.1、2.3 以及 2.2 -> 2.1
                b.color = p.color;
                p.color = b.left.color = TreeNode.BLACK;
                rotateRight(p);
                break;
            }
        }

        // 可能是2.4退出，将 p 节点强制黑色，以实现动态平衡
        cur.color = TreeNode.BLACK;
    }

    /*****************************************************************************************************************
     *   (1)                                                      |  (2)
     *              g(10)                          u(20)          |         g            g
     *            /      \                       /     \          |        /            /
     *        p(5)       u(20)        =>     g(10)     z(22)      |       p     =>     u
     *       /   \       /    \             /    \                |        \          /
     *      c(2) x(8)   y(16) z(22)      p(5)    y(16)            |         u        p
     *                                  /   \                     |
     *                               c(2)   x(8)                  |
     *                                                            |
     *****************************************************************************************************************/
    private void rotateLeft(TreeNode parent) {
        TreeNode right = parent.right;  // u

        //【右子左节点】挂到【父的右节点】
        parent.right = right.left;      // g.右节点 = y
        if (right.left != null) {
            right.left.parent = parent; // y.父节点 = g
        }

        // 【祖父】与【左子 or 右子】
        right.parent = parent.parent;   // u.父节点 = g.父节点
        if (parent.parent != null) {
            if (parent.parent.left == parent) {
                parent.parent.left = right; // 祖父节点.左节点 = u
            } else {
                parent.parent.right = right;
            }
        } else {
            root = right;
        }

        // 【父】与【右子】替换
        parent.parent = right;          // g.父节点 = u
        right.left = parent;            // u.左节点 = g
    }

    /*****************************************************************************************************************
     *  (1)                                                                 |  (2)
     *              g(10)                          p(5)                     |           p           g
     *            /      \                       /     \                    |          /           / \
     *        p(5)       u(20)        =>     c(2)       g(10)               |         g     =>    x   p
     *       /   \       /    \                        /    \               |        /
     *      c(2) x(8)   y(16) z(22)                  x(8)    u(20)          |       x
     *                                                      /    \          |
     *                                                     y(16) z(22)      |
     *                                                                      |
     *****************************************************************************************************************/
    private void rotateRight(TreeNode parent) {
        TreeNode left = parent.left;    // p

        // 【左子右节点】挂到【父的左节点】
        parent.left = left.right;       // g.左节点 = x
        if (left.right != null) {
            left.right.parent = parent; // x.父节点 = g
        }

        // 【祖父】与【左子 or 右子】
        left.parent = parent.parent;    // p.父节点 = g.父节点
        if (parent.parent != null) {
            if (parent.parent.left == parent) {
                parent.parent.left = left;
            } else {
                parent.parent.right = left;
            }
        } else {
            root = left;
        }

        // 【父】与【左子】替换
        parent.parent = left;           // g.父节点 = p
        left.right = parent;            // p.右节点 = g
    }

    /*******************************************************************************************************************
     * 前序遍历
     *******************************************************************************************************************/
    void doPreTravel() {
        Deque<HashMap<Integer, TreeNode>> deque = new ArrayDeque<>();
        deque.add(getMap(root, 0));

        int depth = 0;
        while (deque.size() > 0) {
            HashMap<Integer, TreeNode> map = deque.pollFirst();
            int key = map.keySet().iterator().next();
            if (depth == key) {
                TreeNode node = map.get(key);
                System.out.print(node.value + "(" + (node.color == TreeNode.BLACK ? "black" : "red") + ")\t");

                if (node.left != null) {
                    deque.add(getMap(node.left, depth + 1));
                }
                if (node.right != null) {
                    deque.add(getMap(node.right, depth + 1));
                }

            } else {
                depth = key;
                System.out.println();
                deque.addFirst(map);
            }
        }
        System.out.println();
    }

    private HashMap<Integer, TreeNode> getMap(TreeNode node, int depth) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        map.put(depth, node);
        return map;
    }
}
