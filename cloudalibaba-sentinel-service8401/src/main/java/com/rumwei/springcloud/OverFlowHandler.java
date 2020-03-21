package com.rumwei.springcloud;

import com.alibaba.csp.sentinel.slots.block.BlockException;

public class OverFlowHandler {
    public String blockDeal(BlockException be){
        return "进入限流处理逻辑";
    }
    public String fallbackDeal(Throwable t){
        return "进入异常降级处理逻辑";
    }
}
