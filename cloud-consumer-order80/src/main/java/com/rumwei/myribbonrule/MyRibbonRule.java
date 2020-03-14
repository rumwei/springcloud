package com.rumwei.myribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyRibbonRule {
    /**
    * 使用相关配合
    * 1.主启动类加@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration = MyRibbonRule.class)注解
    * */
    @Bean
    public IRule myRule(){
        return new RandomRule(); //随机策略,netflix自带
    }
}
