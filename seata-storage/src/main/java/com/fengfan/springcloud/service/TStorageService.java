package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.TStorage;

/**
 * (TStorage)表服务接口
 *
 * @author makejava
 * @since 2023-04-01 17:45:41
 */
public interface TStorageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TStorage queryById(Long id);

    /**
     * 新增数据
     *
     * @param tStorage 实例对象
     * @return 实例对象
     */
    TStorage insert(TStorage tStorage);

    /**
     * 修改数据
     *
     * @param tStorage 实例对象
     * @return 实例对象
     */
    TStorage update(TStorage tStorage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
