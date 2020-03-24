package com.rumwei.springcloud.alibaba.dao;

import java.math.BigDecimal;

public interface AccountDao {
    void decrease(Long userId, BigDecimal money);
}
