package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort;
    @GetMapping("/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_OK(id);
        System.err.println("ok:"+result);
        return result;
    }
    @GetMapping("/payment/hystrix/timeout/{id}")
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        String result = paymentService.paymentInfo_Timeout(id);
        System.err.println("8001's timeout:"+result);
        return result;
    }
    @GetMapping("/get")
    public String get(){
        System.err.println("ko");
        return "ko";
    }
    //==========服务熔断
    //首先请求http://localhost:8001/payment/circuit/9，可以正常返回
    //然后疯狂请求http://localhost:8001/payment/circuit/-9，让错误请求比例超过60%
    //然后请求http://localhost:8001/payment/circuit/9，发现也进降级逻辑了，然后等待10s左右，再次请求能正常返回了
    @GetMapping("/payment/circuit/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        System.err.println("8001's circuitBreaker "+result);
        return result;
    }
}
