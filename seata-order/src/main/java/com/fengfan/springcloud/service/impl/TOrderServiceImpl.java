package com.fengfan.springcloud.service.impl;

import com.fengfan.springcloud.dao.TOrderDao;
import com.fengfan.springcloud.entity.TAccount;
import com.fengfan.springcloud.entity.TOrder;
import com.fengfan.springcloud.entity.TStorage;
import com.fengfan.springcloud.service.TAccountFeignService;
import com.fengfan.springcloud.service.TOrderService;
import com.fengfan.springcloud.service.TStorageFeignService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * (TOrder)表服务实现类
 *
 * @author makejava
 * @since 2023-04-01 17:45:26
 */
@Service("tOrderService")
public class TOrderServiceImpl implements TOrderService {
    @Resource
    private TOrderDao tOrderDao;
    @Resource
    private TAccountFeignService tAccountFeignService;
    @Resource
    private TStorageFeignService tStorageFeignService;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TOrder queryById(Long id) {
        return this.tOrderDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder insert(TOrder tOrder) {
        this.tOrderDao.insert(tOrder);
        return tOrder;
    }

    @GlobalTransactional(name = "fsp-create-order",rollbackFor = Exception.class)
    @Override
    public TOrder create() {
        TOrder tOrder = new TOrder();
        tOrder.setUserId(2L);
        tOrder.setProductId(2L);
        tOrder.setCount(10);
        tOrder.setMoney(new BigDecimal(100));
        tOrderDao.insert(tOrder);

        TStorage tStorage = tStorageFeignService.queryById(tOrder.getProductId()).getBody();
        tStorage.setResidue(tStorage.getResidue() - tOrder.getCount());
        tStorage.setUsed(tStorage.getUsed() + tOrder.getCount());
        tStorageFeignService.edit(tStorage);

        TAccount tAccount = tAccountFeignService.queryById(tOrder.getUserId()).getBody();
        tAccount.setResidue(tAccount.getResidue().subtract(tOrder.getMoney()));
        tAccount.setUsed(tAccount.getUsed().add(tOrder.getMoney()));
        tAccountFeignService.edit(tAccount);

        int a = 1/0;

        return tOrder;
    }

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 实例对象
     */
    @Override
    public TOrder update(TOrder tOrder) {
        this.tOrderDao.update(tOrder);
        return this.queryById(tOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tOrderDao.deleteById(id) > 0;
    }
}
