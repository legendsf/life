package com.sf.jkt.k.algorithm.study.zcy.base.b34.class_03.me;

public class PrintMatrixSpiralOrder {
  public static void main(String[] args) {
    int[][] matrix = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
            { 13, 14, 15, 16 } };
    printMatrix(matrix);
  }
  public static void printMatrix(int[][] matrix){
    int tr=0;
    int tc=0;
    int dr=matrix.length-1;
    int dc=matrix[0].length-1;
    while (tr<=dr&&tc<=dc){
      printEdge(matrix,tr++,tc++,dr--,dc--);
    }
  }

  public static void printEdge(int[][]matrix,int tr,int tc,int dr,int dc){
    if(tr==dr) {
        for(int i=tc;i<=dc;i++){
          System.out.print(matrix[tr][i]+" ");
        }
    }else if(tc==dc){
      for(int i=tr;i<=tr;i++){
        System.out.print(matrix[i][tc]+" ");
      }
    }else {
      int curC=tc;
      int curR=tr;
      while (curC!=dc){
        System.out.print(matrix[tr][curC++]+" ");
      }
      while (curR !=dr){
        System.out.print(matrix[curR++][curC]+" ");
      }
      while (curC!=tc){
        System.out.print(matrix[curR][curC--]+" ");
      }
      while (curR!=tr){
        System.out.print(matrix[curR--][curC]+" ");
      }
    }
  }


}
