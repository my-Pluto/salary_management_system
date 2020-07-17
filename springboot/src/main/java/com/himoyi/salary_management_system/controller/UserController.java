package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.service.DepartmentService;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 获取所有用户信息
     *
     * @return
     */
    @GetMapping("users")
    @RequiresAuthentication
    public Result getUsers() {
        List<UserDto> users = userService.selectUsers();
        return Result.success("查询成功", users);
    }

    /**
     * 根据id查询用户数据
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @RequiresAuthentication
    public Result getUser(@PathVariable(name = "id") Long id) {
        UserDto userDto = userService.selectUserById(id);
        if (userDto == null) {
            return Result.fail("查询失败，该用户不存在！", null);
        }
        return Result.success("查询成功", userDto);
    }

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @PostMapping
    @RequiresAuthentication
    public Result addUser(@Validated @RequestBody User user) {
        User user1 = userService.getById(user.getId());
        if (user1 != null) {
            return Result.fail("添加失败，该用户已存在！", null);
        }
        userService.save(user);
        return Result.success("添加用户信息成功", null);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PostMapping("{id}")
    @RequiresAuthentication
    public Result updateUser(@Validated @RequestBody User user) {
        User user1 = userService.getById(user.getId());
        if (user1 == null) {
            return Result.fail("更新失败！该用户不存在", null);
        }
        userService.updateById(user);
        return Result.success("更新用户信息成功", null);
    }

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteUser(@PathVariable(name = "id") Long id) {
        userService.removeById(id);
        return Result.success("删除成功", null);
    }
}

