package com.himoyi.salary_management_system.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 登录返回信息
 */
@Data
public class AccountProfile implements Serializable {
    private Integer id;

    private String name;

    private String email;

    private LocalDateTime creared;

}
