package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.FixedItemDto;
import com.himoyi.salary_management_system.pojo.FixedItem;
import com.himoyi.salary_management_system.mapper.FixedItemMapper;
import com.himoyi.salary_management_system.service.FixedItemService;
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
public class FixedItemServiceImpl extends ServiceImpl<FixedItemMapper, FixedItem> implements FixedItemService {

    @Autowired
    FixedItemMapper fixedItemMapper;
    @Override
    public IPage<FixedItem> selectPage(IPage<FixedItem> page) {
        return fixedItemMapper.selectPage(page, null);
    }

    @Override
    public IPage<FixedItemDto> selectFixedItemPage(Page<FixedItemDto> fixedItemDtoPage, FixedItemDto fixedItemDto) {
        return fixedItemMapper.selectFixedItemPage(fixedItemDtoPage, fixedItemDto);
    }
}
