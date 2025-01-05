package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.UserRoleService;
import com.yunche.infra.entity.AuthUserRole;
import com.yunche.infra.mapper.AuthUserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl extends ServiceImpl<AuthUserRoleMapper, AuthUserRole> implements UserRoleService {

    @Override
    public Boolean saveUserRole(Long userId, Long roleId) {
        AuthUserRole authUserRole = new AuthUserRole();
        authUserRole.setUserId(userId);
        authUserRole.setRoleId(roleId);
        return save(authUserRole);
    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        LambdaQueryWrapper<AuthUserRole> select = Wrappers.<AuthUserRole>lambdaQuery()
                .eq(AuthUserRole::getUserId, userId)
                .select(AuthUserRole::getRoleId);
        List<AuthUserRole> list = list(select);
        return list.stream().map(AuthUserRole::getRoleId).toList();
    }
}
