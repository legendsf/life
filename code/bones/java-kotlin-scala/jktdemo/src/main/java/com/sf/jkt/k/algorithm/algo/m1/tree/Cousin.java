package com.sf.jkt.k.algorithm.algo.m1.tree;

import java.util.Deque;
import java.util.LinkedList;

public class Cousin {

    public static boolean isCousins(TreeNode root, int x, int y) {
        Deque<TreeNode[]> q = new LinkedList<>();
        q.offer(new TreeNode[]{root, null});
        while (!q.isEmpty()) {
            int size = q.size();
            int fx = 0, fy = 0;
            TreeNode[] candidates = new TreeNode[2];
            for (int i = 0; i < size; i++) {
                TreeNode[] poll = q.poll();
                TreeNode cur = poll[0], parent = poll[1];
                if (cur.val == x) {
                    fx = 1;
                    candidates[0] = parent;
                } else if (cur.val == y) {
                    fy = 1;
                    candidates[1] = parent;
                }
                if (cur.left != null) {
                    q.offer(new TreeNode[]{cur.left, cur});
                }
                if (cur.right != null) {
                    q.offer(new TreeNode[]{cur.right, cur});
                }
            }
            if ((fx | fy) ==0 ) {
                continue;
            }
            if ((fx ^ fy) == 1){
                return false;
            }
            if ((fx & fy) == 1) {
                return candidates[0] != candidates[1];
            }
        }
        return false;
    }


   public static class Cusion1{
        int x;
        TreeNode xParent;
        int xDepth;
        boolean xFound=false;

        int y;
        TreeNode yParent;
        int yDepth;
        boolean yFound=false;

        public boolean isCousions(TreeNode root,int x,int y){
            this.x=x;
            this.y=y;
            dfs(root,0,null);
            return xDepth==yDepth&&xParent!=yParent;
        }

        public void  dfs(TreeNode node,int depth,TreeNode parent){
            if(node==null){
                return;
            }
            if(node.val==x){
                xParent=parent;
                xDepth=depth;
                xFound=true;
            }else if(node.val==y){
                yParent=parent;
                yDepth=depth;
                yFound=true;
            }
            if(xFound&&yFound){
                return;
            }
            dfs(node.left,depth+1,node);
            if(xFound&&yFound){
                return;
            }
            dfs(node.right,depth+1,node);
        }


    }


    public static class Cusion2{

    }

    public static boolean isCousins1(TreeNode root,int x,int y){
        Deque<TreeNode[]> queue=new LinkedList<>();//index=1 存放父亲
        queue.offer(new TreeNode[]{root,null});
        while (!queue.isEmpty()){
           int size=queue.size();
           int fx=0,fy=0;
           TreeNode [] candidates=new TreeNode[2];
           for (int i=0;i<size;i++){
               TreeNode[] poll=queue.poll();
               TreeNode cur=poll[0],parent=poll[1];
               if(cur.val==x){
                   fx=1;
                   candidates[0]=parent;
               }else if(cur.val==y){
                   fy=1;
                   candidates[1]=parent;
               }
               if(cur.left!=null){
                   queue.offer(new TreeNode[]{cur.left,cur});
               }
               if(cur.right!=null){
                   queue.offer(new TreeNode[]{cur.right,cur});
               }
           }

            if((fx|fy)==0){
                continue;
            }
            if((fx^fy)==1){
                return false;
            }
            if((fx&fy)==1){
                return candidates[0]!=candidates[1];
            }
        }
        return false;
    }

    public static boolean isCousins2(TreeNode root,int x,int y){
        if(root==null){
            return false;
        }
        Deque<TreeNode[]> queue=new LinkedList<>();
        queue.offer(new TreeNode[]{root,null});
        while (!queue.isEmpty()){
            int size=queue.size();
            int fx=0,fy=0;
            TreeNode[] candidates=new TreeNode[2];
            for (int i=0;i<size;i++){
              TreeNode[] poll=queue.poll();
              TreeNode cur=poll[0],parent=poll[1];
              if (cur.val==x){
                  fx=1;
                  candidates[0]=parent;
              }else if(cur.val==y){
                  fy=1;
                  candidates[1]=parent;
              }
              if(cur.left!=null){
                  queue.offer(new TreeNode[]{cur.left,cur});
              }
              if(cur.right!=null){
                  queue.offer(new TreeNode[]{cur.right,cur});
              }
            }
            if((fx|fy)==0){
                continue;
            }
            if((fx^fy)==1){
                return  false;
            }
            if((fx&fy)==1){
                return candidates[0]!=candidates[1];
            }
        }
        return false;
    }

    /***
     *
     *                 1
     *             2        3
     *           4   5   6    7
     *         8
     *
     * @return
     */
    public static TreeNode createTree(){
        TreeNode n1=new TreeNode(1);
        TreeNode n2=new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);
        TreeNode n6=new TreeNode(6);
        TreeNode n7=new TreeNode(7);
        TreeNode n8=new TreeNode(8);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n3.left=n6;
        n3.right=n7;
        n4.left=n8;
        return n1;
    }

    public static void test1(){
        TreeNode n1=new TreeNode(1);
        TreeNode n2=new TreeNode(2);
        TreeNode n3=new TreeNode(3);
        TreeNode n4=new TreeNode(4);
        TreeNode n5=new TreeNode(5);
        TreeNode n6=new TreeNode(6);
        TreeNode n7=new TreeNode(7);
        TreeNode n8=new TreeNode(8);
        n1.left=n2;
        n1.right=n3;
        n2.left=n4;
        n2.right=n5;
        n3.left=n6;
        n3.right=n7;
        n4.left=n8;
        System.out.println(isCousins(n1, 4, 5));
        System.out.println(isCousins1(n1, 4, 5));
        System.out.println(isCousins(n1, 4, 3));
        System.out.println(isCousins1(n1, 4, 3));
        System.out.println(isCousins2(n1, 4, 3));
        System.out.println();
        System.out.println(isCousins(n1, 4, 6));
        System.out.println(isCousins1(n1, 4, 6));
        System.out.println(isCousins2(n1, 4, 6));
    }

    public static void main(String[] args) {
        test1();
    }


}
