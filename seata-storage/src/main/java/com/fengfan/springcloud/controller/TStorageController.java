package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.TStorage;
import com.fengfan.springcloud.service.TStorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TStorage)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:57:59
 */
@RestController
@RequestMapping("/tStorage")
public class TStorageController {
    /**
     * 服务对象
     */
    @Resource
    private TStorageService tStorageService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/queryById/{id}")
    public ResponseEntity<TStorage> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tStorageService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tStorage 实体
     * @return 新增结果
     */
    @PostMapping("add")
    public ResponseEntity<TStorage> add(TStorage tStorage) {
        return ResponseEntity.ok(this.tStorageService.insert(tStorage));
    }

    /**
     * 编辑数据
     *
     * @param tStorage 实体
     * @return 编辑结果
     */
    @PostMapping("/edit")
    public ResponseEntity<TStorage> edit(@RequestBody TStorage tStorage) {
        return ResponseEntity.ok(this.tStorageService.update(tStorage));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("deleteById")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tStorageService.deleteById(id));
    }

}

