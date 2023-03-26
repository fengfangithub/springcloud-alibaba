package com.fenfan.zuul.config;

import com.fenfan.zuul.filter.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fengfan
 * @description 开发filter配置
 * @date 2022/5/26 16:36
 */
//@Configuration
public class ZuulFilterConfig {

    @Bean
    public AuthFilter authFilter(){
        return new AuthFilter();
    }

}
