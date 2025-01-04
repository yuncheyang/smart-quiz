package com.yunche.common.exception.handler;

import com.yunche.common.entity.Result;
import com.yunche.common.enums.ResultCodeEnum;
import com.yunche.common.exception.UserAlreadyExistsException;
import com.yunche.common.exception.UserLoginOutException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice  // 用于捕获 @RestController 中的异常
public class GlobalExceptionHandler {

    // 捕获自定义的 UserAlreadyExistsException 异常
    @ExceptionHandler(UserAlreadyExistsException.class)
    public Result<String> handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        // 返回友好的错误消息和状态码
        return Result.rspData(ResultCodeEnum.USER_UN_EXIST.code, e.getMessage());
    }

    //捕获用户退出异常
    @ExceptionHandler(UserLoginOutException.class)
    public Result<Object> handleUserLoginOutException(UserLoginOutException e){
        //返回退出登陆详细和状态码401
        return Result.rspData(ResultCodeEnum.USER_LOGIN_OUT.code,e.getMessage());
    }

}