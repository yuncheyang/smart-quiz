package com.yunche.domain.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yunche.application.conreoller.po.UserDoLoginPO;
import com.yunche.infra.entity.AuthUser;

import java.util.Map;

public interface UserService extends IService<AuthUser> {

    Map<String, String> doLogin(UserDoLoginPO userDoLoginPO);

    Boolean register(UserDoLoginPO userDoLoginPO);

    Boolean outLogin();
}
