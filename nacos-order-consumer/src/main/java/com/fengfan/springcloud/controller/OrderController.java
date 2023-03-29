package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import com.fengfan.springcloud.service.PaymentFeignService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fengfan
 * @date 2023/3/23 11:02
 * @since JDK1.8
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @PostMapping("/create")
    public CommonResult<Integer> create(Payment payment) {
        return paymentFeignService.insert(payment);
    }

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Integer id) {
        return paymentFeignService.queryById(id);
    }
}
