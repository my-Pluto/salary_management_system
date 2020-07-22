package com.himoyi.salary_management_system.controller;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.common.dto.StatementDto;
import com.himoyi.salary_management_system.pojo.Statement;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import com.himoyi.salary_management_system.service.StatementService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
@RequestMapping("/statement")
public class StatementController {
    @Autowired
    StatementService statementService;
    @Autowired
    ImportItemDataService importItemDataService;

    @GetMapping("/count/{departmentId}/{month}")
    @RequiresAuthentication
    public Result countSalaryByDepartmetId(@PathVariable(name = "departmentId") Long departmentId,
                                           @PathVariable(name = "month") String month) {
        Statement statement = statementService.list(new QueryWrapper<Statement>()
                .eq("department_id", departmentId)
                .eq("month", month)).get(0);
        if (statement != null) {
            return Result.fail("计算失败！该部门已计算！", null);
        }
        statementService.countSalary(departmentId, month);
        return Result.success("计算成功！已暂存数据库！", null);
    }

    @GetMapping("/salary/provided/{departmentId}/{month}")
    @RequiresAuthentication
    public Result salaryProvided(@PathVariable(name = "departmentId") Long departmentId,
                                 @PathVariable(name = "month") String month) {
        statementService.salaryProvided(departmentId, month);
        return Result.success("工资已发放！", null);
    }

    @PostMapping("/count/{employeeid}/{month}")
    @RequiresAuthentication
    public Result updateStatementByEmployeeId(@RequestBody String json,
                                              @PathVariable(name = "employeeid") Long employee_id,
                                              @PathVariable(name = "month") String month) {
        Statement statement = statementService.list(new QueryWrapper<Statement>()
                .eq("employee_id", employee_id)
                .eq("month", month)).get(0);
        if (statement.getIsProvided() == 1) {
            return Result.fail("更新失败！该用户已发放！", null);
        }
        JSONObject jsonObject = JSONUtil.parseObj(json);
        System.out.println(jsonObject.keySet());
        Set<String> strings = jsonObject.keySet();
        for (String string : strings) {
            Object o = jsonObject.get(string);
            System.out.println(o.toString());
            statementService.updateByEmployeeId(BigDecimal.valueOf(Double.parseDouble(o.toString())), employee_id, month, string);
        }
        return Result.success("更新成功！", null);
    }

    @DeleteMapping("/count/{departmentId}/{month}")
    @RequiresAuthentication
    public Result deleteCache(@PathVariable(name = "departmentId") Long departmentId,
                              @PathVariable(name = "month") String month) {
        Statement statement = statementService.list(new QueryWrapper<Statement>()
                .eq("department_id", departmentId)
                .eq("month", month)).get(0);
        if (statement.getIsProvided() == 1) {
            return Result.fail("该部门已发放！不允许清除暂存！", null);
        }
        statementService.remove(new QueryWrapper<Statement>()
                .eq("department_id", departmentId)
                .eq("month", month));

        return Result.fail("清除成功！", null);
    }

    @PostMapping("/salary/{page}/{size}")
    @RequiresAuthentication
    public Result getData(@RequestBody StatementDto statementDto,
                          @PathVariable(name = "page") Integer page,
                          @PathVariable(name = "size") Integer size) {
        Map<String, Object> list = statementService.getData(page, size, statementDto);
        return Result.success("查询成功！", list);
    }
}

