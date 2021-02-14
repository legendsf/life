package com.sf.netflix.sc.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * 自定义 routelocator
 * https://www.jdon.com/51642
 */
@Configuration
public class GatewayConfig {
    /*
     * 第二种代理的方法
     * 只需要访问 http://localhost:9527/guonei  就能访问到uri中的网址
     * */
    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("path var",
                r ->r.path("/guonei")
                        .uri("http://news.baidu.com/guonei"))
                .route(p->p.path("/mybaidu/**")
                        .uri("https://www.baidu.com/s?ie=utf-8&wd=mybaidu"))
                .route(p ->
                        p.path("/api/msg/user/hello")
                                .filters(f -> f.stripPrefix(2))
                                .uri("http://localhost:9090/")
                )
                .build();
        return routes.build();
    }
}
