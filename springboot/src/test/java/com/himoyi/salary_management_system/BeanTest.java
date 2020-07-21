package com.himoyi.salary_management_system;

import cn.hutool.core.bean.BeanUtil;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.mapper.FixedItemDataMapper;
import com.himoyi.salary_management_system.mapper.FixedItemMapper;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.himoyi.salary_management_system.service.FixedItemDataService;
import com.himoyi.salary_management_system.service.ImportItemDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
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
}
