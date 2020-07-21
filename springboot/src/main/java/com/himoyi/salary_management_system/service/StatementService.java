package com.himoyi.salary_management_system.service;

import com.himoyi.salary_management_system.pojo.Statement;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
public interface StatementService extends IService<Statement> {

    void countSalary(String department);

}
