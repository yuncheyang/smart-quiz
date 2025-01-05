package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.RolePermissionService;
import com.yunche.infra.entity.AuthRolePermission;
import com.yunche.infra.mapper.AuthRolePermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl  extends ServiceImpl<AuthRolePermissionMapper, AuthRolePermission> implements RolePermissionService {

    @Override
    public List<Long> selectByRoleId(Long roleId) {
        LambdaQueryWrapper<AuthRolePermission> select = Wrappers.<AuthRolePermission>lambdaQuery()
                .eq(AuthRolePermission::getRoleId, roleId)
                .select(AuthRolePermission::getPermissionId);
        List<AuthRolePermission> list = list(select);

       return list.stream().map(AuthRolePermission::getPermissionId).toList();

    }
}
