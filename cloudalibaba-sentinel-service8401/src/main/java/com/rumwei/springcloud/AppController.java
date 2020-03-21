package com.rumwei.springcloud;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
    @GetMapping("/testa")
    public String testa(){
        try{
            Thread.sleep(1000);
        }catch(Exception e){}
        return "-----testA";
    }
    @GetMapping("/testb")
    public String testb(){
        return "-----testB";
    }
    @GetMapping("/testc")
    public String testc(){
        try{ Thread.sleep(2000); }catch(Exception e){} //延时降级中的RT
        return "-----testC";
    }
    //对应http://localhost:8401/hotKey?p1=a&p2=b
    //注意此处blockHandler对应的方法只处理超过限流之后的请求处理。发生异常等的兜底方法由fallback指定
    @GetMapping("/hotKey")
    @SentinelResource(value = "hotKey", blockHandler = "overflowMethod", fallback = "exceptionMethod") //作用类似HystrixCommand
    public String hotKey(@RequestParam(value = "p1",required = false) String p1,
                         @RequestParam(value = "p2",required = false) String p2){
        return "hotKey test";
    }
    //降级兜底方法,如果不提供，Sentinel将默认返回Blocked by Sentinel (flow limiting)
    public String overflowMethod(String p1, String p2, BlockException be){
        return "hotKey超过限流进入降级方法";
    }
    //异常兜底方法
    public String exceptionMethod(String p1, String p2, Throwable t){
        return "hotKey发生异常进入降级方法";
    }

    //主逻辑与降级逻辑分开,不耦合在一个类中
    //当同时触发限流规则(block)和降级(fallback)时，以限流规则定义的逻辑为准
    @GetMapping("get")
    @SentinelResource(value = "get", blockHandlerClass = OverFlowHandler.class,
            blockHandler = "blockDeal", fallback = "fallbackDeal")
    public String get(){
        return "get";
    }
}
