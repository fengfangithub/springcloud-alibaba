package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.Payment;

/**
 * @author fengfan
 * @date 2023/3/23 10:05
 * @since JDK1.8
 */
public interface PaymentService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Payment queryById(Integer id);

    /**
     * 新增数据
     *
     * @param payment 实例对象
     * @return 影响行数
     */
    int insert(Payment payment);
}
