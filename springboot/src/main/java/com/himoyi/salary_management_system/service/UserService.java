package com.himoyi.salary_management_system.service;

import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.User;
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
public interface UserService extends IService<User> {

    public List<UserDto> selectUsers();

    public UserDto selectUserById(Long id);
}
