package com.himoyi.salary_management_system.mapper;

import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.common.dto.StatementDto;
import com.himoyi.salary_management_system.pojo.Statement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Mapper
public interface StatementMapper extends BaseMapper<Statement> {

    @Select("UPDATE statement SET is_provided = 1 WHERE department_id = #{department} AND month = #{month}")
    void salaryProvided(@Param("department") Long department, @Param("month") String month);

    @Select("UPDATE statement SET data = #{data} WHERE month = #{month} AND name = #{name} AND employee_id = #{employee_id}")
    void updateByEmployeeId(@Param("data") BigDecimal valueOf,
                            @Param("employee_id") Long employee_id,
                            @Param("month") String string,
                            @Param("name") String name);

    List<Long> getEmployee_id(@Param("statementDto") StatementDto statementDto);


    @Select("SELECT data FROM statement WHERE employee_id = #{employee_id} AND name = #{name} AND month = #{statementDto.month}")
    BigDecimal getData(@Param("employee_id") Long id, @Param("name") String name, @Param("statementDto") StatementDto statementDto);
}
