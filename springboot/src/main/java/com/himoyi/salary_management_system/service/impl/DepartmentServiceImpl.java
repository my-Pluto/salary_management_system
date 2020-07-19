package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.DepartmentDto;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.himoyi.salary_management_system.mapper.DepartmentMapper;
import com.himoyi.salary_management_system.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    public List<String> getProvinces() {
        return  departmentMapper.getProvinces();
    }

    @Override
    public IPage<Department> selectPage(IPage<Department> page) {
        return departmentMapper.selectPage(page, null);
    }

    @Override
    public IPage<Department> selectDepartmentPage(Page<DepartmentDto> departmentDtoPage, DepartmentDto departmentDto) {
        return departmentMapper.selectDepartmentPage(departmentDtoPage, departmentDto);
    }
}
