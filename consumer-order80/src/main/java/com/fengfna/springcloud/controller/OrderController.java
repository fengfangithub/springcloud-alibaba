package com.fengfna.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.*;
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
    private static final String URL = "http://eureka-provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/create")
    public CommonResult<Integer> create(Payment payment) {
        return restTemplate.postForObject(URL + "/payment/insert", payment, CommonResult.class);
    }

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable String id) {
        return restTemplate.getForObject(URL + "/payment/queryById/" + id, CommonResult.class);
    }

}
