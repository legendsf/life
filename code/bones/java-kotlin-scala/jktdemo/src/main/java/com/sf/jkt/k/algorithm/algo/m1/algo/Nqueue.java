package com.sf.jkt.k.algorithm.algo.m1.algo;

import java.util.*;

public class Nqueue {

    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];
        Arrays.fill(queens, -1);
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public static void backtrack(List<List<String>> solutions, int[] queens, int n, int row, Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {
            List<String> board = generateBoard(queens, n);
            solutions.add(board);
        } else {
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);
                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);
                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    public static List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static List<List<String>> solveNQueens1(int n) {
        List<List<String>> solutions = new ArrayList<>();
        int[] queues = new int[n];
        Arrays.fill(queues, -1);
        Set<Integer> columns = new HashSet<>();
        Set<Integer> diagonals1 = new HashSet<>();
        Set<Integer> diagonals2 = new HashSet<>();
        backtrack1(solutions, queues, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    public static void backtrack1(List<List<String>> solutions, int[] queues, int n, int row,
                                  Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if(row==n){
            List<String> board=generateBoard1(queues,n);
            solutions.add(board);
        }else {
            for(int i=0;i<n;i++){
                if(columns.contains(i)){
                    continue;
                }
                int dia1=row-i;
                if(diagonals1.contains(dia1)){
                    continue;
                }
                int dia2=row+i;
                if(diagonals2.contains(dia2)){
                    continue;//回溯
                }
                queues[row]=i;
                columns.add(i);
                diagonals1.add(dia1);
                diagonals2.add(dia2);
                backtrack(solutions,queues,n,row+1,columns,diagonals1,diagonals2);
                queues[row]=-1;
                columns.remove(i);
                diagonals1.remove(dia1);
                diagonals2.remove(dia2);
            }
        }
    }
    public static List<String> generateBoard1(int[] queues,int n){
        List<String> board=new ArrayList<>();
        for(int i=0;i<n;i++){
            char[] row=new char[n];
            Arrays.fill(row,'.');
            row[queues[i]]='Q';
            board.add(new String(row));
        }
        return board;
    }


    public static void test1() {
        System.out.println(solveNQueens(8));
        System.out.println(solveNQueens1(8));
    }

    public static void main(String[] args) {
        test1();
    }

}
