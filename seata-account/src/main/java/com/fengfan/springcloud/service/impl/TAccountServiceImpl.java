package com.fengfan.springcloud.service.impl;

import com.fengfan.springcloud.dao.TAccountDao;
import com.fengfan.springcloud.entity.TAccount;
import com.fengfan.springcloud.service.TAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TAccount)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 17:44:35
 */
@Service("tAccountService")
public class TAccountServiceImpl implements TAccountService {
    @Resource
    private TAccountDao tAccountDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TAccount queryById(Long id) {
        return this.tAccountDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    @Override
    public TAccount insert(TAccount tAccount) {
        this.tAccountDao.insert(tAccount);
        return tAccount;
    }

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 实例对象
     */
    @Override
    public TAccount update(TAccount tAccount) {
        this.tAccountDao.update(tAccount);
        return this.queryById(tAccount.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tAccountDao.deleteById(id) > 0;
    }
}
