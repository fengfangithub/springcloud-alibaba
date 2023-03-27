package com.fengfan.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StreamRabbitmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreamRabbitmqConsumerApplication.class, args);
    }

}
