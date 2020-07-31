package com.sf.jkt.k.comp.connection.http.webclient;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class WebClientDemo {
    public static void main(String[] args) {
//        testWebClient1();
        testWebClient();
    }

    public static void testWebClient1(){
        WebClient client = WebClient.create("http://localhost:8002");
        Mono<String> jsonResult = client.get().uri("/restTest").retrieve().bodyToMono(String.class);
        System.out.println(jsonResult.block());
    }

    public static void testWebClient(){
        WebClient webClient= WebClient.create();
        Mono<String> mono=webClient.get().uri("http://localhost:8002/restTest").retrieve().bodyToMono(String.class);
        System.out.println(mono.block());
    }
}
