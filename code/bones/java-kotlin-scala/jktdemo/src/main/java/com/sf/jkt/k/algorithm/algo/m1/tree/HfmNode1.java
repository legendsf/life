package com.sf.jkt.k.algorithm.algo.m1.tree;

import org.jetbrains.annotations.NotNull;

public class HfmNode1 implements Comparable<HfmNode1> {
    String chars;
    int fre;
    HfmNode1 left;
    HfmNode1 right;
    HfmNode1 parent;

    @Override
    public int compareTo(@NotNull HfmNode1 o) {//小的放前面
        return this.fre-o.fre;
    }
}
