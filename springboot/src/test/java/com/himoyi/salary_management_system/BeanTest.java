package com.himoyi.salary_management_system;

import cn.hutool.core.bean.BeanUtil;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class BeanTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void beanTest() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setSex("男");

        Map<String, Object> map = BeanUtil.beanToMap(employeeDto, false, true);


        System.out.println(employeeService.listByMap(map));
    }
}
