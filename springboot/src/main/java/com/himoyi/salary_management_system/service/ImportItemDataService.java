package com.himoyi.salary_management_system.service;

import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
public interface ImportItemDataService extends IService<ImportItemData> {

    List<Object> getData(Integer page, Integer size, ImportItemDataDto importItemDataDto);

    void updateByEmployeeId(Object object, Long id, String name, String month);

    String getLastMonth();
}
