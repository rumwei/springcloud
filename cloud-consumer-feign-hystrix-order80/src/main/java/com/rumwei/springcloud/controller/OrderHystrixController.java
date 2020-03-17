package com.rumwei.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.rumwei.springcloud.service.OrderHystrixService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//配置通用的降级方法，即本类下的所有带@HystrixCommand注解同时没有单独配置降级方法的服务，就使用commonFallBack()方法去兜底
//@DefaultProperties(defaultFallback = "commonFallBack")
public class OrderHystrixController {
    @Autowired
    OrderHystrixService service;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
//    @HystrixCommand
    public String paymentInfo_OK(@PathVariable("id") Integer id){
        String result = service.paymentInfo_OK(id);
        return result;
    }
    //没有服务降级
//    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
//        String result = service.paymentInfo_Timeout(id);
//        return result;
//    }
    //引入服务降级
    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "fallbackMethod",commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    public String paymentInfo_Timeout(@PathVariable("id") Integer id){
        //int i=10/0; //同样会走降级逻辑
        String result = service.paymentInfo_Timeout(id);
        return result;
    }
//    public String fallbackMethod(Integer id){
//        return "我是消费者80，服务端没有在约定时间内返回给我结果或者本应用自己报错，因此发生服务降级。id："+id;
//    }

    //全局FallBack兜底方法
//    public String commonFallBack(){
//        return "我是消费者80，现在进入了Global全局服务降级";
//    }

}
