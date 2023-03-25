package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author fengfan
 * @date 2023/3/25 16:36
 * @since JDK1.8
 */
@FeignClient(value = "eureka-provider-payment")
public interface PaymentFeignService {

    @GetMapping("/payment/queryById/{id}")
    CommonResult<Payment> queryById(@PathVariable Integer id);

    @PostMapping("/payment/insert")
    CommonResult<Integer> insert(@RequestBody Payment payment);

    @GetMapping("/payment/getTimeOut")
    CommonResult<String> getTimeOut();
}
