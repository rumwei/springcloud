package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.entity.CommonResult;
import com.rumwei.springcloud.entity.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;

//RestTemplate是Spring提供的用于访问Rest服务的客户端模版工具集
@RestController
@Slf4j
@RequestMapping("/consumer")
public class OrderController {
    //private static final String PAYMENT_URL = "http://localhost:8003"; //未接入Eureka之前
    //接入Eureka,服务名配合@LoadBalanced(配置一种选择服务主机的规则)注解可以找到具体的主机
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    @Autowired
    RestTemplate restTemplate;
    @PostMapping("/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/save",payment,CommonResult.class);
    }
    @GetMapping("/payment/get/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) throws IOException {
        System.out.println(id);
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }
}