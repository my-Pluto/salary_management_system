package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.PositionDto;
import com.himoyi.salary_management_system.pojo.Position;
import com.himoyi.salary_management_system.mapper.PositionMapper;
import com.himoyi.salary_management_system.service.PositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 岗位服务
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements PositionService {

    @Autowired
    PositionMapper positionMapper;

    /**
     * 分页
     * @param page
     * @return
     */
    @Override
    public IPage<Position> selectPage(IPage<Position> page) {
        return positionMapper.selectPage(page, null);
    }

    /**
     * 分页查询
     * @param positionPage
     * @param positionDto
     * @return
     */
    @Override
    public IPage<Position> selectPositionPage(Page<PositionDto> positionPage, PositionDto positionDto) {
        return positionMapper.selectPositionPage(positionPage, positionDto);
    }
}
