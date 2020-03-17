package com.rumwei.springcloud.service;

import org.springframework.stereotype.Service;

//该类专门用于对OrderHystrixService中的接口做服务降级逻辑
//当调用服务提供方发生超时，错误，或者服务端机器宕机(即服务器关机),都会进该降级处理类
@Service
public class OrderFallBackService implements OrderHystrixService{
    @Override
    public String paymentInfo_OK(Integer id) {
        return "-----OrderFallBackService fall back, paymentInfo_OK进入了服务降级处理";
    }
    @Override
    public String paymentInfo_Timeout(Integer id) {
        return "-----OrderFallBackService fall back, paymentInfo_Timeout进入了服务降级处理";
    }
}
