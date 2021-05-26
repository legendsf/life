package com.sf.jkt.k.algorithm.algo.m1.graph;

public class ListDG1 {
    int vlen;
    int elen;
    ListUDG1.VertexNode[] vertexNodeList;

    public ListDG1(char[] vexs, char[][] edges) {
        vlen = vexs.length;
        elen = edges.length;
        vertexNodeList = new ListUDG1.VertexNode[vlen];
        for (int i = 0; i < vlen; i++) {
            vertexNodeList[i] = new ListUDG1.VertexNode();
            vertexNodeList[i].vertex = vexs[i];
            vertexNodeList[i].firstEdge = null;
        }
        for (int i = 0; i < elen; i++) {
            ListUDG1.EdgeNode edgeNode = new ListUDG1.EdgeNode();
            int vi = getPosition(edges[i][0], vexs);
//            int vj=getPosition(edges[i][1],vexs);
            edgeNode.adjvex = edges[i][1];
            edgeNode.next = vertexNodeList[vi].firstEdge;
            vertexNodeList[vi].firstEdge = edgeNode;
        }
    }

    private int getPosition(char ch, char[] vexs) {
        for (int i = 0; i < vexs.length; i++) {
            if (vexs[i] == ch) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        for (int i = 0; i < vlen; i++) {
            System.out.print(vertexNodeList[i].vertex + "--->");
            if (vertexNodeList[i].firstEdge != null) {
                ListUDG1.EdgeNode tmp = vertexNodeList[i].firstEdge;
                System.out.print(tmp.adjvex);
                while (tmp.next != null) {
                    tmp = tmp.next;
                    System.out.print(tmp.adjvex);
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
        ListDG1 udg1=new ListDG1(vexs,edges);
        udg1.print();
    }

    public static void main(String[] args) {
       test1();
    }
}
