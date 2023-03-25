package com.fengfan.springcloud.service.impl;

import com.fengfan.springcloud.service.HystrixFeignConsumerService;
import org.springframework.stereotype.Component;

/**
 * @author fengfan
 * @date 2023/3/25 22:20
 * @since JDK1.8
 */
@Component
public class HystrixFeignFallbackImpl implements HystrixFeignConsumerService {
    @Override
    public String infoOk(String id) {
        return "infoOk fallback";
    }

    @Override
    public String timeOut(String id) {
        return "timeout fall back";
    }
}
