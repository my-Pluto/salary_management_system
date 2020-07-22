package com.himoyi.salary_management_system.mapper;

import com.himoyi.salary_management_system.common.dto.ImportItemDataDto;
import com.himoyi.salary_management_system.common.dto.ImportItemDto;
import com.himoyi.salary_management_system.pojo.ImportItemData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
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
public interface ImportItemDataMapper extends BaseMapper<ImportItemData> {

    List<String> getName(@Param("importItemDataDto") ImportItemDataDto importItemDataDto);

    List<Long> getEmployee_id(@Param("importItemDataDto") ImportItemDataDto importItemDataDto);

    @Select("SELECT data FROM import_item_data WHERE employee_id = #{id} AND item_name = #{name} AND month >= #{importItemDataDto.lowMonth} AND month <= #{importItemDataDto.highMonth}")
    BigDecimal getSalary(@Param("id") Long id, @Param("name") String name,
                         @Param("importItemDataDto") ImportItemDataDto importItemDataDto);

    @Select("UPDATE import_item_data SET data = #{data} WHERE employee_id = #{id} AND item_name=#{name} AND month = '#{month}'")
    void updateByEmployeeId(@Param("data") Object object, @Param("id") Long id,
                            @Param("name") String name, @Param("month") String month);
}
