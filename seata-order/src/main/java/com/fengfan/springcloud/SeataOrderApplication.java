package com.fengfan.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.fengfan.**.dao")
@EnableDiscoveryClient
@EnableFeignClients
public class SeataOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeataOrderApplication.class, args);
    }

}
