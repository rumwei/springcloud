package com.rumwei.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan({"com.rumwei.springcloud.alibaba.dao"})
public class MybatisConfig {
}
