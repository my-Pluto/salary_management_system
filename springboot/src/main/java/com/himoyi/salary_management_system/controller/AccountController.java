package com.himoyi.salary_management_system.controller;

import cn.hutool.core.map.MapUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.LoginDto;
import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.service.UserService;
import com.himoyi.salary_management_system.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 账号控制器
 */

@RestController
@RequestMapping
//后端跨域解决
@CrossOrigin
public class AccountController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    /**
     * 登录API
     * @param loginDto
     * @param response
     * @return
     */
    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        // 查询用户
        User user = userService.getOne(new QueryWrapper<User>().eq("id", loginDto.getId()));

        // 验证登录信息
        if (user == null) {
            return Result.fail("该账号不存在！", null);
        }

        if (!user.getPassword().equals(loginDto.getPassword())) {
            return Result.fail("密码错误！", null);
        }

        // 生成token
        String jwt = jwtUtils.generateToken(user.getId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        // 返回结果
        return Result.success(MapUtil.builder()
                .put("id", user.getId())
                .put("name", user.getName())
                .put("gmtcreate", user.getGmtCreate())
                .map());
    }

    /**
     * 退出结果
     * @return
     */
    @GetMapping("/loginout")
    public Result loginOut() {
        SecurityUtils.getSubject().logout();
        return Result.success("退出成功！", null);
    }
}
