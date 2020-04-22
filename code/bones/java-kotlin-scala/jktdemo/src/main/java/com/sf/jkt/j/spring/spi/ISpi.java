package com.sf.jkt.j.spring.spi;

public interface ISpi<T> {
    boolean verify(T condition);

    default int order() {
        return 10;
    }
}
