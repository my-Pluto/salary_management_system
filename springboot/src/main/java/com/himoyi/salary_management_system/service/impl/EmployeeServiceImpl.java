package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.EmployeesDto;
import com.himoyi.salary_management_system.pojo.Employee;
import com.himoyi.salary_management_system.mapper.EmployeeMapper;
import com.himoyi.salary_management_system.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public Page<Employee> selectPage(Page<Employee> page) {
        return employeeMapper.selectPage(page, null);
    }

    @Override
    public IPage<EmployeesDto> selectEmployeePage(Page<EmployeeDto> page, EmployeeDto employeeDto) {
        return employeeMapper.selectEmployeePage(page, employeeDto);
    }
}
