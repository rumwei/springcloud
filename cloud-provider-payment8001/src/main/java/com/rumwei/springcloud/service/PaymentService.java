package com.rumwei.springcloud.service;

import com.rumwei.springcloud.entity.Payment;

public interface PaymentService {
    int save(Payment payment);
    Payment getPaymentById(Long id);
}
