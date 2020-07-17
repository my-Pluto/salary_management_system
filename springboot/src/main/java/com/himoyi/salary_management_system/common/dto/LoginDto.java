package com.himoyi.salary_management_system.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {

    @NotBlank(message = "工号不能为空")
    private Long id;

    @NotBlank(message = "密码不能为空")
    private String password;
}
