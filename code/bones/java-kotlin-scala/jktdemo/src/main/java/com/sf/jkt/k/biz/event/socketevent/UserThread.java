package com.sf.jkt.k.biz.event.socketevent;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import lombok.Builder;
import lombok.Data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Data
public class UserThread extends Thread {
    Socket connection;
    EventBus channel;
    BufferedReader in;
    PrintWriter out;

    public UserThread(Socket connection,EventBus channel){
        this.connection=connection;
        this.channel=channel;
        try {
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Subscribe
    public void  receiveMessage(String message){
        if(out!=null){
            out.println(message);
            System.out.println("Receive Message: "+message);
        }
    }

    @Override
    public void run() {
        try {
            String input;
            while ((input=in.readLine())!=null){
                channel.post(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        in=null;
        out=null;
    }


}
