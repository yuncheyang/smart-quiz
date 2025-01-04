package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yunche.domain.dto.LoginUser;
import com.yunche.infra.entity.AuthUser;
import com.yunche.infra.mapper.AuthUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AuthUserMapper authUserMapper;

    /**
     * security核心认证方法
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<AuthUser> eq = Wrappers.<AuthUser>lambdaQuery()
                .eq(AuthUser::getUserName, username);
        AuthUser authUser = authUserMapper.selectOne(eq);
        if (Objects.isNull(authUser)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息

        //把数据封装成UserDetails返回
        return new LoginUser(authUser);
    }
}
