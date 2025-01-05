package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.AuthUserRole;

import java.util.List;

public interface UserRoleService extends IService<AuthUserRole> {

    Boolean saveUserRole(Long userId, Long roleId);

    List<Long> selectRoleIdByUserId(Long userId);
}
