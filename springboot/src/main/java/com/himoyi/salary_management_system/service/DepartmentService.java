package com.himoyi.salary_management_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.DepartmentDto;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
public interface DepartmentService extends IService<Department> {

    List<String> getProvinces();

    IPage<Department> selectPage(IPage<Department> page);

    IPage<Department> selectDepartmentPage(Page<DepartmentDto> departmentDtoPagePage, DepartmentDto departmentDto);
}
