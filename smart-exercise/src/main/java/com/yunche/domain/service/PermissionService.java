package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.AuthPermission;

import java.util.List;

public interface PermissionService extends IService<AuthPermission> {

    List<String> selectKeyByIds(List<Long> permissionIds);

}
