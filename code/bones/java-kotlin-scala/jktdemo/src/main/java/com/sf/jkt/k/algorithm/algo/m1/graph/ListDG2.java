package com.sf.jkt.k.algorithm.algo.m1.graph;

/**
 * https://www.cnblogs.com/codingmengmeng/p/5645073.html
 *
 */
public class ListDG2 {
    int n,m,i;
    char[]u,v,w;
    int[] first,next;

    public ListDG2(char[]vexs,char[][]edges) {
        n=vexs.length;
        m=edges.length;
        u=new char[m];
        v=new char[m];
        w=new char[m];
        first=new int[m];
        next=new int[m];
        for (int i=0;i<m;i++){
            for (int j=0;j<edges[i].length;j++){
                u[i]=edges[i][0];
                v[i]=edges[i][1];
                w[i]=edges[i][2];
//                next[i]=first[i];
//                first[i]=i;
            }
        }
        System.out.printf("end");
    }

    public void print0(){
       int k=first[0];
       while (k!=-1){
           System.out.println(u[k]+" "+v[k]+" "+w[k]+" ");
           k=next[k];
       }
    }

    public void printall(){
        for (int i=0;i<n;i++){
           int k=first[i];
           while (k!=-1){

               System.out.println(u[k]+" "+v[k]+" "+w[k]+" ");
               k=next[k];
           }
            System.out.println("--------------");
        }
    }

    public static void test1(){
        char[] vexs={'1','2','3','4'};
        char[][]edges=new char[][]{
                {'1','4','9'},
                {'4','3','8'},
                {'1','2','5'},
                {'2','4','6'},
                {'1','3','7'}
        };
        ListDG2 udg1=new ListDG2(vexs,edges);
        udg1.print0();
        System.out.println("*******");
        udg1.printall();
    }

    public static void main(String[] args) {
        test1();
    }

}
