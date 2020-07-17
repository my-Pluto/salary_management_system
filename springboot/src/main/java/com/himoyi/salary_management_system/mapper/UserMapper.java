package com.himoyi.salary_management_system.mapper;

import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

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
public interface UserMapper extends BaseMapper<User> {
    List<UserDto> selectUsers();

    UserDto selectUserById(@Param("id") Long id);
}
