package com.sf.jkt.k.algorithm.algo.m1.graph;

public class ListUDG2 {
    private static class VertexNode{
        char vertex;
        EdgeNode firstEdge;
    }
    private static class EdgeNode{
        char adjvex;
        EdgeNode next;
    }
    int vlen;
    int elen;
    VertexNode[] vertexNodeList;

    public ListUDG2(char[] vertexs,char[][]edges) {
        vlen=vertexs.length;
        elen=edges.length;
        vertexNodeList=new VertexNode[vlen];
        for (int i=0;i<vlen;i++){
           vertexNodeList[i]=new VertexNode();
           vertexNodeList[i].vertex=vertexs[i];
           vertexNodeList[i].firstEdge=null;
        }
        for (int i=0;i<elen;i++){
            int vi=getPosition(edges[i][0],vertexs);
            int vj=getPosition(edges[i][1],vertexs);
            EdgeNode edge=new EdgeNode();
            edge.adjvex=edges[i][1];
            edge.next=vertexNodeList[vi].firstEdge;
            vertexNodeList[vi].firstEdge=edge;
            EdgeNode edge1=new EdgeNode();
            edge1.adjvex=edges[i][0];
            edge1.next=vertexNodeList[vj].firstEdge;
            vertexNodeList[vj].firstEdge=edge1;
        }
    }
    private int getPosition(char ch,char[]vertexs){
        for (int i=0;i<vertexs.length;i++){
            if (vertexs[i]==ch){
                return i;
            }
        }
        return -1;
    }

    private void print(){
        System.out.println("adjlist");
        for (int i=0;i<vlen;i++){
            if(vertexNodeList[i].firstEdge!=null){
                System.out.print(vertexNodeList[i].vertex+"--->");
                EdgeNode edge= vertexNodeList[i].firstEdge;
                System.out.print(edge.adjvex);
                while (edge.next!=null){
                    edge=edge.next;
                    System.out.print(" "+edge.adjvex);
                }
            }
            System.out.println();
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
        ListUDG2 udg1=new ListUDG2(vexs,edges);
        udg1.print();
    }

    public static void main(String[] args) {
        test1();
    }
}
