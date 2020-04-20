package com.sf.jkt.k.algorithm.face.tree;

/**
 * https://www.cnblogs.com/rainple/p/9970760.html
 * https://www.cnblogs.com/xiaohualu/p/11815207.html
 *
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>> {

    private Entry<T> root;
    private int size = 0;

    static class Entry<T extends Comparable<T>> {
        private T item;
        Entry<T> left;
        Entry<T> right;
        Entry<T> parent;

        Entry(T item, Entry<T> parent) {
            this.item = item;
            this.parent = parent;
        }
    }

    public T put(T item) {
        Entry<T> t = root;
        if (t == null) {
            root = new Entry<T>(item, null);
            size++;
            return root.item;
        }
        int ret = 0;
        Entry<T> p = t;
        while (t != null) {
            ret = item.compareTo(t.item);
            p = t;
            if (ret < 0) {
                t = t.left;
            } else if (ret > 0) {
                t = t.right;
            } else {
                t.item = item;
                return t.item;
            }
        }

        Entry<T> e = new Entry<>(item, p);
        if (ret < 0) {
            p.left = e;
        } else {
            p.right = e;
        }

        size++;
        return e.item;

    }

    /***
     * https://blog.csdn.net/isea533/article/details/80345507
     * @param item
     * @return
     */
    public boolean remove(T item) {

        Entry<T> delEntry = getEntry(item);
        if (delEntry == null) {
            return false;
        }
        Entry<T> p = delEntry.parent;
        size--;
        if (delEntry.left == null && delEntry.right == null) {
            if (delEntry == root) {//根结点
                root = null;
            } else {
                if (delEntry == p.left) {
                    p.left = null;
                } else {
                    p.right = null;
                }
            }
        } else if (delEntry.right == null) {
            Entry<T> lc = delEntry.left;
            if (p == null) {//根结点
                lc.parent = null;
                root = lc;
            } else {
                //重置父亲结点的子节点
                if (delEntry == p.left) {
                    p.left = lc;
                } else {
                    p.right = lc;
                }
                lc.parent = p;//重置子节点的父结点
            }
        } else if (delEntry.left == null) {
            Entry<T> rc = delEntry.right;
            if (p == null) {
                rc.parent = null;//释放父结点引用
                root = rc;
            } else {
                if (delEntry == p.left) {
                    p.left = rc;
                } else {
                    p.right = rc;
                }
                rc.parent = p;
            }
        } else {
            //父结点、子结点都存在,要用后继结点替代删除结点，删除结点的位置存在，值被改变
            Entry<T> successor = getSuccessor(delEntry);
            delEntry.item = successor.item;
            if (delEntry.right == successor) {
                if (successor.right != null) {//后继结点有右结点
                    delEntry.right = successor.right;
                    successor.right.parent = delEntry;
                } else {
                    //右子节点没有子节点
                    delEntry.right = null;
                }
            } else {
                successor.parent.left = null;
            }
            return true;
        }
        //加快GC回收
        delEntry.parent = null;
        delEntry.left = null;
        delEntry.right = null;
        return true;
    }

    /**
     * 中序遍历获取后继结点，即为当前结点下一个结点
     * 如果右子树不为空，返回右子树的，最左子树
     * 如果右子树为空
     * 如果当前结点是父结点的左孩子，那么，当前结点的后继结点即为父结点
     * 如果当前结点不是父亲结点的左孩子，继续往上找，直到父亲结点是当前结点的左孩子,当前结点即为后继结点
     *
     * @param entry
     * @return
     */
    public Entry<T> getSuccessor(Entry<T> entry) {
        if (entry == null) {
            return null;
        }
        if (entry.right != null) {
            Entry<T> tmp = entry.right;
            while (tmp.left != null) {
                tmp = tmp.left;
            }
            return tmp;
        } else {
            Entry<T> parent = entry.parent;
            while (parent != null && parent.left != entry) {
                entry = parent;
                parent = entry.parent;
            }
            return parent;
        }

    }

    private Entry<T> getEntry(T item) {
        Entry<T> t = root;
        int ret;
        while (t != null) {
            ret = item.compareTo(t.item);
            if (ret < 0) {
                t = t.left;
            } else if (ret > 0) {
                t = t.right;
            } else {
                return t;
            }
        }
        return null;
    }

    public void preTravel(Entry<T> e) {
        if (e != null) {
            //中
            System.out.print(e.item + " ");
            //左
            preTravel(e.left);
            //右
            preTravel(e.right);
        }
    }

    public void midTravel(Entry<T> e) {
        if (e != null) {
            //左
            midTravel(e.left);
            //中
            System.out.print(e.item + " ");
            //右
            midTravel(e.right);
        }
    }

    public void sufTravel(Entry<T> e) {
        if (e != null) {
            sufTravel(e.left);
            sufTravel(e.right);
            System.out.print(e.item + " ");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Entry<T> getRoot() {
        return root;
    }

    public void clear() {
        clear(getRoot());
        root = null;
    }

    public void clear(Entry<T> entry) {
        if (entry != null) {
            clear(entry.left);
            entry.left = null;
            clear(entry.right);
            entry.right = null;
        }
    }

    public void print() {
        midTravel(root);
    }

    public boolean contains(T item) {
        return getEntry(item) != null;
    }


    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        //放数据
        binaryTree.put(73);
        binaryTree.put(22);
        binaryTree.put(532);
        binaryTree.put(62);
        binaryTree.put(72);
        binaryTree.put(243);
        binaryTree.put(42);
        binaryTree.put(3);
        binaryTree.put(12);
        binaryTree.put(52);

        System.out.println("size： " + binaryTree.size());
        binaryTree.put(52);
        System.out.println("添加相同元素后的size： " + binaryTree.size());
        //判断数据是否存在
        System.out.println("数据是否存在：" + binaryTree.contains(12));
        //中序遍历
        System.out.print("中序遍历结果： ");
        binaryTree.midTravel(binaryTree.getRoot());
        System.out.println();
        //前序遍历
        System.out.print("前遍历结果： ");
        binaryTree.preTravel(binaryTree.getRoot());
        System.out.println();
        //后序遍历
        System.out.print("后续遍历结果： ");
        binaryTree.sufTravel(binaryTree.getRoot());
        //删除数据
        System.out.println();
        binaryTree.remove(62);
        System.out.println("删除数据后判断是否存在：" + binaryTree.contains(62));
        //清空二叉树
        binaryTree.clear();
        System.out.print("清空数据后中序遍历： ");
        binaryTree.midTravel(binaryTree.getRoot());
    }

}
