package com.yunche.domain.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.infra.entity.AuthRole;

import java.util.List;

public interface RoleService extends IService<AuthRole> {

    AuthRole selectByKey(String adminUser);

    List<String> selectKeyByIds(List<Long> roleIds);
}
