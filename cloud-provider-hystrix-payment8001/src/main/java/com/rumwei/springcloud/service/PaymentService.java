package com.rumwei.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    //==========服务降级
    //模拟可以正常返回的服务
    public String paymentInfo_OK(Integer id){
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_OK,id: "+id;
    }
    //模拟会超时或者会出错的服务，本应用作为服务提供方，cloud-consumer-feign-hystrix-order80(下称FHO80)作为本服务的消费方
    //压测结果
    //不采用Hystrix，用JMeter开200线程，循环100次请求该接口的同时，利用postman请求FHO80的paymentInfo_OK接口1000次,可以看到接口调用的
    //耗时在6ms～40ms(正常4~10ms)波动，部分请求会出现500服务器内部错误，对应的FHO80控制台会打印Read timed out异常信息。
    //说明高并发下，该接口的拥堵会影响其他接口提供的服务，tomcat默认的线程数被打满，没有多余的线程来处理上面的OK接口服务
    //正因为有上述的故障或者不佳表现，才诞生了服务降级/容错/限流等技术
//    public String paymentInfo_Timeout(Integer id){
//        try{
//            Thread.sleep(3000); //3s休眠
//        }catch(Exception e){}
//        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+" 耗时3s";
//    }
    //采用Hystrix,配合@EnableCircuitBreaker注解
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            //表示该方法的允许处理时间,单位ms.注意Eureka服务端调用客户端默认允许的延时1000ms
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String paymentInfo_Timeout(Integer id){
        try{
            Thread.sleep(3000); //3s休眠
        }catch(Exception e){}
        //int age = 10/0; //抛异常也会走下面的兜底逻辑
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+" 耗时3s";
    }
    //当上述方法超时或者抛出错误信息，异常等时，会使用下面方法来响应服务调用方
    public String paymentInfo_TimeoutHandler(Integer id){
        //从下方返回的线程池名称可以看出，处理该方法的线程是HystrixTimer线程池，不再占用主线程池，即降级逻辑的执行不再影响其他接口服务的正常运行
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_TimeoutHandler,发生服务降级";
    }

    //==============服务熔断
    //以下断路器有CLOSED(闭合,正常提供服务),OPEN(断开,表示服务不再可用)
    //以下配置表示如果存在10次请求失败6次(10*60%)及以上，则该断路器从CLOSED切换到OPEN，在这期间所有请求会进行服务降级
    //10s后，会尝试让服务处理一个请求(从OPEN切到Half-Open)，如果处理成功，则断路器切回CLOSED，并进入请求监控状态。如果处理失败，则再OPEN10s，如此重复循环下去
    //另外用户可以配置一定的规则，当断路器在Half-Open状态时，放入的请求可以根据定义的规则来过滤；也可以不配置
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"), //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"), //请求次数，默认20
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "20000"), //时间窗口期,此处即10s，默认5s
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸，默认50%
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0) throw new RuntimeException("8001's exception id不能为负数"); //模拟失败
        String serialNumber = IdUtil.simpleUUID(); //来自HuTool工具包，类似UUID.random
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "8001's fall back. id 不能为负数，请稍后再试，id："+id;
    }
}
