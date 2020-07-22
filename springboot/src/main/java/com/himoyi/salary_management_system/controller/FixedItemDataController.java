package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.common.dto.FixedItemDto;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.service.DepartmentService;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.FixedItemDataService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.soap.Addressing;
import java.awt.geom.RectangularShape;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
@RequestMapping("/fixeditemdata")
public class FixedItemDataController {

    @Autowired
    FixedItemDataService fixedItemDataService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @RequiresAuthentication
    public Result getFixedItemDatas() {
        List<FixedItemData> fixedItemDatas = fixedItemDataService.list();
        return Result.success("查询成功！", fixedItemDatas);
    }

    @GetMapping("/fixeditemdata/{page}/{size}")
    @RequiresAuthentication
    public Result getFixedItemData(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        Map<String, Object> list = fixedItemDataService.getData(page, size, null);
        return Result.success("查询成功！", list);
    }

    @PostMapping("/fixeditemdata/{page}/{size}")
    @RequiresAuthentication
    public Result getFixedItemDataByData(@RequestBody FixedItemDataDto fixedItemDataDto,
                                         @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        Map<String, Object> list = fixedItemDataService.getData(page, size, fixedItemDataDto);
        System.out.println(fixedItemDataDto);

        return Result.success("查询成功！", list);
    }

    @PostMapping("/fixeditemdata")
    @RequiresAuthentication
    public Result saveOrUpdateFixedItemData(@RequestBody FixedItemData fixedItemData) {
        if (fixedItemData.getEmployeeId() == null || fixedItemData.getEmployeeName() == null) {
            List<Employee> employees = employeeService.list(
                    new QueryWrapper<Employee>().eq("department", fixedItemData.getDeptId()));
            for (Employee employee : employees) {
                FixedItemData fixedItemData1 = new FixedItemData();
                BeanUtil.copyProperties(fixedItemData, fixedItemData1);
                fixedItemData1.setEmployeeId(employee.getId());
                fixedItemData1.setEmployeeName(employee.getName());

                fixedItemDataService.saveOrUpdate(fixedItemData1, new QueryWrapper<FixedItemData>()
                        .eq("dept_id", fixedItemData1.getDeptId())
                        .eq("employee_id", fixedItemData1.getEmployeeId())
                        .eq("item_id", fixedItemData1.getItemId()));
            }
        }
        return Result.success("批量修改成功！", null);
    }

    @PostMapping("/fixeditemdata/{employeeid}")
    @RequiresAuthentication
    public Result updateFixedItemDataByEmployeeId(@RequestBody String json, @PathVariable(name = "employeeid") Long id) {
        System.out.println(json);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        System.out.println(jsonObject.keySet());
        Set<String> strings = jsonObject.keySet();
        for (String string : strings) {
            Object o = jsonObject.get(string);
            System.out.println(o.toString());
            fixedItemDataService.updateByEmployeeId(BigDecimal.valueOf(Double.parseDouble(o.toString())), id, string);
        }
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("{id}")
    @RequiresAuthentication
    public Result deleteFixedItemData(@PathVariable(name = "id") Long id) {
        FixedItemData fixedItemData = fixedItemDataService.getById(id);
        if (fixedItemData == null) {
            return Result.fail("删除失败！该记录不存在！", null);
        }
        fixedItemDataService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

