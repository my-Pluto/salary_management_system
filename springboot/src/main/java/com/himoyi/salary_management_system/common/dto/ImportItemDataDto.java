package com.himoyi.salary_management_system.common.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 高炉项目数据Dto
 */

@Data
public class ImportItemDataDto {
    private Long id;

    /**
     * 项目名称
     */
    private String itemName;

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
     * 员工姓名
     */
    private String employeeName;

    /**
     * 员工id
     */
    private Long employeeId;

    /**
     * 数据
     */
    private BigDecimal data;

    /**
     * 年月
     */
    private String month;
    
    private String lowMonth;

    private String highMonth;


}
