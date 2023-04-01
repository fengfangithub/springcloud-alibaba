package com.fengfan.springcloud.controller;

import com.fengfan.springcloud.entity.TOrder;
import com.fengfan.springcloud.service.TOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (TOrder)表控制层
 *
 * @author makejava
 * @since 2023-04-01 18:57:08
 */
@RestController
@RequestMapping("/tOrder")
public class TOrderController {
    /**
     * 服务对象
     */
    @Resource
    private TOrderService tOrderService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<TOrder> queryById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(this.tOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param tOrder 实体
     * @return 新增结果
     */
    @PostMapping("create")
    public ResponseEntity<TOrder> create(TOrder tOrder) {
        return ResponseEntity.ok(this.tOrderService.create());
    }

    /**
     * 编辑数据
     *
     * @param tOrder 实体
     * @return 编辑结果
     */
    @PostMapping("edit")
    public ResponseEntity<TOrder> edit(TOrder tOrder) {
        return ResponseEntity.ok(this.tOrderService.update(tOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @PostMapping("deleteById")
    public ResponseEntity<Boolean> deleteById(Long id) {
        return ResponseEntity.ok(this.tOrderService.deleteById(id));
    }

}

