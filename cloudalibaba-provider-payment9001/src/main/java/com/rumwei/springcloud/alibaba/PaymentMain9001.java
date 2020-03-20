package com.rumwei.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//启动完成后，可以在nacos主页看到相关的服务注册信息，服务名就是application.yml中配置的nacos-payment-provider
//同时在nacos主页可以配置权重，来实现自定义的负载均衡
@SpringBootApplication
@EnableDiscoveryClient
public class PaymentMain9001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentMain9001.class,args);
    }
}
