package com.fengfan.springcloud.service;

import com.fengfan.springcloud.entity.TAccount;

/**
 * (TAccount)表服务接口
 *
 * @author makejava
 * @since 2023-04-01 17:44:34
 */
public interface TAccountService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TAccount queryById(Long id);

    /**
     * 新增数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    TAccount insert(TAccount tAccount);

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    TAccount update(TAccount tAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
