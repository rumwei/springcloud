package com.rumwei.springcloud.alibaba.dao;

import com.rumwei.springcloud.alibaba.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderDao {
    void create(Order order); //新建订单
    void update(@Param("userId") Long userId, @Param("status") Integer status); //修改订单状态
}
