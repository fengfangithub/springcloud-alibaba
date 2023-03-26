package com.fengfan.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fengfan
 * @description 打印配置服务中的配置内容
 * @date 2022/5/30 9:37
 */
@RestController
@RequestMapping("/api")
@RefreshScope
public class ConfigPrintController {
    @Value("${config.name}")
    private String name;
    @Value("${config.age}")
    private String age;

    @PostMapping("/printConfig")
    public String printConfig(){
        return "name：" + name + "，age：" + age;
    }
}
