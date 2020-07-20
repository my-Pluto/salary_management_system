package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.DepartmentDto;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.pojo.Position;
import com.himoyi.salary_management_system.service.DepartmentService;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.PositionService;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@RestController
@CrossOrigin
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;
    @Autowired
    PositionService positionService;
    @Autowired
    EmployeeService employeeService;

    /**
     * 查询所有部门
     * @return
     */
    @GetMapping("/departments")
    @RequiresAuthentication
    public Result getDepartments() {
        List<Department> departments = departmentService.list();
        return Result.success("查询成功！", departments);
    }

    @GetMapping("/departments/{page}/{size}")
    @RequiresAuthentication
    public Result getUsersPage(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        Page<Department>departmentPage = new Page<>(page, size);
        IPage<Department> departments = departmentService.selectPage(departmentPage);
        return Result.success("查询成功！", departments);
    }

    @GetMapping("/departments/provinces")
    @RequiresAuthentication
    public Result getProvinces() {
        List<String> provinces = departmentService.getProvinces();
        List<Map<String, String>> list = new ArrayList<>();
        for (String province : provinces) {
            Map<String, String> map = MapUtil.newHashMap();
            map.put("value", province);
            map.put("label", province);
            list.add(map);
        }
        return Result.success("查询成功！", list);
    }
    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @RequiresAuthentication
    public Result getDepartmentById(@PathVariable String id) {
        Department department = departmentService.getById(id);
        if (department == null) {
            return Result.fail("该部门不存在！", null);
        }
        return Result.success("查询成功！", department);
    }

    /**
     * 模糊查询部门信息
     * @param departmentDto
     * @return
     */
    @PostMapping("/department")
    @RequiresAuthentication
    public Result getDepartment(@RequestBody DepartmentDto departmentDto) {
        Map<String, Object> map = BeanUtil.beanToMap(departmentDto, false, true);
        List<Department> departments = departmentService.listByMap(map);
        return Result.success("查询成功！", departments);
    }

    @PostMapping("/department/{page}/{size}")
    @RequiresAuthentication
    public Result getPositionsPage(@RequestBody DepartmentDto departmentDto,
                                   @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        IPage<Department> positions =departmentService.selectDepartmentPage(new Page<DepartmentDto>(page, size), departmentDto);
        return Result.success("查询成功！", positions);
    }

    /**
     * 添加部门信息
     * @param department
     * @return
     */
    @PostMapping
    @RequiresAuthentication
    public Result addDepartment(@Validated @RequestBody Department department) {
        Department department1 = departmentService.getOne(new QueryWrapper<Department>()
                .eq("name", department.getName())
                .eq("province", department.getProvince()));
        if (department1 != null) {
            return Result.fail("添加失败！该部门已存在");
        }
        departmentService.save(department);
        return Result.success("添加部门成功！", null);
    }

    /**
     * 修改部门信息
     * @param department
     * @return
     */
    @PostMapping("{id}")
    @RequiresAuthentication
    public Result updateDepartment(@Validated @RequestBody Department department) {
        Department department1 = departmentService.getById(department.getId());
        if (department1 == null) {
            return Result.fail("更新失败！没有该部门！", null);
        }

        departmentService.updateById(department);
        return Result.success("更新成功！", null);
    }

    /**
     * 删除部门信息
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteDepartment(@PathVariable(name = "id") Long id) {
        employeeService.remove(new QueryWrapper<Employee>().eq("department", id));
        Department department = departmentService.getById(id);
        positionService.remove(new QueryWrapper<Position>().eq("department", department.getName()));
        departmentService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

