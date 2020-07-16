package com.himoyi.salary_management_system.shiro;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountProfile {
    private Integer id;

    private String username;

    private String avatar;

    private String email;

    private LocalDateTime creared;

}
