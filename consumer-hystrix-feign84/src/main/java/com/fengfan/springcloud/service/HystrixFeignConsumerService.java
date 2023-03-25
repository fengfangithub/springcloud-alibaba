package com.fengfan.springcloud.service;

import com.fengfan.springcloud.service.impl.HystrixFeignFallbackImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author fengfan
 * @date 2023/3/25 21:51
 * @since JDK1.8
 */
@FeignClient(value = "hystrix-provider-payment", fallback = HystrixFeignFallbackImpl.class)
public interface HystrixFeignConsumerService {

    @GetMapping("/hystrix/ok/{id}")
    String infoOk(@PathVariable String id);

    @GetMapping("/hystrix/timeOut/{id}")
    String timeOut(@PathVariable String id);
}
