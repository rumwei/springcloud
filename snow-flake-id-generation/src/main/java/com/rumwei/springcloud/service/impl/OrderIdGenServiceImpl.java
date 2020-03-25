package com.rumwei.springcloud.service.impl;

import com.rumwei.springcloud.service.OrderIdGenService;
import com.rumwei.springcloud.util.IDGeneratorSnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderIdGenServiceImpl implements OrderIdGenService {
    @Autowired
    private IDGeneratorSnowFlake snowFlake;
    @Override
    public String getOrderIdBySnowFlake() {
        //测试begin
        long a = System.currentTimeMillis();
        for (int i=0; i<10000; i++){
            System.out.println(snowFlake.snowflakeId());
        }
        System.out.println("产生10000个ID花费的时间"+(System.currentTimeMillis()-a)+"ms"); //经测试10000个id，42ms产生
        //测试end
        return snowFlake.snowflakeId()+"";
    }
}
