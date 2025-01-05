package com.yunche.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yunche.domain.service.PermissionService;
import com.yunche.infra.entity.AuthPermission;
import com.yunche.infra.mapper.AuthPermissionMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<AuthPermissionMapper, AuthPermission> implements PermissionService {
    @Override
    public List<String> selectKeyByIds(List<Long> permissionIds) {
        LambdaQueryWrapper<AuthPermission> select = Wrappers.<AuthPermission>lambdaQuery()
                .in(AuthPermission::getId, permissionIds)
                .select(AuthPermission::getPermissionKey);
       return list(select).stream().map(AuthPermission::getPermissionKey).toList();

    }
}
