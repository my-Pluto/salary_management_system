package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.CountItem;
import com.himoyi.salary_management_system.pojo.ImportItem;
import com.himoyi.salary_management_system.mapper.ImportItemMapper;
import com.himoyi.salary_management_system.service.ImportItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

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

    /**
     * 分页
     * @param importItemPage
     * @return
     */
    @Override
    public IPage<ImportItem> selectPage(Page<ImportItem> importItemPage) {
        return importItemMapper.selectPage(importItemPage, null);
    }

    /**
     * 分页查询
     * @param importItemDtoPage
     * @param importItemDto
     * @return
     */
    @Override
    public IPage<ImportItemDto> selectImportItemPage(Page<ImportItemDto> importItemDtoPage, ImportItemDto importItemDto) {
        return importItemMapper.selectImportItemPage(importItemDtoPage, importItemDto);
    }

    /**
     * 获取名称
     * @return
     */
    @Override
    public List<ImportItem> getName() {
        List<ImportItem> countItems = importItemMapper.selectList(null);
        countItems.sort(Comparator.comparing(ImportItem::getNumber));
        return countItems;
    }
}
