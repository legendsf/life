package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.*;

public class TravelDemo {

    public static TreeNode createTree(){
        return null;
    }

    public static List<Integer> preTravel(TreeNode node){
        List<Integer> ans=new ArrayList<>();
        preTravel(node,ans);
        return ans;
    }
    public static void preTravel(TreeNode node,List<Integer> ans){
       if(node==null){
           return;
       }
       ans.add(node.val);
       preTravel(node.left,ans);
       preTravel(node.right,ans);
    }
    public static void midTravel(TreeNode node,List<Integer> ans){
        if(node==null){
            return;
        }
        midTravel(node.left,ans);
        ans.add(node.val);
        midTravel(node.right,ans);
    }

    public static void postTravel(TreeNode node, List<Integer> ans){
        if(node==null){
            return;
        }
        postTravel(node.left,ans);
        postTravel(node.right,ans);
        ans.add(node.val);
    }

    public static void levelTravel(TreeNode node,List<List<Integer>> ans,int level){
        if(node==null){
            return;
        }
        if(ans.size()<=level){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(node.val);
        levelTravel(node.left,ans,level+1);
        levelTravel(node.right,ans,level+1);
    }

    public List<List<Integer>> levelOrder(TreeNode root){
        List<List<Integer>>ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode temp=queue.poll();
                level.add(temp.val);
                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if(temp.right!=null){
                    queue.add(temp.right);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> levelOrder1(TreeNode root){
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        List<TreeNode> previousLayer= Arrays.asList(root);
        while (!previousLayer.isEmpty()){
            List<TreeNode> currentLayer=new ArrayList<>();
            List<Integer> previousLayerVals=new ArrayList<>();
            for (TreeNode node:previousLayer){
                previousLayerVals.add(node.val);
                currentLayer.add(node.left);
                currentLayer.add(node.right);
            }
            ans.add(previousLayerVals);
            previousLayer=currentLayer;
        }
        return ans;
    }

    public static void testSrotTree(){
        int[]arr={7,7,7,9,10,12,5,1,9};
        BinarySortTree binarySortTree =new BinarySortTree();
        for(int i:arr){
            binarySortTree.add(new Node(i));
        }
        binarySortTree.midSort();


//        Node node=binarySortree.search(7);
//        System.out.println(node.value);
//        binarySortree.delete(12);

        //删除value=7的节点
        binarySortTree.delete(7);
        System.out.println("----------");
        binarySortTree.midSort();


//        Node node1 = binarySortree.searchParent(10);
//        System.out.println(node1.value);

    }

    public static void main(String[] args) {
        testSrotTree();
    }
}
