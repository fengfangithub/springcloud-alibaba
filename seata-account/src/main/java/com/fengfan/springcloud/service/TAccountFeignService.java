package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.TAccount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("seata-account")
public interface TAccountFeignService {

    @PostMapping("/tAccount/edit")
    public ResponseEntity<TAccount> edit(@RequestBody TAccount tAccount);

    @GetMapping("/tAccount/queryById/{id}")
    public ResponseEntity<TAccount> queryById(@PathVariable("id") Long id);
}
