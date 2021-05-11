package com.sf.netflix.sc.gateway.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserHandler {
   public Mono<ServerResponse> getUser(ServerRequest request){
       final Optional<String> id = request.queryParam("id");
       if(id.isPresent()){
           User user=User.builder().id(Integer.parseInt(id.get())).name(UUID.randomUUID().toString()).build();
           return ServerResponse.ok()
                   .contentType(MediaType.APPLICATION_JSON)
                   .body(Mono.just(user),User.class);
       }
       User user=User.builder().id(0).name(UUID.randomUUID().toString()).build();
       return ServerResponse.ok()
               .contentType(MediaType.APPLICATION_JSON)
               .body(Mono.just(user),User.class);
   }
}
