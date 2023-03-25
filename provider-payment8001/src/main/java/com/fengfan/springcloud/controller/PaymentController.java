package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import com.fengfan.springcloud.service.PaymentService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author fengfan
 * @date 2023/3/23 10:09
 * @since JDK1.8
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Resource
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient client;

    @GetMapping("/queryById/{id}")
    public CommonResult<Payment> queryById(@PathVariable Integer id){
        Payment payment = paymentService.queryById(id);
        if(payment != null){
            return new CommonResult<Payment>(200, "成功", payment);
        }else {
            return new CommonResult<Payment>(500, "失败");
        }
    }

    @PostMapping("/insert")
    public CommonResult<Integer> insert(@RequestBody Payment payment){
        int result = paymentService.insert(payment);
        if(result > 0){
            return new CommonResult<Integer>(200, "成功", result);
        }else {
            return new CommonResult<Integer>(500, "失败");
        }
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

}
