package com.sf.jkt.k.comp.connection.vertx;

import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.net.NetServer;
import io.vertx.ext.web.Router;

public class VertxServer {
    public static void main(String[] args) {
        testTcpServer();
    }

    public static void testTcpServer(){
        Vertx vertx = Vertx.vertx();
        NetServer netServer=vertx.createNetServer();
        netServer.connectHandler(netSocket -> {
            netSocket.handler(buffer -> {
                System.out.println("接收到数据："+buffer.toString());
                netSocket.write(Buffer.buffer("Server Received"));
            });
            netSocket.closeHandler(close->{
                System.out.println("客户端退出连接");
            });
        });
        netServer.listen(5555,res->{
            if(res.succeeded()){
                System.out.println("服务器启动成功");
            }
        });
    }

    private static void testHttpServer() {
        Vertx vertx=Vertx.vertx();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type","text/plain");
            response.end("hello world from Vertx-web");
        });
        server.requestHandler(router::accept).listen(8071);
    }
}
