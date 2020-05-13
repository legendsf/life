package com.sf.jkt.k.biz.event;

public class TestEventListener {
    public int lastMessage=0;
    public void listen(TestEvent event){
        lastMessage=event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    public int getLastMessage(){
        return lastMessage;
    }
}
