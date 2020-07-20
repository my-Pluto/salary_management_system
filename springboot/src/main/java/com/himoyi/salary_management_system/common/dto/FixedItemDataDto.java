package com.himoyi.salary_management_system.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class FixedItemDataDto {

    /**
     * 记录id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目id
     */
    private Long itemId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 部门id
     */
    private Long deptId;

    /**
     * 员工名称
     */
    private String employeeName;

    /**
     * 员工id
     */
    private Long employeeId;

    /**
     * 金额
     */
    private BigDecimal salary;

    private BigDecimal lowSalary;

    private BigDecimal highSalary;
}
