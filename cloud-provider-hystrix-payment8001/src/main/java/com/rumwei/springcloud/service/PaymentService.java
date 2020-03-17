package com.rumwei.springcloud.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {
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
    public String paymentInfo_Timeout(Integer id){
        try{
            Thread.sleep(3000); //3s休眠
        }catch(Exception e){}
        return "线程池: "+Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+" 耗时3s";
    }
}
