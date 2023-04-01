package com.fengfan.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fengfan.springcloud.entity.CommonResult;

/**
 * @author fengfan
 * @date 2023/4/1 15:19
 * @since JDK1.8
 */
public class CustomerBlockHandler {
    public static CommonResult handlerException(BlockException e) {
        return new CommonResult(444, "限流，禁止访问");
    }
}
