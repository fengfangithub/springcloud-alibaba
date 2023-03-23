package com.fengfna.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author fengfan
 * @date 2023/3/23 11:02
 * @since JDK1.8
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private static final String URL = "http://localhost:8001/payment/";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(URL + "insert", payment, CommonResult.class);
    }
}
