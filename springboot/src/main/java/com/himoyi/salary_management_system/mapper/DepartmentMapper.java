package com.himoyi.salary_management_system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.DepartmentDto;
import com.himoyi.salary_management_system.pojo.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<Department> {

    @Select("SELECT province FROM department GROUP BY province")
    List<String> getProvinces();

    IPage<Department> selectDepartmentPage(Page<DepartmentDto> departmentDtoPage, @Param("departmentDto") DepartmentDto departmentDto);
}
