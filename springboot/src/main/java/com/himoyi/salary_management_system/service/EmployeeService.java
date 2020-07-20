package com.himoyi.salary_management_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.EmployeeDto;
import com.himoyi.salary_management_system.common.dto.EmployeesDto;
import com.himoyi.salary_management_system.pojo.Employee;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
public interface EmployeeService extends IService<Employee> {
    Page<Employee> selectPage(Page<Employee> page);
    IPage<EmployeesDto> selectEmployeePage(Page<EmployeeDto> page, EmployeeDto employeeDto);
}
