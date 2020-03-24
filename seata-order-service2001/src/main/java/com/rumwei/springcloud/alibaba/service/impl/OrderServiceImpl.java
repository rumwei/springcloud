package com.rumwei.springcloud.alibaba.service.impl;

import com.rumwei.springcloud.alibaba.dao.OrderDao;
import com.rumwei.springcloud.alibaba.entity.Order;
import com.rumwei.springcloud.alibaba.service.AccountService;
import com.rumwei.springcloud.alibaba.service.OrderService;
import com.rumwei.springcloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class OrderServiceImpl implements OrderService {
    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;
    @Override
    @GlobalTransactional(name = "create-order",rollbackFor = Exception.class) //分布式事务关键注解
    /** 1.若不添加该注解，同时seata-account-service2003微服务内部出现超时，那么该方法执行完之后，就会出现订单已入库、库存扣了、
     *    余额扣了(2003只是超时，没有在规定时间内返回结果给2001，并不影响2003内部执行完调用的逻辑)，最终订单状态未更改成完成的情况
     *  2.添加之后，同样seata-account-service2003微服务内部出现超时，该方法执行完后，三个库都不再会有相关记录的产生了
    * */
    public void create(Order order) {
        System.err.println("-----开始新建订单");
        orderDao.create(order);
        System.err.println("-----订单微服务调用库存微服务，库存扣减begin");
        storageService.decrease(order.getProductId(),order.getCount());
        System.err.println("-----订单微服务调用库存微服务，库存扣减end");

        System.err.println("-----订单微服务调用账户微服务，账户扣减begin");
        accountService.decrease(order.getUserId(),order.getMoney());
        System.err.println("-----订单微服务调用库存微服务，账户扣减end");

        System.err.println("-----修改订单状态");
        orderDao.update(order.getUserId(),1);

        System.err.println("-----下订单结束~~~");
    }

}
