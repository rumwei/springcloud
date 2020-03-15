package com.rumwei.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RumweiLoadBalancerImpl implements RumweiLoadBalancer{
    private AtomicInteger atomicInteger = new AtomicInteger(0);
    //对请求进行计数(线程安全的)
    public final int getAndIncrement(){
        int current;
        int next;
        do{
            current = this.atomicInteger.get();
            next = current >= Integer.MAX_VALUE?0:current+1;
        }while (!this.atomicInteger.compareAndSet(current,next));
        //compareAndSet(expectedValue, newValue) : 如果当前值(current value)(此处指atomicInteger变量)等于期待的值(expectedValue)(此处指current),
        //则原子地更新指定值为新值(newValue)(此处指next), 如果更新成功，返回true, 否则返回false, 换句话可以这样说: 将原子变量设置为新的值, 但是如果从我上
        //次看到的这个变量之后到现在被其他线程修改了(和我期望看到的值不符), 那么更新失败
        return next;
    }
    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
