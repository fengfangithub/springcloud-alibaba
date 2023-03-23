package com.fengfan.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
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
public class OrderZookeeperController {
    private static final String URL = "http://provider-payment";
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/getPort")
    public String getPort() {
        return restTemplate.getForObject(URL + "/payment/getPort", String.class);
    }
}
