package com.fengfan.springcloud.dao;

import com.fengfan.springcloud.entity.TAccount;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (TAccount)表数据库访问层
 *
 * @author makejava
 * @since 2023-04-01 17:38:25
 */
public interface TAccountDao {

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
     * @return 影响行数
     */
    int insert(TAccount tAccount);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<TAccount> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<TAccount> entities);

    /**
     * 修改数据
     *
     * @param tAccount 实例对象
     * @return 影响行数
     */
    int update(TAccount tAccount);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}
