package com.rumwei.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope //实现配置的动态刷新，否则更新了github，3344可以同步拿到最新值，但是3355还是使用老配置值
//具体步骤：1.在github上更新了配置之后，因为已经引入了cloud bus，此时只需要用户手动通过http请求刷新一下配置中心
//服务端3344：curl -X POST "http://localhost:3344/actuator/bus-refresh"即可完成3355和3366的刷新
//2.在github上更新了配置之后，如果只想通知本实例(3366)，而让3355还是使用老配置，则用户手动通过http请求刷新配置中心服务
//端3344：curl -X POST "http://localhost:3344/actuator/bus-refresh/config-client:3366"即可只刷新3366。其中config-client即
//为bootstrap.yml中的spring.application.name配置的应用名
public class ConfigClientController {
    @Value("${env}")
    private String env; //注意yml文件的冒号后有空格，否则读取失败，即env: dev,而不是env:dev

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/config/get")
    public String getConfig(){
        return "serverPort:"+serverPort+"\t\n"+"env:"+env;
    }
}
