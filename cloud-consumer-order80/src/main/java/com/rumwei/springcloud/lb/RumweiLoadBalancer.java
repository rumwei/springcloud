package com.rumwei.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface RumweiLoadBalancer {
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
