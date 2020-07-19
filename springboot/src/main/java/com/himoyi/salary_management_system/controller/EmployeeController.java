package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.service.EmployeeService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@CrossOrigin
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    @RequiresAuthentication
    public Result getEmployees() {
        List<Employee> employees = employeeService.list();
        return Result.success("查询成功！", employees);
    }

    @GetMapping("/employees/{page}/{size}")
    @RequiresAuthentication
    public Result getUsersPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        Page<Employee> employeePage = new Page<>(page, size);
        Page<Employee> employees = employeeService.selectPage(employeePage);
        return Result.success("查询成功！", employeePage);
    }

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Result getEmployee(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success("查询成功！", employee);
    }

    @PostMapping("employees")
    @RequiresAuthentication
    public Result getEmployees(@RequestBody EmployeeDto employeeDto) {
        Map<String, Object> map = BeanUtil.beanToMap(employeeDto, false, true);
        List<Employee> departments = employeeService.listByMap(map);
        return Result.success("查询成功！", departments);
    }
}

