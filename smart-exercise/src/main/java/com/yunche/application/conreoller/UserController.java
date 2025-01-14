package com.yunche.application.conreoller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.yunche.application.conreoller.po.UserDoLoginPO;
import com.yunche.common.entity.Result;
import com.yunche.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user")
@Tag(name = "用户模块")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/doLogin")
    @Operation(summary = "登陆接口")
    public Result<Map<String,String>> doLogin(@RequestBody UserDoLoginPO userDoLoginPO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.doLogin.po:{}", JSON.toJSONString(userDoLoginPO));
            }
            Preconditions.checkNotNull(userDoLoginPO.getUserName(),"用户名不能为空");
            Preconditions.checkNotNull(userDoLoginPO.getPassword(),"用户密码不能为空");
            return Result.ok(userService.doLogin(userDoLoginPO));
        } catch (Exception e) {
            log.error("UserController.doLogin.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    @PostMapping("/register")
    @Operation(summary = "注册接口")
    public Result<Boolean> register(@RequestBody UserDoLoginPO userDoLoginPO){
        try {
            if (log.isInfoEnabled()) {
                log.info("UserController.register.po:{}", JSON.toJSONString(userDoLoginPO));
            }
            Preconditions.checkNotNull(userDoLoginPO.getUserName(),"用户名不能为空");
            Preconditions.checkNotNull(userDoLoginPO.getPassword(),"用户密码不能为空");
            return Result.ok(userService.register(userDoLoginPO));
        }catch (Exception e){
            log.error("UserController.register.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }

    }

    @PostMapping("/outLogin")
    public Result<Boolean> outLogin(){
        try {
            return Result.ok(userService.outLogin());
        } catch (Exception e) {
            log.error("UserController.outLogin.error:{}", e.getMessage(), e);
            return Result.fail("用户注销失败");
        }
    }
}
