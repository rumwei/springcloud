package com.rumwei.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //实现配置的动态刷新，否则更新了github，3344可以同步拿到最新值，但是3355还是使用老配置值
//具体步骤：在github上更新了配置之后，需要用户手动通过http请求来刷新3355的配置：curl -X POST "http://localhost:3355/actuator/refresh"
//执行返回成功后，3355即可拿到最新值
public class ConfigClientController {
    @Value("${env}")
    private String env; //注意yml文件的冒号后有空格，否则读取失败，即env: dev,而不是env:dev

    @GetMapping("/config/get")
    public String getConfig(){
        return env;
    }
}
