package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.AuthRolePermission;

import java.util.List;

public interface RolePermissionService extends IService<AuthRolePermission> {

    List<Long> selectByRoleId(Long roleId);

}
