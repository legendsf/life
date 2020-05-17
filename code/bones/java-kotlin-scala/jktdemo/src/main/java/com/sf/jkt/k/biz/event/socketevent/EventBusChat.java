package com.sf.jkt.k.biz.event.socketevent;

import com.google.common.eventbus.EventBus;

import java.net.ServerSocket;
import java.net.Socket;

public class EventBusChat {
    public static void main(String[] args) throws Exception{
        EventBus channel = new EventBus();
        ServerSocket serverSocket;
        serverSocket=new ServerSocket(4444);
        while (true){
            Socket connetion = serverSocket.accept();
            UserThread newUser = new UserThread(connetion,channel);
            channel.register(newUser);//注册事件处理器
            newUser.start();
        }
    }
}
