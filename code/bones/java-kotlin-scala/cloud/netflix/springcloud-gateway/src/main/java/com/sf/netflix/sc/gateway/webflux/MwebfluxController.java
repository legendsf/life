package com.sf.netflix.sc.gateway.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MwebfluxController {
    @GetMapping("/index")
    public Mono<String> index(){
        return Mono.just("well done!");
    }
}
