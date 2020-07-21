package com.himoyi.salary_management_system.common.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class StatementDto {
    private Long id;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 项目在工资条中的序号
     */
    private Integer number;

    /**
     * 员工工号
     */
    private Long employeeId;

    private String employeeName;

    /**
     * 员工所在部门
     */
    private String departmentName;

    private Long departmentId;
    /**
     * 项目数据
     */
    private BigDecimal data;

    /**
     * 是否已经发放
     */
    private Integer isProvided;

    /**
     * 年月信息
     */
    private String month;

}
