package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.service.OrderIdGenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderIdGenController {
    @Autowired
    private OrderIdGenService orderIdGenService;

    @RequestMapping("/snowflake")
    public String orderIdGen(){
        return orderIdGenService.getOrderIdBySnowFlake();
    }
}
