package com.himoyi.salary_management_system.common.dto;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;


@Data
public class FixedItemDto {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 固定项目名称
     */
    private String name;

    /**
     * 增减项，表明该项目是增加、减少、还是无影响
     */
    private Integer effect;

    /**
     * 表明该项目在生成工资条中的序号
     */
    private Integer number;

    /**
     * 是否显示
     */
    private Integer isDisplay;
}
