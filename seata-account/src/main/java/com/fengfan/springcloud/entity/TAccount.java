package com.fengfan.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * (TAccount)实体类
 *
 * @author makejava
 * @since 2023-04-01 17:38:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TAccount implements Serializable {
    private static final long serialVersionUID = -74333906289184490L;
    /**
     * id
     */
    private Long id;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 总额度
     */
    private BigDecimal total;
    /**
     * 已用余额
     */
    private BigDecimal used;
    /**
     * 剩余可用额度
     */
    private BigDecimal residue;

}

