package com.himoyi.salary_management_system.service;

import com.himoyi.salary_management_system.common.dto.StatementDto;
import com.himoyi.salary_management_system.pojo.Statement;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
public interface StatementService extends IService<Statement> {

    void countSalary(Long department, String month);

    void salaryProvided(Long department, String month);

    void updateByEmployeeId(BigDecimal valueOf, Long employee_id, String month, String name);

    List<Object> getData(Integer page, Integer size, StatementDto statementDto);
}
