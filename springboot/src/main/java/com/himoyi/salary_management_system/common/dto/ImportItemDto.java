package com.himoyi.salary_management_system.common.dto;

import lombok.Data;


@Data
public class ImportItemDto {
    private Long id;

    /**
     * 导入项目名称
     */
    private String name;

    /**
     * 增减项
     */
    private Integer effect;

    /**
     * 在工资表中的显示序号
     */
    private Integer number;

    /**
     * 是否在工资条中显示
     */
    private Integer isDisplay;


}
