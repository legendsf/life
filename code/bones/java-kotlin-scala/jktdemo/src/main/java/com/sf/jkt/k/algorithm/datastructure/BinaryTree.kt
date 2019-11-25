package com.sf.jkt.k.algorithm.datastructure

import java.util.*




class BinaryTree(var data: Comparable<Any>, var leftChild: BinaryTree?, var rightChild: BinaryTree?) {
    fun preOrderTraverse(tree: BinaryTree?) {
        if (tree != null) {
            println(tree.data)
            preOrderTraverse(tree.leftChild)
            preOrderTraverse(tree.rightChild)
        }
    }

    fun midOrderTraverse(tree: BinaryTree?) {
        if (tree != null) {
            midOrderTraverse(tree.leftChild)
            println(tree.data)
            midOrderTraverse(tree.rightChild)
        }
    }

    fun postOrderTraverse(tree: BinaryTree?) {
        if (tree != null) {
            postOrderTraverse(tree.leftChild)
            postOrderTraverse(tree.rightChild)
            println(tree.data)
        }
    }

    fun levelOrderTraverse(tree: BinaryTree) {
        var list = LinkedList<BinaryTree>()
        list.add(tree)
        while (list.isNotEmpty()) {
            var data = list.poll()
            println(data)
            if (tree.leftChild != null) {
                list.offer(tree.leftChild)
            }
            if (tree.rightChild != null) {
                list.offer(tree.rightChild)
            }
        }
    }

    fun mirror(tree: BinaryTree?) {
        if (tree == null) {
            return
        }
        if (tree.leftChild == null && tree.rightChild == null) {
            return
        }
        var temp = tree.leftChild
        tree.rightChild = temp
        tree.leftChild = temp
        if (tree.leftChild != null) {
            mirror(tree.leftChild)
        }
        if (tree.rightChild != null) {
            mirror(tree.rightChild)
        }
    }



}
