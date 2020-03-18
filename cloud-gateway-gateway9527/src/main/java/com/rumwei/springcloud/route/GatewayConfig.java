/*
package com.rumwei.springcloud.route;

//该类配置的效果等同于application.yml文件中的配置。一种是yml配置方式，一种是硬编码方式。同时存在时以硬编码方式为准

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    @Bean
    RouteLocator routes(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_routh1",r -> r.path("/payment/get/**").uri("http://localhost:8002/payment/get/**")).build();
        routes.route("payment_routh2",r -> r.path("/payment/lb/**").uri("http://localhost:8002/payment/lb/**")).build();
        return routes.build();
    }

    @Bean
    RouteLocator routes2(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        routes.route("payment_routh3",r -> r.path("/payment/blabla/**").uri("http://localhost:8002/payment/blabla/**")).build();
        return routes.build();
    }
}
*/
