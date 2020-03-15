package com.rumwei.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {
    @Bean
//    @LoadBalanced //赋予了RestTemplate负载均衡能力 使用自定义的负载均衡逻辑，因此注释掉，加上则表示使用的ribbon已经提供好的负载均衡组件
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
