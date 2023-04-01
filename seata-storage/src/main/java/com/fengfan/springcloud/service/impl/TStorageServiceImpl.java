package com.fengfan.springcloud.service.impl;

import com.fengfan.springcloud.dao.TStorageDao;
import com.fengfan.springcloud.entity.TStorage;
import com.fengfan.springcloud.service.TStorageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (TStorage)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 17:45:42
 */
@Service("tStorageService")
public class TStorageServiceImpl implements TStorageService {
    @Resource
    private TStorageDao tStorageDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TStorage queryById(Long id) {
        return this.tStorageDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param tStorage 实例对象
     * @return 实例对象
     */
    @Override
    public TStorage insert(TStorage tStorage) {
        this.tStorageDao.insert(tStorage);
        return tStorage;
    }

    /**
     * 修改数据
     *
     * @param tStorage 实例对象
     * @return 实例对象
     */
    @Override
    public TStorage update(TStorage tStorage) {
        this.tStorageDao.update(tStorage);
        return this.queryById(tStorage.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tStorageDao.deleteById(id) > 0;
    }
}
