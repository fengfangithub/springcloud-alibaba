package com.fengfan.springcloud.service.impl;

import com.fengfan.springcloud.dao.PaymentDao;
import com.fengfan.springcloud.entity.Payment;
import com.fengfan.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author fengfan
 * @date 2023/3/23 10:08
 * @since JDK1.8
 */
@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;

    @Override
    public Payment queryById(Integer id) {
        return paymentDao.queryById(id);
    }

    @Override
    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }
}
