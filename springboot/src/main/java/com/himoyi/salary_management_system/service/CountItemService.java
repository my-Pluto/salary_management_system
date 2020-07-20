package com.himoyi.salary_management_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.CountItemDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.CountItem;
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
public interface CountItemService extends IService<CountItem> {

    List<String> getFirstOperation();

    IPage<CountItem> selectPage(Page<CountItem> countItemPage);

    IPage<CountItemDto> selectCountItemPage(Page<ImportItemDto> importItemDtoPage, CountItemDto countItemDto);
}
