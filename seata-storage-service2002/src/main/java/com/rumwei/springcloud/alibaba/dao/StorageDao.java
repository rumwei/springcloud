package com.rumwei.springcloud.alibaba.dao;

public interface StorageDao {
    void decrease(Long productId,Integer count);
}
