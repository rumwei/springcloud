package com.rumwei.springcloud.alibaba;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //支持Nacos动态刷新
public class ConfigClientController {
    @Value("${appId}")
    private String appId; //在Nacos上配置的字段
    @GetMapping(value = "get/config")
    public String getConfig(){
        return "config in nacos is appId: "+appId+" and service from 3377";
    }
}
