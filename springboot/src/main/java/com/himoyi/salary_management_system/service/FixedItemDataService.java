package com.himoyi.salary_management_system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.FixedItemDataDto;
import com.himoyi.salary_management_system.pojo.FixedItemData;
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
public interface FixedItemDataService extends IService<FixedItemData> {

    IPage<FixedItemData> selectPage(Page<FixedItemData> fixedItemDataPage);

    IPage<FixedItemData> selectFixedItemPage(Page<FixedItemDataDto> fixedItemDataPage, FixedItemDataDto fixedItemDataDto);

    List<Object> getData(Integer page, Integer size, FixedItemDataDto fixedItemDataDto);

    void updateByEmployeeId(Object object, Long id, String name);
}
