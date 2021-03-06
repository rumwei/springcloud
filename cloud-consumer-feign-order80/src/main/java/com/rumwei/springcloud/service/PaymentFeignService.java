package com.rumwei.springcloud.service;

import com.rumwei.springcloud.entity.CommonResult;
import com.rumwei.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 该接口中的方法定义与服务提供方的Controller保持一致
 * 具体可以查看8001，8002两个项目的PaymentController类
* */
@Component
//@FeignClient(value = "CLOUD-PAYMENT-SERVICE") //服务提供方应用名，直接调用服务提供方
@FeignClient(value = "CLOUD-GATEWAY-SERVICE") //Gateway网关名，通过网关转发去访问服务
public interface PaymentFeignService {
//    CommonResult<Integer> save(Payment payment);
    @GetMapping(value = "payment/get/{id}") //注解与服务提供方8001，8002的Controller保持一致
    public CommonResult getPaymentById(@PathVariable("id") Long id);
    @GetMapping(value = "/payment/zipkin")
    public String paymentZipKin();
}
