package com.himoyi.salary_management_system.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.himoyi.salary_management_system.pojo.User;
import com.himoyi.salary_management_system.service.UserService;
import com.himoyi.salary_management_system.util.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;
    /**
     * 判断是否是我们需要的token，如果是，则进行强转操作
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 在jwt登录时会进入该方法中，进行登录操作
     * 认证操作核心
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取token
        JwtToken token = (JwtToken) authenticationToken;

        // 获取用户id
        String userId = jwtUtils.getClaimByToken((String)token.getPrincipal()).getSubject();
        // 通过用户id查询用户信息
        User user = userService.getById(Long.valueOf(userId));

        // 校验用户信息
        if (user == null) {
            throw new UnknownAccountException("账户不存在！");
        }

        // 封装可以显示的用户信息
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);

        // 返回用户信息
        return new SimpleAuthenticationInfo(profile, token.getCredentials(), getName());
    }
}
