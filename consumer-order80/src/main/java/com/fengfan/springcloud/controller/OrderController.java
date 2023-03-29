package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

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
    @Resource
    private DiscoveryClient client;

    @PostMapping("/create")
    public CommonResult<Integer> create(Payment payment) {
        return restTemplate.postForObject(URL + "/payment/insert", payment, CommonResult.class);
    }

    @GetMapping("/getPayment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable Integer id) {
        return restTemplate.getForObject(URL + "/payment/queryById/" + id, CommonResult.class);
    }

    @PostMapping("/discovery")
    public Object discovery(){
        List<String> list = client.getServices();
        System.out.println(list);
        List<ServiceInstance> serviceInstances = client.getInstances("eureka-provider-payment");
        for (ServiceInstance serviceInstance: serviceInstances){
            System.out.println(serviceInstance);
        }
        return client;
    }

    @GetMapping("/paymentZipkin")
    public String paymentZipkin(){
        return restTemplate.getForObject(URL + "/payment/paymentZipkin/", String.class);
    }
}
