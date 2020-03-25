package com.rumwei.springcloud.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IDGeneratorSnowFlake {
    /**
    * dataCenterId与workerId取值范围都是0～31
    * */
    private long dataCenterId = 1; //数据中心
    private long workerId = 0; //机器ID
    private Snowflake snowflake = IdUtil.createSnowflake(workerId,dataCenterId);

    @PostConstruct
    public void init(){
        try{
            workerId = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            System.out.println("当前机器的workerId: "+workerId);
        }catch(Exception e){
            System.err.println("当前机器的workerId获取失败");
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
    }
    //使用默认的workerId与dataCenterId
    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }
    //自己指定workerId与dataCenterId
    public synchronized long snowflakeId(long workerId, long dataCenterId){
        Snowflake snowflake = IdUtil.createSnowflake(workerId,dataCenterId);
        return snowflake.nextId();
    }
}
