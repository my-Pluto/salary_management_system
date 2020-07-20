package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.mapper.FixedItemDataMapper;
import com.himoyi.salary_management_system.service.FixedItemDataService;
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
public class FixedItemDataServiceImpl extends ServiceImpl<FixedItemDataMapper, FixedItemData> implements FixedItemDataService {

    @Autowired
    FixedItemDataMapper fixedItemDataMapper;
    @Override
    public IPage<FixedItemData> selectPage(Page<FixedItemData> fixedItemDataPage) {
        return fixedItemDataMapper.selectPage(fixedItemDataPage, null);
    }

    @Override
    public IPage<FixedItemData> selectFixedItemPage(Page<FixedItemDataDto> fixedItemDataDtoPage, FixedItemDataDto fixedItemDataDto) {
        return fixedItemDataMapper.selectFixedItemPage(fixedItemDataDtoPage, fixedItemDataDto);
    }

}
