package com.fengfan.springcloud.dao;

import com.fengfan.springcloud.entity.TOrder;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TOrder)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 17:41:17
 */
public interface TOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TOrder queryById(Long id);

    /**
     * 新增数据
     *
     * @param tOrder 实例对象
     * @return 影响行数
     */
    int insert(TOrder tOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TOrder> entities);

    /**
     * 修改数据
     *
     * @param tOrder 实例对象
     * @return 影响行数
     */
    int update(TOrder tOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
