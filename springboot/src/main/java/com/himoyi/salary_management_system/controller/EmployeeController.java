package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.EmployeesDto;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.service.DepartmentService;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.PositionService;
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
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;

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
        EmployeesDto employeesDto = new EmployeesDto();
        employeesDto.setId(employee.getId());
        employeesDto.setName(employee.getName());
        employeesDto.setSex(employee.getSex());
        employeesDto.setEmail(employee.getEmail());
        employeesDto.setEntryTime(employee.getEntryTime());
        employeesDto.setDepartment(departmentService.getById(employee.getDepartment()).getName());
        employeesDto.setPosition(positionService.getById(employee.getPosition()).getName());
        return Result.success("查询成功！", employeesDto);
    }

    @PostMapping("employees")
    @RequiresAuthentication
    public Result getEmployees(@RequestBody EmployeeDto employeeDto) {
        Map<String, Object> map = BeanUtil.beanToMap(employeeDto, false, true);
        System.out.println(map);
        List<Employee> departments = employeeService.listByMap(map);
        return Result.success("查询成功！", departments);
    }

    @PostMapping("employees/{page}/{size}")
    @RequiresAuthentication
    public Result getEmployees(@RequestBody EmployeeDto employeeDto,
                               @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<EmployeesDto> employeesDtoIPage = employeeService.selectEmployeePage(new Page<EmployeeDto>(page, size), employeeDto);
        return Result.success("查询成功！", employeesDtoIPage);
    }

    @PostMapping
    @RequiresAuthentication
    public Result addEmployee(@Validated Employee employee) {
        employeeService.save(employee);
        return Result.success("添加员工成功！", null);
    }

    @PostMapping("/{id}")
    @RequiresAuthentication
    public Result updateEmployee(@Validated Employee employee) {
        Employee employee1 = employeeService.getById(employee.getId());
        if (employee1 == null) {
            return Result.fail("更新失败！该员工不存在！", null);
        }
        employeeService.saveOrUpdate(employee);
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("/{id}")
    @RequiresAuthentication
    public Result deleteEmployee(@PathVariable(name = "id") Long id) {
        Employee employee = employeeService.getById(id);
        if (employee == null) {
            return Result.fail("删除失败！该用户不存在！", null);
        }
        employeeService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

