package com.sf.jkt.k.comp.connection.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;
import io.vertx.core.parsetools.RecordParser;

/***
 *
 * https://github.com/netty/netty/issues/7319
 */
public class VertxClient {
    public static void main(String[] args) {
        testVertxClient();
    }

    public static void testVertxClient(){
        Vertx vertx=Vertx.vertx();
        NetClient netClient=vertx.createNetClient();
        netClient.connect(5555,"localhost",conn->{
            if(conn.succeeded()){
                NetSocket s = conn.result();
                s.write(Buffer.buffer("hello"));
                s.handler(buffer -> {
                    System.out.println(buffer.toString());
                });
            }else {
                System.out.println("连接服务器异常");
            }
        });

    }

    public static void parser(){

        final RecordParser parser = RecordParser.newDelimited("\n", h -> {
            System.out.println(h.toString());
        });

        parser.handle(Buffer.buffer("HELLO\nHOW ARE Y"));
        parser.handle(Buffer.buffer("OU?\nI AM"));
        parser.handle(Buffer.buffer("DOING OK"));
        parser.handle(Buffer.buffer("\n"));
    }
}
