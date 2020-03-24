package com.rumwei.springcloud.alibaba.service;

import com.rumwei.springcloud.alibaba.dao.AccountDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl {
    @Resource
    private AccountDao accountDao;

    public void decrease(Long userId, BigDecimal money){
        System.err.println("Account微服务中，扣减余额开始");
        accountDao.decrease(userId,money);
        System.err.println("Account微服务中，扣减余额结束");
    }
}
