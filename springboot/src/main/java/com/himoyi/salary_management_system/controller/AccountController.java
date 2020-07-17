package com.himoyi.salary_management_system.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.LoginDto;
import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.service.UserService;
import com.himoyi.salary_management_system.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        User user = userService.getOne(new QueryWrapper<User>().eq("id", loginDto.getId()));

        if (user == null) {
            return Result.fail("该账号不存在！", null);
        }

        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误！", null);
        }

        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("name", user.getName())
                .put("gmtcreate", user.getGmtCreate())
                .map());
    }

    @GetMapping("/loginout")
    public Result loginOut() {
        SecurityUtils.getSubject().logout();
        return Result.success("退出成功！", null);
    }
}
