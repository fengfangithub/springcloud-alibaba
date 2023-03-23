package com.fengfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ProviderPayment8002Application {

    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment8002Application.class, args);
    }

}
