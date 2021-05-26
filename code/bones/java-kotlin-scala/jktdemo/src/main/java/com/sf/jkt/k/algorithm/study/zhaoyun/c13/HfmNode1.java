package com.sf.jkt.k.algorithm.study.zhaoyun.c13;

import org.jetbrains.annotations.NotNull;

public class HfmNode1 implements Comparable<HfmNode1>{
    public String chars;
    public int fre;
    HfmNode1 left;
    HfmNode1 right;
    HfmNode1 parent;

    @Override
    public int compareTo(@NotNull HfmNode1 o) {
        return this.fre-o.fre;
    }

}
