package com.himoyi.salary_management_system;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.mapper.FixedItemDataMapper;
import com.himoyi.salary_management_system.mapper.FixedItemMapper;
import com.himoyi.salary_management_system.mapper.StatementMapper;
import com.himoyi.salary_management_system.pojo.*;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.FixedItemDataService;
import com.himoyi.salary_management_system.service.FixedItemService;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BeanTest {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    FixedItemDataMapper fixedItemDataMapper;

    @Autowired
    ImportItemDataService importItemDataService;

    @Autowired
    FixedItemDataService fixedItemDataService;

    @Autowired
    FixedItemService fixedItemService;

    @Autowired
    StatementMapper statementMapper;
    @Test
    public void beanTest() {
        Employee employee = new Employee();

        employee.setName("李四");
        employee.setSex("男");
        employee.setDepartment(Long.valueOf("1"));
        employee.setPosition(Long.valueOf("1"));
        employee.setEmail("2698391541@qq.com");
        employee.setEntryTime(new Date());

        System.out.println(employee);

        employeeService.save(employee);
    }

    @Test
    public void sqlTest() {
        System.out.println(fixedItemDataService.getData(1, 5, null));
    }

    @Test
    public void dateTest() {
        ImportItemData importItemDataDto = new ImportItemData();
        importItemDataDto.setMonth(importItemDataService.getLastMonth());
        System.out.println(importItemDataDto);
    }

    @Test
    public void sortTest() {
        List<FixedItem> list = fixedItemService.list();
        for (FixedItem fixedItem : list) {
            System.out.println(fixedItem.getName());
        }
        System.out.println("=======================");
        list = fixedItemService.getName();
        for (FixedItem fixedItem : list) {
            System.out.println(fixedItem.getName());
        }
    }

    @Test
    public void bTest() {
        Long id = Long.valueOf(10000001);

        System.out.println(statementMapper.selectList(new QueryWrapper<Statement>().eq("employee_id", id)));
    }
}
