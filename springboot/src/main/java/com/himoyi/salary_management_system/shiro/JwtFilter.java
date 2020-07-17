package com.himoyi.salary_management_system.shiro;

import cn.hutool.json.JSONUtil;
import com.himoyi.salary_management_system.common.Result;
import com.himoyi.salary_management_system.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 如果存在jwt，则会调用executeLogin方法，在该方法中进行登录处理
 * 登录后会委托Realm处理
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 获取jwt，如果没有jwt，则跳过，直接交由Controller注解处理
     * 如果登录/有jwt，则将jwt封装成token形式，交由shiro进行登录处理
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 从header中获取jwt信息
        String jwt = httpServletRequest.getHeader("Authorization");

        // 如果jwt不存在，即未登录，则直接返回null
        if (jwt.isEmpty()) {
            return null;
        }

        // 如果jwt存在，级已登录，则返回包装成token的jwt
        return new JwtToken(jwt);
    }

    /**
     * 拦截操作
     * 如果没问题，会进行校验操作，包括过期、错误等
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 从header中获取jwt信息
        String jwt = httpServletRequest.getHeader("Authorization");

        // 如果jwt不存在，则不需要登录拦截，返回true
        if (jwt == null || jwt.isEmpty()) {
            return true;
        } else {

            // 校验token
            Claims claims = jwtUtils.getClaimByToken(jwt);
            if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
                throw new ExpiredCredentialsException("Token已过期，请重新登录！");
            }

            // 登录
            return executeLogin(servletRequest, servletResponse);
        }
    }

    // 当登录失败时会抛出异常，为了防止前端异常出现，降低用户体验，需要对异常进行处理
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 获取错误
        Throwable throwable = e.getCause() == null ? e : e.getCause();

        // 生成错误结果集
        Result result = Result.fail(throwable.getMessage());

        // 生成JSON串
        String json = JSONUtil.toJsonStr(request);

        try {
            // 返回JSON
            httpServletResponse.getWriter().print(json);
        } catch (IOException ignored) {
        }

        return false;
    }
}
