package com.sf.jkt.k.algorithm.algo.m1.tree;

import com.sf.jkt.k.algorithm.algo.m1.base.ListNode;

import java.util.*;

public class TreeDemo1 {
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode p){
        if(p.right!=null){
            p=p.right;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==p.val){
               return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static TreeNode inorderSuccessor1(TreeNode root,TreeNode p){
        if(p==null||root==null){
            return null;
        }
        if(p.right!=null){
            p=p.right;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==p.val){//前一个结点的值等于p，那么当前结点就是后继结点
                return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static TreeNode inorderSuccessor2(TreeNode root,TreeNode p){
        if(p==null||root==null){
            return null;
        }
        if(p.right!=null){
            p=p.right;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;


        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==p.val){
                return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static TreeNode createTree(){
        TreeNode t1=new TreeNode(1);
        TreeNode t2=new TreeNode(2);
        TreeNode t3=new TreeNode(3);
        t2.left=t1;
        t2.right=t3;
        return t2;
    }

    public static TreeNode inorderSuccessor3(TreeNode root,TreeNode p){
        if(p.right!=null){
            p=p.right;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;

        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==p.val){
                return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static TreeNode inorderSuccessor4(TreeNode root,TreeNode p){
        if(p.right!=null){
            p=p.right;
            while (p.left!=null){
                p=p.left;
            }
            return p;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder= Integer.MIN_VALUE;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==p.val){
                return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static List<Integer> preTravel(TreeNode root){
        List<Integer> ans= new LinkedList<>();
        if(root==null){
            return ans;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);

//        while (!stack.isEmpty()) {
//            TreeNode node = stack.pop();
//            System.out.print(node.val + " ");
//            if (node.right != null) {
//                stack.push(node.right);
//            }
//            if (node.left != null) {
//                stack.push(node.left);
//            }
//        }

        while (!stack.isEmpty()){
            TreeNode tmp=stack.pop();
            System.out.print(tmp.val+" ");
            ans.add(tmp.val);
            if(tmp.right!=null){
                stack.push(tmp.right);
            }
            if(tmp.left!=null){
                stack.push(tmp.left);
            }
        }

        System.out.println();
        return ans;
    }

    public static List<Integer> preTravel1(TreeNode root){
        List<Integer> ans = new LinkedList<>();
        if(root==null){
            return ans;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node=stack.pop();
            System.out.print(node.val+" ");
            ans.add(node.val);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }
        System.out.println();
        return ans;

    }

    public static List<Integer> inTravel(TreeNode root){
       List<Integer> ans= new LinkedList<>();
       if(root==null){
           return ans;
       }
       ArrayDeque<TreeNode> stack=new ArrayDeque<>();
       while (!stack.isEmpty()||root!=null){
          while (root!=null){
              stack.push(root);
              root=root.left;
          }
          root=stack.pop();
          ans.add(root.val);
          System.out.print(root.val+" ");
          root=root.right;
       }
        System.out.println();
       return ans;
    }

    public static List<Integer> postTravel1(TreeNode root){
        List<Integer> ans= new LinkedList<>();
        if(root==null){
            return ans;
        }
        Stack<TreeNode> stack1=new Stack();
        Stack<TreeNode> stack2=new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            TreeNode node=stack1.pop();
            stack2.push(node);
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            TreeNode node=stack2.pop();
            System.out.print(node.val+" ");
            ans.add(node.val);
        }
        System.out.println();
        return ans;
    }


    public static List<Integer> postTravel2(TreeNode head){
       List<Integer> ans=new LinkedList<>();
       if(head==null){
           return ans;
       }
       Stack<TreeNode> stack1=new Stack<>();
       Stack<TreeNode> stack2=new Stack<>();
       stack1.push(head);
       while (!stack1.isEmpty()){
           TreeNode node=stack1.pop();
           stack2.push(node);

           if(node.left!=null){
               stack1.push(node.left);
           }
           if(node.right!=null){
               stack1.push(node.right);
           }
       }
       while (!stack2.isEmpty()){
           TreeNode tmp=stack2.pop();
           System.out.print(tmp.val+" ");
           ans.add(tmp.val);
       }
        System.out.println();
       return ans;
    }

    public static List<List<Integer>> levelTravel4(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        helper3(root,ans,0);
        ans.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return ans;
    }
    public static void helper3(TreeNode root,List<List<Integer>> ans,int level){
        if(root==null){
            return;
        }
        if(level==ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        if(root.left!=null){
            helper(root.left,ans,level+1);
        }
        if(root.right!=null){
            helper(root.right,ans,level+1);
        }
    }

    public static List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<Integer>();
            int currentLevelSize = queue.size();
            for (int i = 1; i <= currentLevelSize; ++i) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            ret.add(level);
        }

        return ret;
    }

    public static List<List<Integer>> levelTravel1(TreeNode root) {
        List<List<Integer>> result=new ArrayList();
        if(root==null){
            return result;
        }
        helper(root,result,0);
        result.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return result;
    }
    public static void helper(TreeNode root,List<List<Integer>> result,int levels){
        if(result.size()==levels){
            result.add(new ArrayList<Integer>());
        }
        result.get(levels).add(root.val);
        if(root.left!=null){
            helper(root.left,result,levels+1);
        }
        if(root.right!=null){
            helper(root.right,result,levels+1);
        }
    }

    public static List<List<Integer>> levelTravel2(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
           List<Integer>level=new LinkedList<>();
           ans.add(level);
           int currentLevleSize=queue.size();
           for(int i=0;i<currentLevleSize;i++){
               TreeNode node=queue.poll();
               System.out.print(node.val+" ");
               level.add(node.val);
               if(node.left!=null){
                   queue.add(node.left);
               }
               if(node.right!=null){
                   queue.add(node.right);
               }
           }
        }
        System.out.println();
        return ans;
    }

    public static List<List<Integer>> levelTravel3(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level=new LinkedList<>();
            ans.add(level);
            int currentLevelSize=queue.size();
            for (int i=0;i<currentLevelSize;i++){
                TreeNode node=queue.poll();
                level.add(node.val);
                System.out.print(node.val+" ");
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        System.out.println();
        return ans;
    }

    public static List<List<Integer>> levelTravel6(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        helper1(root,ans,0);
        ans.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return ans;
    }
    public static void helper1(TreeNode root,List<List<Integer>> ans,int level){
       if(root==null){
           return;
       }
       if(level==ans.size()){
           ans.add(new ArrayList<>());
       }
       ans.get(level).add(root.val);
       if(root.left!=null){
           helper(root.left,ans,level+1);
       }
       if(root.right!=null){
           helper(root.right,ans,level+1);
       }
    }

    public static List<List<Integer>> levelTravel5(TreeNode root){
        List<List<Integer>> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        helper2(root,ans,0);
        ans.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return ans;
    }
    public static void helper2(TreeNode root,List<List<Integer>> ans,int level){
        if(root==null){
            return;
        }
        if(level==ans.size()){
            ans.add(new ArrayList<>());
        }
        ans.get(level).add(root.val);
        if(root.left!=null){
           helper(root.left,ans,level+1);
        }
        if(root.right!=null){
            helper(root.right,ans,level+1);
        }
    }
    public static List<Integer> morrisPre(TreeNode root){
       List<Integer> ans=new ArrayList<>();
       if(root==null){
           return ans;
       }
       TreeNode cur1=root;
       TreeNode cur2=null;
       while (cur1!=null){
           cur2=cur1.left;
           if(cur2!=null){
              while (cur2.right!=null&&cur2.right!=cur1){
                  cur2=cur2.right;
              }
              if(cur2.right==null){
                 cur2.right=cur1;
                 ans.add(cur1.val);
                  System.out.print(cur1.val+" ");
                 cur1=cur1.left;
                 continue;
              }else {
                  cur2.right=null;
              }
           }else {
               ans.add(cur1.val);
               System.out.print(cur1.val+" ");
           }
           cur1=cur1.right;
       }
        System.out.println();
       return ans;
    }

    public static List<Integer> morrisPre1(TreeNode root){
        List<Integer> ans= new ArrayList<>();
        if(root==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  ans.add(cur.val);
                   System.out.print(cur.val+" ");
                  cur=cur.left;
                  continue;
               }else {
                 mostRight.right=null;
               }
            }else {
                ans.add(cur.val);
                System.out.print(cur.val+" ");
            }
            cur=cur.right;
        }
        System.out.println();
        return ans;
    }


    public static List<Integer> morrisPre2(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  ans.add(cur.val);
                   System.out.print(cur.val+" ");
                  cur=cur.left;
                  continue;
               }else {
                  mostRight.right=null;
               }
            }else {
               ans.add(cur.val);
                System.out.print(cur.val+" ");
            }
            cur=cur.right;
        }
        System.out.println();
        return ans;
    }


    public static List<Integer> morrisIn(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                  mostRight.right=null;
               }
            }
            ans.add(cur.val);
            System.out.print(cur.val+" ");
            cur=cur.right;
        }
        System.out.println();
        return ans;
    }

    public static List<Integer> morrisIn1(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                  mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
               }
            }
            ans.add(cur.val);
            System.out.print(cur.val+" ");
            cur=cur.right;
        }
        System.out.println();
        return ans;
    }

    public static List<Integer> morrisIn2(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        if(ans==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
               }
               ans.add(cur.val);
                System.out.print(cur.val);
               cur=cur.right;
            }
        }
        return ans;
    }

    public static TreeNode inorderSuccessor5(TreeNode root,TreeNode node){
        if(node==null){
            return null;
        }
        if(node.right!=null){
            node=node.right;
            while (node.left!=null){
                node=node.left;
            }
            return node;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;
        while (!stack.isEmpty() ||root!=null ){
           while (root!=null){
               stack.push(root);
               root=root.left;
           }
           root=stack.pop();
           if(inorder==node.val){
               return root;
           }
           inorder=root.val;
           root=root.right;
        }
        return null;
    }

    public static TreeNode inorderSuccessor6(TreeNode root,TreeNode node){
        if(node==null){
            return null;
        }
        if(node.right!=null){
            node=node.right;
            while (node.left!=null){
                node=node.left;
            }
            return node;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        int inorder=Integer.MIN_VALUE;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
            if(inorder==node.val){
                return root;
            }
            inorder=root.val;
            root=root.right;
        }
        return null;
    }

    public static List<Integer> postTravel4(TreeNode root){
        List<Integer> ans=new ArrayList<>();
        if(root==null){
            return ans;
        }
        Stack<TreeNode> stack1=new Stack<>();
        stack1.push(root);
        Stack<TreeNode> stack2=new Stack<>();
        while (!stack1.isEmpty()){
            TreeNode node=stack1.pop();
            stack2.push(node);
            if(node.left!=null){
                stack1.push(node.left);
            }
            if(node.right!=null){
                stack1.push(node.right);
            }
        }
        while (!stack2.isEmpty()){
            TreeNode node=stack2.pop();
            ans.add(node.val);
            System.out.print(node.val+" ");
        }
        System.out.println();
        return ans;
    }

    public static List<List<Integer>> levelTravel7(TreeNode root){
        List<List<Integer>> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        Queue<TreeNode> queue=new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> level=new ArrayList<>();
            ans.add(level);
            int currentLevelSize=queue.size();
            for (int i=0;i<currentLevelSize;i++){
                TreeNode node=queue.poll();
                level.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
            }
        }
        ans.forEach(x->x.forEach(y->System.out.print(y+" ")));
        System.out.println();
        return ans;
    }
    public static List<Integer> morrisPost(TreeNode root){
        List<Integer> ans=new LinkedList<>();
        if(root==null){
            return ans;
        }
        TreeNode cur=root;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight=mostRight.right;
                }
                if(mostRight.right==null){
                   mostRight.right=cur;
                   cur  =cur.left;
                   continue;
                }else {
                    mostRight.right=null;
                    printEdge(cur.left,ans);
//                    printEdge(cur.left);
                }
            }
            cur=cur.right;
        }
        printEdge(root,ans);
//        printEdge(root);
        System.out.println();
        return ans;
    }
    public static void printEdge(TreeNode node,List<Integer> ans){
        TreeNode tail=reverseEdge(node);
        TreeNode cur=tail;
        while (cur!=null){
            ans.add(cur.val);
            System.out.print(cur.val+" ");
            cur=cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverse(TreeNode node){
        if(node==null){
            return node;
        }
        TreeNode prev=null;
        TreeNode cur=node;
        while (cur!=null){
            TreeNode next=cur.right;
            node.right=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }




    public static void morrisPos(TreeNode head) {
        if (head == null) {
            return;
        }
        TreeNode cur1 = head;
        TreeNode cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                    printEdge(cur1.left);
                }
            }
            cur1 = cur1.right;
        }
        printEdge(head);
        System.out.println();
    }

    public static void printEdge(TreeNode head) {
        TreeNode tail = reverseEdge(head);
        TreeNode cur = tail;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.right;
        }
        reverseEdge(tail);
    }

    public static TreeNode reverseEdge(TreeNode from) {
        TreeNode pre = null;
        TreeNode next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }

    public static List<Integer> morrisPost1(TreeNode node){
        List<Integer> ans=new ArrayList<>();
        if(node==null){
            return ans;
        }
        TreeNode cur=node;
        TreeNode mostRight=null;
        while (cur!=null){
            mostRight=cur.left;
            if(mostRight!=null){
               while (mostRight.right!=null&&mostRight.right!=cur){
                   mostRight=mostRight.right;
               }
               if(mostRight.right==null){
                  mostRight.right=cur;
//                   System.out.print(cur.val+" ");//先序
                  cur=cur.left;
                  continue;
               }else {
                   mostRight.right=null;
                   printEdge1(cur.left,ans);
               }
            }else {
//                System.out.print(cur.val);//先序遍历
            }
//            System.out.print(cur.val+" ");//中序
            cur=cur.right;
        }
        printEdge1(node,ans);
        System.out.println();
        return ans;
    }

    public static void printEdge1(TreeNode node,List<Integer> ans){
       TreeNode tail=reverseEdge1(node);
       TreeNode cur=tail;
       while (cur!=null){
           ans.add(cur.val);
           System.out.print(cur.val+" ");
           cur=cur.right;
       }
       reverseEdge1(tail);
    }
    public static TreeNode reverseEdge1(TreeNode node){
        if(node==null||node.right==null){
            return node;
        }
        TreeNode prev=null;
        TreeNode cur=node;
        TreeNode tmp=null;
        while (cur!=null){
            tmp=cur.right;
            cur.right=prev;
            prev=cur;
            cur=tmp;
        }
        return prev;
    }


    public static void printEdge2(TreeNode node){
        if(node==null){
            return;
        }
        TreeNode tail=reverseEdge2(node);
        TreeNode cur=tail;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.right;
        }
        System.out.println();
        reverseEdge2(tail);
    }

    public static TreeNode reverseEdge2(TreeNode node){
        if(node==null||node.right==null){
            return null;
        }
        TreeNode prev=null;
        TreeNode cur=node;
        TreeNode tmp=null;
        while (cur!=null){
            tmp=cur.right;
            cur.right=prev;
            prev=cur;
            cur=tmp;
        }
        return prev;
    }

    public static void printEdge3(TreeNode node){
        if(node==null){
            return;
        }
        TreeNode tail=reverseEdge2(node);
        TreeNode cur=tail;
        while (cur!=null){
            System.out.print(cur.val+" ");
            cur=cur.right;
        }
        System.out.println();
        reverseEdge2(tail);
    }


    public static ListNode reverseNode(ListNode node){
        ListNode prev=null;
        ListNode cur=node;
        while (cur!=null){
            ListNode next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }

    public static ListNode reverse(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        ListNode p= reverse(head.next);
        head.next.next=head;
        head.next=null;
        return p;
    }


    public static ListNode reverse1(ListNode node){
        if(node==null||node.next==null){
            return node;
        }
        ListNode p=reverse1(node.next);
        node.next.next=node;
        node.next=null;
        return p;
    }
    public static void printListNode(ListNode node){
        if(node==null){
            return;
        }
        while (node!=null){
            System.out.print(node.val+" ");
            node=node.next;
        }
        System.out.println();
    }

    public static ListNode reverse2(ListNode node){
        if(node==null||node.next==null){
            return node;
        }
        ListNode prev=null;
        ListNode cur=node;
        ListNode tmp=null;
        while (cur!=null){
            tmp =cur.next;
            cur.next=prev;
            prev=cur;
            cur =tmp;
        }
        return prev;
    }

    public static ListNode reverse3(ListNode node){
       if(node==null||node.next==null){
           return node;
       }
       ListNode p=reverse3(node.next);
       node.next.next=node;
       node.next=null;
       return p;
    }

    public static TreeNode inorderSuccessor7(TreeNode root,TreeNode node){
      if(node==null){
          return null;
      }
      if(node.right!=null){
          node=node.right;
          while (node.left!=null){
              node=node.left;
          }
          return node;
      }
      ArrayDeque<TreeNode> stack=new ArrayDeque<>();
      int inorder=Integer.MIN_VALUE;
      while (!stack.isEmpty()||root!=null){
          while (root!=null){
             stack.push(root);
             root=root.left;
          }
          root=stack.pop();
          if(inorder==node.val){
              return root;
          }
          inorder=root.val;
          root=root.right;
      }
      return null;
    }

    public static TreeNode inorderPoinner(TreeNode root,TreeNode node){
        if(node==null){
            return null;
        }
        if(node.left!=null){
            node=node.left;
            while (node.right!=null){
                node=node.right;
            }
            return node;
        }
        if(root==null){
            return null;
        }
        ArrayDeque<TreeNode> stack=new ArrayDeque<>();
        TreeNode prev=null;
        while (!stack.isEmpty()||root!=null){
            while (root!=null){
                stack.push(root);
                root=root.left;
            }
            root=stack.pop();
//            //前一个值为node.val,那么当前结点就是后继
//            if(prev!=null&&prev.val==node.val){
//                return root;
//            }
            //当前值为node.val,prev 就是前驱
            if (prev!=null&&root.val== node.val){
                return prev;
            }
            prev=root;

            root=root.right;
        }
        return null;
    }

    public static TreeNode getParent(TreeNode root,TreeNode node){
        if(root==null||node==null){
            return null;
        }
        if(root.left==node||root.right==node){
            return root;
        }
       TreeNode left= getParent(root.left,node);
        TreeNode right=getParent(root.right,node);
        return left!=null?left:right;
    }

    public static TreeNode getBrother(TreeNode root,TreeNode node){
        TreeNode parent=getParent(root,node);
        if (parent==null){
            return null;
        }
        return parent.left==node?parent.right:parent.left;
    }

    public static void test1(){
        TreeNode root=createTree();
        System.out.println(inorderSuccessor3(root, root.left));
        System.out.println(inorderSuccessor4(root, root.left));
        System.out.println(inorderSuccessor5(root, root.left));
        System.out.println(inorderSuccessor6(root, root.left));
        postTravel1(root);
        postTravel4(root);
        levelTravel1(root);
        levelTravel6(root);
        levelTravel7(root);
        System.out.println("----");
        postTravel1(root);
        morrisPos(root);
        morrisPost(root);
        System.out.println("------");
        morrisPost1(root);
        printEdge2(root);
        printEdge3(root);
        System.out.println(inorderSuccessor7(root, root.left));
    }

    public static void test2(){
        TreeNode root=createTree();
        preTravel(root);
        inTravel(root);
        preTravel1(root);
        postTravel2(root);
        System.out.println("----");
        levelTravel1(root);
        levelTravel2(root);
        levelTravel3(root);
        levelTravel4(root);
        levelTravel5(root);
        System.out.println("-----");
    }
    public static void test3(){
        TreeNode root=createTree();
        preTravel(root);
        morrisPre(root);
        morrisPre1(root);
        morrisPre2(root);

        System.out.println("----");
        inTravel(root);
        morrisIn(root);
        morrisIn1(root);
        morrisIn2(root);
    }
    public static void test4(){
        TreeNode root=createTree();
        TreeNode tail=  reverseEdge(root);
        root=createTree();
        tail=    reverse(root);
        System.out.println("");
        ListNode listNode=createListNode();
        ListNode listNode1=reverseNode(listNode);
        System.out.println("");
        printListNode(listNode1);
        listNode=createListNode();
        listNode1= reverse(listNode);
        printListNode(listNode1);
        listNode=createListNode();
        printListNode(reverse2( listNode));
        listNode=createListNode();
        printListNode(reverse3( listNode));
    }

    public static void test5(){
        TreeNode treeNode=createTree();
        System.out.println(inorderSuccessor(treeNode, treeNode));
        System.out.println(inorderPoinner(treeNode, treeNode));
        System.out.println(inorderPoinner(treeNode, treeNode.right));
    }

    public static ListNode createListNode(){
        ListNode n1=new ListNode(1);
        ListNode n2=new ListNode(2);
        ListNode n3=new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        return n1;
    }

    public static void test6(){
        TreeNode root=createTree();
        System.out.println(getParent(root, root));
        System.out.println(getParent(root, root.left));
        System.out.println(getBrother(root, root.left));
    }

    public static void main(String[] args) {
        test6();
    }
}
