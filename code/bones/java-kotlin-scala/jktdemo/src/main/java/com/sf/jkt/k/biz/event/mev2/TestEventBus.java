package com.sf.jkt.k.biz.event.mev2;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * 流程：
 *  1、建立 eventBus
 *  2、注册事件监听器
 *  3、事件监听器要有 @Subscribe 注解
 *  4、eventBus 推送 event
 */
public class TestEventBus {

    public static void testReceiveEvent()throws Exception{
        EventBus eventBus = new EventBus();
        TestEventListener el = new TestEventListener();
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);
        eventBus.register(el);//注册事件
        //int long 等基本类型会触发 DeadEvent
        eventBus.post(new Integer(1));//listenInt
        eventBus.post("2");//listenStr
//        eventBus.post(new Object());//DeadEvent
        eventBus.post(new TestEvent(45));
        System.out.println(el.getLastMessage());
        System.out.println(deadEventListener.notDelivered);
    }

    public static void main(String[] args) throws  Exception{
       testReceiveEvent();
        System.out.println();
    }

}

class DeadEventListener{
    boolean notDelivered = false;
    @Subscribe
    public void listen(DeadEvent deadEvent){
        notDelivered=true;
        System.out.println("deadEvent:"+deadEvent.getEvent());
        System.out.println("deadEventSource:"+deadEvent.getSource());
    }
}