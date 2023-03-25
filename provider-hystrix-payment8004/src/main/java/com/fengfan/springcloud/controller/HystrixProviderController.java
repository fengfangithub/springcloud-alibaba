package com.fengfan.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengfan
 * @date 2023/3/25 21:26
 * @since JDK1.8
 */
@RestController
@RequestMapping("/hystrix")
public class HystrixProviderController {
    @Value("${server.port}")
    private String port;

    @GetMapping("/ok/{id}")
    public String infoOk(@PathVariable String id){
        return "线程池" + Thread.currentThread().getName() + "infoOk，id-" + id;
    }

    @GetMapping("/timeOut/{id}")
    public String timeOut(@PathVariable String id){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "线程池" + Thread.currentThread().getName() + "timeOut，id-" + id + "耗时3秒钟";
    }
}

