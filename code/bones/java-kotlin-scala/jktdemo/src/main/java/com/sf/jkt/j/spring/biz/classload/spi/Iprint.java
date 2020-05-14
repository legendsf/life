package com.sf.jkt.j.spring.biz.classload.spi;

public interface Iprint extends ISpi<Integer> {
    default void execute(Integer level, Object... msg) {
        print(msg.length > 0 ? (String) msg[0] : null);
    }

    void print(String msg);
}
