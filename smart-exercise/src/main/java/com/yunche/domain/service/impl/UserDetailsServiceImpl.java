package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yunche.common.exception.UserAuthException;
import com.yunche.common.exception.UserLoginException;
import com.yunche.common.utils.RedisCache;
import com.yunche.domain.dto.LoginUser;
import com.yunche.domain.dto.UserRolePermission;
import com.yunche.infra.entity.AuthUser;
import com.yunche.infra.mapper.AuthUserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AuthUserMapper authUserMapper;

    @Resource
    private RedisCache redisCache;

    private static final String LOGIN_USER = "loginUser";

    /**
     * security核心认证方法
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<AuthUser> eq = Wrappers.<AuthUser>lambdaQuery()
                .eq(AuthUser::getUserName, username);
        AuthUser authUser = authUserMapper.selectOne(eq);
        if (Objects.isNull(authUser)) {
            throw new UserLoginException("没有该用户信息，请注册");
        }

        //TODO (授权，即查询用户具有哪些权限)查询对应的用户信息
        Long userId = authUser.getId();
        String buildKey = redisCache.buildKey(LOGIN_USER, userId.toString());
        UserRolePermission userRolePermission = redisCache.getCacheObject(buildKey);
        if (Objects.isNull(userRolePermission)) {
            throw new UserAuthException("用户无授权信息");
        }
        List<String> roles = userRolePermission.getRoleMap().get("Roles");
        List<String> permissions = userRolePermission.getPermissionMap().get("Permissions");
        // 创建一个新的集合，将 roles 和 permissions 合并到一起
        List<String> allAuthorities = new ArrayList<>();
        allAuthorities.addAll(roles);        // 将 roles 中的元素添加到 allAuthorities 中
        allAuthorities.addAll(permissions);  // 将 permissions 中的元素添加到 allAuthorities 中

        //把数据封装成UserDetails返回
        return new LoginUser(authUser,allAuthorities);
    }
}
