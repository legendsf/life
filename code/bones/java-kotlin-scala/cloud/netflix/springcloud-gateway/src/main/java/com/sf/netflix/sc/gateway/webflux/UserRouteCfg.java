package com.sf.netflix.sc.gateway.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class UserRouteCfg {
//    @Bean
    public  RouterFunction<ServerResponse> userRouteFuctions(UserHandler handler){
//        RouterFunctions.route().Get("");
        return  null;
    }
}
