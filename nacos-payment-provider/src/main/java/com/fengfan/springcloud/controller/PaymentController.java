package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.CommonResult;
import com.fengfan.springcloud.entity.Payment;
import com.fengfan.springcloud.service.PaymentFeignService;
import com.fengfan.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author fengfan
 * @date 2023/3/23 10:09
 * @since JDK1.8
 */
@RestController
@RequestMapping("/payment")
public class PaymentController implements PaymentFeignService {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/queryById/{id}")
    @Override
    public CommonResult<Payment> queryById(@PathVariable Integer id){
        Payment payment = paymentService.queryById(id);
        if(payment != null){
            return new CommonResult<Payment>(200, "成功", payment);
        }else {
            return new CommonResult<Payment>(500, "失败");
        }
    }

    @PostMapping("/insert")
    @Override
    public CommonResult<Integer> insert(@RequestBody Payment payment){
        int result = paymentService.insert(payment);
        if(result > 0){
            return new CommonResult<Integer>(200, "成功", result);
        }else {
            return new CommonResult<Integer>(500, "失败");
        }
    }

    @GetMapping("/getTimeOut")
    @Override
    public CommonResult<String> getTimeOut() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return new CommonResult<>(200, "成功", "超时控制");
    }

}
