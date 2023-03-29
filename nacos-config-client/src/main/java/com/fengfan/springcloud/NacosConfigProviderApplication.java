package com.fengfan.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.fengfan.**.dao")
@EnableDiscoveryClient
public class NacosConfigProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(NacosConfigProviderApplication.class, args);
    }

}
