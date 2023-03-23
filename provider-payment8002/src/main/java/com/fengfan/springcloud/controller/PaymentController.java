package com.fengfan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * @author fengfan
 * @date 2023/3/23 10:09
 * @since JDK1.8
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/getPort")
    public String getPort(Integer id) {
        return "springcloud服务注册到zookeeper，服务端口号： " + port;
    }
}
