package com.himoyi.salary_management_system.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
@RequestMapping("/importitemdata")
public class ImportItemDataController {

    @Autowired
    ImportItemDataService importItemDataService;

    @Autowired
    EmployeeService employeeService;


    @GetMapping
    @RequiresAuthentication
    public Result getimportItemDatas() {
        List<ImportItemData> importItemData = importItemDataService.list();
        return Result.success("查询成功！", importItemData);
    }

    @GetMapping("/importitemdata/{page}/{size}")
    @RequiresAuthentication
    public Result getimportItemData(@PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size){
        Map<String, Object> list = importItemDataService.getData(page, size, null);
        return Result.success("查询成功！", list);
    }

    @PostMapping("/importitemdata/{page}/{size}")
    @RequiresAuthentication
    public Result getimportItemDataByData(@RequestBody ImportItemDataDto importItemDataDto,
                                         @PathVariable(name = "page") Integer page, @PathVariable(name = "size") Integer size) {
        System.out.println(importItemDataDto);
        Map<String, Object> list = importItemDataService.getData(page, size, importItemDataDto);

        return Result.success("查询成功！", list);
    }

    @PostMapping("/importitemdata")
    @RequiresAuthentication
    public Result saveOrUpdateImportItemData(@RequestBody ImportItemData importItemData) {
        if (importItemData.getEmployeeId() == null || importItemData.getEmployeeName() == null) {
            List<Employee> employees = employeeService.list(
                    new QueryWrapper<Employee>().eq("department", importItemData.getDeptId()));
            for (Employee employee : employees) {
                ImportItemData importItemData1 = new ImportItemData();
                BeanUtil.copyProperties(importItemData, importItemData1);
                importItemData1.setEmployeeId(employee.getId());
                importItemData1.setEmployeeName(employee.getName());
                importItemData1.setMonth(importItemDataService.getLastMonth());
                importItemDataService.saveOrUpdate(importItemData1);
            }
        }
        return Result.success("批量修改成功！",null);
    }

    @PostMapping("/importitemdata/{employeeid}")
    @RequiresAuthentication
    public Result updateImportItemDataByEmployeeId(@RequestBody String json, @PathVariable(name = "employeeid") Long id) {
        System.out.println(json);
        JSONObject jsonObject = JSONUtil.parseObj(json);
        System.out.println(jsonObject.keySet());
        Set<String> strings = jsonObject.keySet();
        for (String string : strings) {
            Object o = jsonObject.get(string);
            System.out.println(o.toString());
            importItemDataService.updateByEmployeeId(BigDecimal.valueOf(Double.parseDouble(o.toString())), id, string, importItemDataService.getLastMonth());
        }
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("/{id}/{month}")
    @RequiresAuthentication
    public Result deleteFixedItemData(@PathVariable(name = "id") Long id, @PathVariable(name = "month") Date date) {
        ImportItemData importItemData =importItemDataService.getById(id);
        if (importItemData == null) {
            return Result.fail("删除失败！该记录不存在！", null);
        }
        importItemDataService.removeById(id);
        return Result.success("删除成功！", null);
    }
}

