package com.himoyi.salary_management_system.service.impl;

import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.mapper.UserMapper;
import com.himoyi.salary_management_system.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
