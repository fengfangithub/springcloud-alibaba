package com.fengfan.springcloud.dao;

import com.fengfan.springcloud.entity.TStorage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TStorage)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 17:42:33
 */
public interface TStorageDao {

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
     * @return 影响行数
     */
    int insert(TStorage tStorage);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TStorage> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TStorage> entities);

    /**
     * 修改数据
     *
     * @param tStorage 实例对象
     * @return 影响行数
     */
    int update(TStorage tStorage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
