package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.entity.CommonResult;
import com.rumwei.springcloud.entity.Payment;
import com.rumwei.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class OrderFeignController {
    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id){
        return paymentFeignService.getPaymentById(id);
    }
//    @PostMapping("/consumer/payment/save")
//    public CommonResult<Integer> save(@RequestBody Payment payment){
//        return paymentFeignService.save(payment);
//    }
    @GetMapping(value = "/consumer/payment/zipkin")
    public String paymentZipKin(){
        return paymentFeignService.paymentZipKin();
    }
}
