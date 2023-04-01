package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.TAccount;
import com.fengfan.springcloud.service.TAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TAccount)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:55:33
 */
@RestController
@RequestMapping("/tAccount")
public class TAccountController {
    /**
     * 服务对象
     */
    @Resource
    private TAccountService tAccountService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public ResponseEntity<TAccount> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tAccountService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tAccount 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<TAccount> add(TAccount tAccount) {
        return ResponseEntity.ok(this.tAccountService.insert(tAccount));
    }

    /**
     * 编辑数据
     *
     * @param tAccount 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public ResponseEntity<TAccount> edit(@RequestBody TAccount tAccount) {
        return ResponseEntity.ok(this.tAccountService.update(tAccount));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("deleteById")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tAccountService.deleteById(id));
    }

}

