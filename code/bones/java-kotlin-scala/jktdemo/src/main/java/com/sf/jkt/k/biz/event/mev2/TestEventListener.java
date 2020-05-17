package com.sf.jkt.k.biz.event.mev2;

import com.google.common.eventbus.Subscribe;

public class TestEventListener {
    public int lastMessage=0;
    @Subscribe
    public void listen(TestEvent event){
        lastMessage=event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    @Subscribe
    public void listenInt(Integer i){
        lastMessage=i;
        System.out.println("Message.listenInt:"+lastMessage);
    }

    @Subscribe
    public void listenStr(String i){
        lastMessage=Integer.valueOf(i);
        System.out.println("Message.listenStr:"+lastMessage);
    }

    public int getLastMessage(){
        return lastMessage;
    }
}
