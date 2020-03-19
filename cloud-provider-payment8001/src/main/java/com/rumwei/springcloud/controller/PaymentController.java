package com.rumwei.springcloud.controller;

import com.rumwei.springcloud.entity.CommonResult;
import com.rumwei.springcloud.entity.Payment;
import com.rumwei.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Value("${server.port}")
    private String serverPort; //作为服务端的标记，服务端时集群时，可以判断客户端来调用时，调的是本服务端
    @PostMapping(value = "payment/save")
    public CommonResult save(@RequestBody Payment payment){
        int result = paymentService.save(payment);
        if (result > 0) return new CommonResult(200,"支付成功,serverPort: "+serverPort,result);
        return new CommonResult(500,"支付失败");
    }
    @GetMapping(value = "payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment result = paymentService.getPaymentById(id);
        log.info("查询结果："+result.getSerial());
        if (result != null) return new CommonResult(200,"查询成功,serverPort: "+serverPort,result);
        return new CommonResult(500,"查询失败,对应id为"+id);
    }
    @Autowired
    private DiscoveryClient discoveryClient; //可以获取已经注册到Eureka中到微服务的信息,注意需要EnableEurekaClient注解
    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        //获取已经注册到Eureka中的微服务的服务名列表
        List<String> services = discoveryClient.getServices();
        services.forEach(p->{System.err.println("注册到Eureka的微服务名称："+p);});
        //根据服务名称查找Eureka上的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(p->{
            System.err.println("服务id: "+p.getServiceId()+"\n"+"实例id："+p.getInstanceId()
            +"主机名称："+p.getHost()+"\n"+"服务端口："+p.getPort()+"\n"+"服务的URI："+p.getUri());
        });
        return this.discoveryClient;
    }
    //用于验证自定义的负载均衡算法,lb即loadBalance
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB(){
        return serverPort;
    }
    //用于验证zipkin的链路信息收集功能
    @GetMapping(value = "/payment/zipkin")
    public String paymentZipKin(){
        return serverPort+" hi, I am paymentZipKin server fall back!";
    }




}
