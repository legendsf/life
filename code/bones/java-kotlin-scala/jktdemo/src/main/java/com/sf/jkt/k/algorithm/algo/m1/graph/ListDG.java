/*
 * 邻接表有向图（已提供参数）
 * 输入说明：vexs  --  顶点数组
 * 		   edges  --  边数组
 * 输出说明：邻接表（即对每个顶点vi都建立一个链接为vi为弧尾的表）
 */
package com.sf.jkt.k.algorithm.algo.m1.graph;
public class ListDG {
    int vlen; // 顶点个数
    int elen; // 边个数
    VertexNode[] vertexNodeList; // 顶点数组
    EdgeNode edgeNode;
 
    /**
     * 构造函数
     * @param vexs
     * @param edges
     */
    public ListDG(char[] vexs, char[][] edges) {
        vlen = vexs.length;
        elen = edges.length;
 
        // 初始化顶点,建立顶点表
        vertexNodeList = new VertexNode[vlen];
        for (int i = 0; i < vlen; i++) {
            vertexNodeList[i] = new VertexNode();
            vertexNodeList[i].vertex = vexs[i];
            vertexNodeList[i].firstedge = null;
        }
 
        // 初始化边，利用头插法建立邻接表
        for (int i = 0; i < elen; i++) {
            EdgeNode edgeNode = new EdgeNode();
            int vi = getPosition(edges[i][0], vexs);
            int vj = getPosition(edges[i][1], vexs);
             
            edgeNode.adjvex = edges[i][1];
            edgeNode.next = vertexNodeList[vi].firstedge;
            vertexNodeList[vi].firstedge = edgeNode;
        }
    }
 
    /**
     *  顶点表结点
     */
    private class VertexNode {
        char vertex; // 顶点域，存储顶点信息
        EdgeNode firstedge; // 边表头指针
    }
 
    /**
     *  边表结点
     */
    private class EdgeNode {
        char adjvex; // 邻接点域，存储该顶点对应的下标
        EdgeNode next; // 链域，指向下一个邻接点
    }
 
    /**
     *  返回ch位置
     */
    private int getPosition(char ch, char[] vexs) {
        for (int i = 0; i < vlen; i++)
            if (vexs[i] == ch)
                return i;
        return -1;
    }
 
    /**
     *  打印邻接表
     */
    public void print() {
        System.out.printf("AdjList:\n");
        for (int i = 0; i < vlen; i++) {
            System.out.print(vertexNodeList[i].vertex + "-->");
            if (vertexNodeList[i].firstedge != null) {
                EdgeNode mEdgeNode = new EdgeNode();
                mEdgeNode = vertexNodeList[i].firstedge;
                System.out.print(mEdgeNode.adjvex);
                while (mEdgeNode.next != null) {
                    mEdgeNode = mEdgeNode.next;
                    System.out.print(mEdgeNode.adjvex);
                }
                System.out.print("\n");
            } else {
                System.out.print("\n");
            }
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
 
        ListDG listDG = new ListDG(vexs, edges);
        listDG.print();
    }
}