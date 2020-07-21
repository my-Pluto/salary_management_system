package com.himoyi.salary_management_system.service.impl;

import com.himoyi.salary_management_system.pojo.Statement;
import com.himoyi.salary_management_system.mapper.StatementMapper;
import com.himoyi.salary_management_system.service.*;
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
public class StatementServiceImpl extends ServiceImpl<StatementMapper, Statement> implements StatementService {

    @Autowired
    FixedItemService fixedItemService;
    @Autowired
    FixedItemDataService fixedItemDataService;
    @Autowired
    ImportItemService importItemService;
    @Autowired
    ImportItemDataService importItemDataService;
    @Autowired
    CountItemService countItemService;

    @Override
    public void countSalary(String department) {
        
    }
}
