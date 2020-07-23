package com.himoyi.salary_management_system.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * 员工Dto
 */
@Data
public class EmployeesDto {

    /**
     * 员工工号
     */
    private Long id;

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工性别
     */
    private String sex;

    /**
     * 员工部门
     */
    private String department;

    /**
     * 员工职位
     */
    private String position;

    /**
     * 员工联系方式
     */
    private String email;

    /**
     * 入职时间
     */
    private Date entryTime;
}
