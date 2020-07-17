package com.himoyi.salary_management_system.controller;


import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.service.EmployeeService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
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

    @GetMapping("/{id}")
    @RequiresAuthentication
    public Result getEmployee(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getById(id);
        return Result.success("查询成功！", employee);
    }

//    @PostMapping("employees")
//    @RequiresAuthentication
//    public Result getEmployees(@RequestBody )
}

