package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.ImportItem;
import com.himoyi.salary_management_system.mapper.ImportItemMapper;
import com.himoyi.salary_management_system.service.ImportItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class ImportItemServiceImpl extends ServiceImpl<ImportItemMapper, ImportItem> implements ImportItemService {

    @Autowired
    ImportItemMapper importItemMapper;

    @Override
    public IPage<ImportItem> selectPage(Page<ImportItem> importItemPage) {
        return importItemMapper.selectPage(importItemPage, null);
    }

    @Override
    public IPage<ImportItemDto> selectImportItemPage(Page<ImportItemDto> importItemDtoPage, ImportItemDto importItemDto) {
        return importItemMapper.selectImportItemPage(importItemDtoPage, importItemDto);
    }
}
