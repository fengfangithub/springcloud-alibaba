package com.fengfan.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fengfan.**.dao")
public class ProviderPayment8001Application {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment8001Application.class, args);
    }

}
