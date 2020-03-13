package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.entity.CommonResult;
import com.rumwei.springcloud.entity.Payment;
import com.rumwei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping(value = "payment/save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        log.info("插入结果："+result);
        if (result > 0) return new CommonResult(200,"支付成功",result);
        return new CommonResult(500,"支付失败");
    }
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        new Exception().printStackTrace();
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result.getSerial());
        if (result != null) return new CommonResult(200,"查询成功",result);
        return new CommonResult(500,"查询失败,对应id为"+id);
    }




}
