package com.rumwei.springcloud.alibaba.service;

import com.rumwei.springcloud.alibaba.dao.StorageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class StorageServiceImpl {
    @Resource
    private StorageDao storageDao;

    public void decrease(Long productId,Integer count){
        System.err.println("storage微服务中，扣减库存开始");
        storageDao.decrease(productId,count);
        System.err.println("storage微服务中，扣减库存结束");
    }
}
