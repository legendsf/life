package com.sf.jkt.k.algorithm.algo.m1.graph;

public class ListUDG1 {
    public static class VertexNode{
        char vertex;
        EdgeNode firstEdge;
    }
    public static class EdgeNode{
        char adjvex;
        EdgeNode next;
    }
    int vlen;
    int elen;
    VertexNode[] vertexNodeList;

    public ListUDG1(char[] vexs,char[][]edges) {
        vlen=vexs.length;
        elen=edges.length;
        vertexNodeList=new  VertexNode[vlen];
        for (int i=0;i<vlen;i++){
            vertexNodeList[i]=new VertexNode();
            vertexNodeList[i].vertex=vexs[i];
            vertexNodeList[i].firstEdge=null;
        }
        for (int i=0;i<elen;i++){
            EdgeNode e1=new EdgeNode();
            EdgeNode e2=new EdgeNode();
            int vi=getPosition(edges[i][0],vexs);
            int vj=getPosition(edges[i][1],vexs);
            e1.adjvex=edges[i][1];
            e1.next=vertexNodeList[vi].firstEdge;
            vertexNodeList[vi].firstEdge=e1;

            e2.adjvex=edges[i][0];
            e2.next=vertexNodeList[vj].firstEdge;
            vertexNodeList[vj].firstEdge=e2;
        }
    }

    private int getPosition(char ch,char[] vexs){
        for (int i=0;i<vlen;i++){
            if(vexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    public void print(){
        for (int i=0;i<vlen;i++){
            System.out.print(vertexNodeList[i].vertex+"--->");
            if(vertexNodeList[i].firstEdge!=null){
                EdgeNode node=new EdgeNode();
                node=vertexNodeList[i].firstEdge;
                System.out.print(node.adjvex);
                while (node.next!=null){
                    node=node.next;
                    System.out.print(node.adjvex);
                }
                System.out.println();
            }else {
                System.out.println();
            }
        }
    }

    public static void test1(){
        char[] vexs={'A','B','C','D'};
        char[][]edges=new char[][]{
                {'A','B'},
                {'A','C'},
                {'A','D'},
                {'B','D'},
                {'C','D'}
        };
        ListUDG1 udg1=new ListUDG1(vexs,edges);
        udg1.print();
    }

    public static void main(String[] args) {
       test1();
    }
}
