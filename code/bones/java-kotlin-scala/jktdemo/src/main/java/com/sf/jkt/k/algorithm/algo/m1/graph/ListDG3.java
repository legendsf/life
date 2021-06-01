package com.sf.jkt.k.algorithm.algo.m1.graph;

public class ListDG3 {
    int vlen;
    int elen;
    VertexNode[] vertexNodeList;
    EdgeNode edgeNode;
    private static class VertexNode{
        char vertex;
        EdgeNode firstEdge;
    }
    private static class EdgeNode{
        char adjvex;
        EdgeNode next;
    }

    public ListDG3(char[]vexs,char[][]edges){
        vlen=vexs.length;
        elen=edges.length;
        vertexNodeList=new VertexNode[vlen];
        for (int i=0;i<vlen;i++){
            vertexNodeList[i]=new VertexNode();
            vertexNodeList[i].vertex=vexs[i];
            vertexNodeList[i].firstEdge=null;
        }
        for (int i=0;i<elen;i++){
            int vi=getPosition(edges[i][0],vexs);
            int vj=getPosition(edges[i][1],vexs);
            EdgeNode edgeNode=new EdgeNode();
            edgeNode.adjvex=edges[i][1];
            edgeNode.next=vertexNodeList[vi].firstEdge;
            vertexNodeList[vi].firstEdge=edgeNode;
        }
    }

    private int getPosition(char ch, char[] vexs){
       for (int i=0;i<vlen;i++){
           if(vexs[i]==ch){
               return i;
           }
       }
       return -1;
    }

    private void print(){
        System.out.println("adjlist");
        for (int i=0;i<vlen;i++){
            System.out.print(vertexNodeList[i].vertex+"--->");
            if(vertexNodeList[i].firstEdge!=null){
                EdgeNode edge=new EdgeNode();
                edge=vertexNodeList[i].firstEdge;
                System.out.print(edge.adjvex);
                while (edge.next!=null){
                    edge=edge.next;
                    System.out.print(","+edge.adjvex);
                }
            }
            System.out.println();
        }
    }

    /**
     * 主函数
     * @param args
     */
    public static void main(String args[]) {
        // 顶点数组
        char[] vexs = {
                'A', 'B', 'C', 'D'
        };
        // 边数组
        char[][] edges = new char[][] {
                {
                        'A', 'B'
                }, {
                'A', 'C'
        }, {
                'A', 'D'
        }, {
                'B', 'D'
        }, {
                'C', 'D'
        }
        };

        ListDG3 listDG = new ListDG3(vexs, edges);
        listDG.print();
    }
}
