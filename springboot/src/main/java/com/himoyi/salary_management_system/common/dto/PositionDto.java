package com.himoyi.salary_management_system.common.dto;

import lombok.Data;

/**
 * 岗位Dto
 */

@Data
public class PositionDto {
    private Long id;
    private String name;
    private String department;
    private Long departmentId;
}
