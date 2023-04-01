package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.TStorage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("seata-storage")
public interface TStorageFeignService {

    @PostMapping("/tStorage/edit")
    public ResponseEntity<TStorage> edit(@RequestBody TStorage tStorage);

    @GetMapping("/tStorage/queryById/{id}")
    public ResponseEntity<TStorage> queryById(@PathVariable("id") Long id);
}
