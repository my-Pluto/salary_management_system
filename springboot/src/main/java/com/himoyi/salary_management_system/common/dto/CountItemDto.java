package com.himoyi.salary_management_system.common.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CountItemDto {
    /**
     * 计算项目ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 计算项目名称
     */
    private String name;

    /**
     * 增减项
     */
    private Integer effect;

    /**
     * 工资条中的序号
     */
    private Integer number;

    /**
     * 在工资条中是否显示
     */
    private Integer isDisplay;

    /**
     * 第一个运算数
     */
    private String firstOperand;

    /**
     * 运算符
     */
    private String operator;

    /**
     * 第二个运算数
     */
    private BigDecimal secentOperand;

}
