package com.sf.jkt.k.biz.event;

public class TestEvent {
    private  int message=0;

    public TestEvent(int message) {
        this.message=message;
    }

    public int getMessage() {
        return message;
    }
}
