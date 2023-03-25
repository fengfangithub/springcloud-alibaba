package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.service.HystrixFeignConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author fengfan
 * @date 2023/3/25 21:26
 * @since JDK1.8
 */
@RestController
@RequestMapping("/consumer/hystrix")
public class HystrixConsumerController {
    @Resource
    private HystrixFeignConsumerService hystrixFeignConsumerService;

    @GetMapping("/ok/{id}")
    public String infoOk(@PathVariable String id){
        return "订单调用支付，返回：" + hystrixFeignConsumerService.infoOk(id);
    }

    @GetMapping("/timeOut/{id}")
    public String timeOut(@PathVariable String id){
       return "订单调用支付，返回：" + hystrixFeignConsumerService.timeOut(id);
    }

    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        return Thread.currentThread().getName()+"\t"+"调用成功";
    }
    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }
}

