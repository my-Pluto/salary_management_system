package com.himoyi.salary_management_system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.himoyi.salary_management_system.common.dto.UserDto;
import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.mapper.UserMapper;
import com.himoyi.salary_management_system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * 用户管理
 *
 * @author 张玉飞 陈辰 刘月锟 宫雅琦 邵景宇
 * @since 2020-07-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<UserDto> selectUsers() {
        return userMapper.selectUsers();
    }

    @Override
    public UserDto selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public IPage<UserDto> selectPage(IPage<UserDto> page) {
        return userMapper.selectPage(page);
    }
}
