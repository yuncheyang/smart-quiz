package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.RoleService;
import com.yunche.infra.entity.AuthRole;
import com.yunche.infra.mapper.AuthRoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRole> implements RoleService {


    @Override
    public AuthRole selectByKey(String adminUser) {
        LambdaQueryWrapper<AuthRole> select = Wrappers.<AuthRole>lambdaQuery()
                .eq(AuthRole::getRoleKey, adminUser)
                .select(AuthRole::getId);
        AuthRole authRole = getOne(select);
        return authRole;
    }

    @Override
    public List<String> selectKeyByIds(List<Long> roleIds) {
        LambdaQueryWrapper<AuthRole> select = Wrappers.<AuthRole>lambdaQuery()
                .in(AuthRole::getId, roleIds)
                .select(AuthRole::getRoleKey);
       return list(select).stream().map(AuthRole::getRoleKey).toList();
    }
}
