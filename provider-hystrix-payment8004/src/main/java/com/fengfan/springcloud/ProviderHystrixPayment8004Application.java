package com.fengfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProviderHystrixPayment8004Application {

    public static void main(String[] args) {
        SpringApplication.run(ProviderHystrixPayment8004Application.class, args);
    }

}
