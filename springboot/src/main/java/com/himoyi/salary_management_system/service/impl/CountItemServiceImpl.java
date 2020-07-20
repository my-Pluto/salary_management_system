package com.himoyi.salary_management_system.service.impl;

import cn.hutool.core.collection.ListUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.CountItemDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.CountItem;
import com.himoyi.salary_management_system.mapper.CountItemMapper;
import com.himoyi.salary_management_system.pojo.FixedItem;
import com.himoyi.salary_management_system.pojo.FixedItemData;
import com.himoyi.salary_management_system.pojo.ImportItem;
import com.himoyi.salary_management_system.service.CountItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.himoyi.salary_management_system.service.FixedItemService;
import com.himoyi.salary_management_system.service.ImportItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CountItemServiceImpl extends ServiceImpl<CountItemMapper, CountItem> implements CountItemService {

    @Autowired
    CountItemMapper countItemMapper;
    @Autowired
    ImportItemService importItemService;
    @Autowired
    FixedItemService fixedItemService;

    @Override
    public IPage<CountItem> selectPage(Page<CountItem> countItemPage) {
        return countItemMapper.selectPage(countItemPage, null);
    }

    @Override
    public IPage<CountItemDto> selectCountItemPage(Page<ImportItemDto> importItemDtoPage, CountItemDto countItemDto) {
        return countItemMapper.selectCountItemPage(importItemDtoPage, countItemDto);
    }

    @Override
    public List<String> getFirstOperation() {
        List<String> list = new ArrayList<>();
        List<ImportItem> importItems = importItemService.list();
        List<FixedItem> fixedItems = fixedItemService.list();
        for (FixedItem fixedItem : fixedItems) {
            list.add(fixedItem.getName());
        }
        for (ImportItem importItem : importItems) {
            list.add(importItem.getName());
        }
        return list;
    }
}
