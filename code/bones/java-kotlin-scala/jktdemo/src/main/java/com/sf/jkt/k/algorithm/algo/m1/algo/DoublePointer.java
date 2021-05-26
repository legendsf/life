package com.sf.jkt.k.algorithm.algo.m1.algo;

import com.sf.jkt.k.algorithm.algo.m1.base.ListNode;
import com.sf.jkt.k.algorithm.algo.m1.tree.TreeNode;

import java.util.*;

public class DoublePointer {
    public static int binarySearch(int[] arr,int num){
        if(arr==null||arr.length<1){
            return -1;
        }
        Arrays.sort(arr);
       return doBinarySearch(arr,0,arr.length-1,num);
    }
    public static int doBinarySearch(int[]arr,int l,int r,int num){
        int mid= l+(r-l)/2;
        if(arr[mid]==num){
            return mid;
        }else if(num<arr[mid]){
            return doBinarySearch(arr,l,mid-1,num);
        }else {
            return doBinarySearch(arr,mid+1,r,num);
        }
    }

    public static int binarySearch1(int[]arr,int target){
        if(arr==null||arr.length<1){
            return -1;
        }
        int pivot=0,l=0,r=arr.length-1;
        while (l<=r){
            pivot= l+(r-l)/2;
            if(arr[pivot]==target){
                return pivot;
            }else if(target<arr[pivot]){
               r=pivot-1;
            }else {
                l=pivot+1;
            }
        }
        return -1;
    }


    public static ListNode reverse(ListNode node){
        if(node==null){
            return node;
        }
        ListNode prev=null,cur=node;
        while (cur!=null){
            ListNode next=cur.next;
            cur.next=prev;
            prev=cur;
            cur=next;
        }
        return prev;
    }

    public static int countSubStrings(String str){//huiwen
        if(str==null||str.length()<1){
            return 0;
        }
        int n=str.length(),ans=0;
        List<String> subList=new ArrayList<>();
        for(int i=0;i<2*n-1;i++){
            int l=i/2,r=l+i%2;
            while ( l>=0 && r<n && str.charAt(l)==str.charAt(r)){
                subList.add(str.substring(l,r+1));
                ans++;
                l--;
                r++;
            }
        }
        subList.stream().forEachOrdered(x->System.out.print(x+" "));
        System.out.println("ans:"+ans);
        return ans;
    }

    public static void reverse(char[] s){
        if(s==null||s.length<1){
            return ;
        }
        for(int i=0,j=s.length-1;i<s.length/2;i++,j--){
            s[i] ^= s[j];
            s[j] ^= s[i];
            s[i] ^= s[j];
        }
    }

    public static void test1(){
        countSubStrings("aba");
        char[] s=new char[]{'a','b','c'};
        reverse(s);
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i]+" ");
        }
    }

    public static ListNode detectCycle(ListNode node){
        ListNode fast=node,slow=node;
        while (true){
            if(fast==null||fast.next==null){
                return null;
            }
            fast=fast.next.next;
            slow=slow.next;
            if(fast==slow){
                break;
            }
        }
        fast=node;
        while (fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }

    public static int[] twoNums(int[] arr,int target){
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]+arr[j]==target){
                    return new int[]{i+1,j+1};
                }
            }
        }
        return new int[0];
    }

    public static int[] twoNums1(int[] arr,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<arr.length;i++){
            if(map.containsKey(target-arr[i])){
                return new int[]{map.get(target-arr[i])+1,i+1};
            }
            map.put(arr[i],i);
        }
        return new int[0];
    }

    public static int binarySearch3(int[]arr,int target){
        if(arr==null||arr.length<1){
            return -1;
        }
        int l=0,r=arr.length-1,pivot=0;
        while (l<=r){
            pivot=l+(r-l)/2;
            if(arr[pivot]==target){
                return pivot;
            }else if(arr[pivot]>target) {
                r=pivot-1;
            }else {
                l=pivot+1;
            }
        }
        return -1;
    }

    public static int[] twoSum2(int[] arr,int target){
        for(int i=0;i<arr.length;i++){
            int low=i+1,high=arr.length-1;
            while (low<=high){
                int mid= low+(high-low)/2;
                if(arr[mid]==target-arr[i]){
                    return new int[]{i+1,mid+1};
                }else if(arr[mid]>target-arr[i]) {
                    high=mid-1;
                }else {
                    low=mid+1;
                }
            }
        }
        return new int[]{-1,-1};
    }

    public static int[] twoSum3(int[] nums,int target){
        int low=0,high=nums.length-1;
        while (low<high){
            int sum=nums[low]+nums[high];
            if(sum==target){
               return new int[]{low+1,high+1};
            }else if(sum<target){
                low++;
            }else {
                high--;
            }
        }
        return new int[]{-1,-1};
    }

    public static boolean find(TreeNode root, int target, Set set){
        if(root==null){
            return false;
        }
        if(set.contains(target-root.val)){
            return true;
        }
        set.add(root.val);
        return find(root.left,target,set)||find(root.right,target,set);
    }

    public static boolean find(TreeNode root, int target){
        if(root==null){
            return false;
        }
        Set<Integer> set=new HashSet<>();
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            if (queue.peek()!=null){
                TreeNode node=queue.remove();
                if(set.contains(target-node.val)){
                    return true;
                }
                set.add(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }else {
                queue.remove();
            }
        }
        return false;
    }

    public static boolean find2(TreeNode root, int target){
        if(root==null){
            return false;
        }
        List<Integer> list=new LinkedList<>();
        inOrder(root,list);
        int low=0,hi = list.size()-1;
        while (low<hi){
            int sum=list.get(low)+list.get(hi);
            if(sum==target){
                return true;
            }else if(sum<target){
                low++;
            }else {
                hi--;
            }
        }
        return false;
    }


    public static void inOrder(TreeNode node, List<Integer> list){
        if(node==null){
            return;
        }
        inOrder(node.left,list);
        list.add(node.val);
        inOrder(node.right,list);

    }

    public static List<List<Integer>> threeSum(int[] nums){
        if(nums==null||nums.length<3){
            return new ArrayList<>();
        }
        Set<List<Integer>> set=new HashSet<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++){
            int target = -nums[i];
            int left=i+1;
            int right=nums.length-1;
            while (left<right){
                int sum=nums[left]+nums[right];
                if(sum==target){
                    set.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    left++;
                    right--;
                }else if(sum<target){
                    left++;
                }else {
                    right--;
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static List<List<Integer>> threeSum1(int[]nums){
        if(nums==null||nums.length<3){
            return  new ArrayList<>();
        }
        List<List<Integer>> res=new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-2;i++){
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            int target= - nums[i];
            int left=i+1;
            int right=nums.length-1;
            while (left<right){
                int sum=nums[left]+nums[right];
                if(sum==target){
                    res.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left<right && nums[left]==nums[++left]){};
                    while (left<right && nums[right]==nums[--right]){};
                }else if(sum<target){
                    left++;
                }else {
                   right--;
                }
            }

        }
        return res;
    }

    public static List<List<Integer>> threeSum2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums); // O(nlogn)

        // O(n^2)
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {continue;}
            // 在 i + 1 ... nums.length - 1 中查找相加等于 -nums[i] 的两个数
            int target = -nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 去重
                    while (left < right && nums[left] == nums[++left]){};
                    while (left < right && nums[right] == nums[--right]){};
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return res;
    }

    public static List<List<Integer>> fourSum(int[] nums,int target){
        if(nums==null||nums.length<4){
            return new ArrayList<>();
        }
        List<List<Integer>> res= new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0;i<nums.length-3;i++){
            if(i>0 && nums[i]==nums[i-1]){
                continue;
            }
            for(int j=i+1;j<nums.length-2;j++){
                if(j>i+1&& nums[j]==nums[j-1]){
                    continue;
                }
                int partSum=nums[i]+nums[j];
                int left=j+1;
                int right=nums.length-1;
                while (left<right){
                    int sum=partSum+nums[left]+nums[right];
                    if(sum==target){
                        res.add(Arrays.asList(nums[i],nums[j],nums[left],nums[right]));
                        while (left<right && nums[left]==nums[++left]){};
                        while (left<right && nums[right]==nums[--right]){};
                    }else if(sum<target){
                        left++;
                    }else {
                        right--;
                    }
                }
            }
        }
        return res;
    }

    public static void swapOdd(int[] nums){
        if(nums==null||nums.length<2){
            return;
        }
        int m=0,n=1,len=nums.length;
        while (m<len && n<len){
            if(nums[n-1]%2!=0){
                swap(nums,n,len-1);
                n += 2;
            }else {
                swap(nums,m,len-1);
                m += 2;
            }
        }
    }
    public static void swap(int[] arr,int i,int j){
        if(i!=j){
            arr[i] ^=arr[j];
            arr[j] ^=arr[i];
            arr[i] ^=arr[j];
        }
    }

    public static int[] singleNums(int[]nums){
        if(nums==null|| nums.length<2){
            return new int[0];
        }
        int ret=0;
        for(int n:nums){
            ret ^= n;
        }
        int div=1;
        while ((div&ret)==0){
            div <<= 1;
        }
        int a=0,b=0;
        for(int n:nums){
            if((div&n)!=0){
                a ^=n;
            }else {
                b ^=n;
            }
        }
        return new  int[]{a,b};
    }

    public static int singleNumbers1(int[] nums){
        int ans=0;
        for(int i=0;i<32;i++){
            int total=0;
            for(int n:nums){
                 total += ((n>>i)&1);
            }
            if (total%3!=0){
                ans |= (1<<i);
            }
        }
        return ans;
    }

    public static void test3(){
        int[] nums=new int[]{-7,-4,0,0,3,4};
        threeSum2(nums).stream().forEachOrdered(x->{
            x.forEach(y->System.out.print(y+" "));
            System.out.println();
        });
        System.out.println();
        threeSum(nums).stream().forEachOrdered(x->{
            x.forEach(y->System.out.print(y+" "));
            System.out.println();
        });
    }
    /*
        数组上的操作就是遍历交换和扩容
        链表的操作就是断开重连，及遍历
     */
    public static void test4(){
        int[] arr=new int[]{1,3,4,7,8};
        swapOdd(arr);
        Arrays.stream(arr).forEachOrdered(x->System.out.print(x+" "));
        System.out.println();
        arr=new int[]{3,3,1,1,2};
        System.out.println(findOdd(arr));
    }
    public static int findOdd(int[] nums){
        if(nums==null||nums.length<1){
            return -1;
        }
        int res=0;
        for (int i = 0; i < nums.length; i++) {
            res ^=nums[i];
        }
        return  res ;
    }
    public static void main(String[] args) {
        test4();
    }
}
