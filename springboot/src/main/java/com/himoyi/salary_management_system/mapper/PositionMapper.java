package com.himoyi.salary_management_system.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.Position;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Mapper
public interface PositionMapper extends BaseMapper<Position> {

    IPage<Position> selectPage(IPage<Position> page);

    IPage<Position> selectPositionPage(Page<PositionDto> positionPage, @Param("positionDto") PositionDto positionDto);
}
