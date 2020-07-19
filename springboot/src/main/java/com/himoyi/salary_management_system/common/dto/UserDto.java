package com.himoyi.salary_management_system.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String department;
    private Date gmtCreate;
}
