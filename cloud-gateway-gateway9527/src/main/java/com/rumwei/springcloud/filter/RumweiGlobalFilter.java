/*package com.rumwei.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

//自定义全局过滤器,一般用来做全局日志记录，统一网关鉴权等.因为是全局过滤器，因此不需要其他地方去引用，Spring会自动加载
@Component
@Slf4j
public class RumweiGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.err.println("["+ LocalDateTime.now() +"]*****entered RumweiGlobalFilter...*****");
        String username = exchange.getRequest().getQueryParams().getFirst("username"); //取出请求中的username
        if (username == null){
            System.err.println("用户名为null，非法用户");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete(); //直接退出
        }
        return chain.filter(exchange); //表示该Filter已经执行完，进入下一个Filter
    }
    //加载该过滤器的顺序，越小优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
*/
