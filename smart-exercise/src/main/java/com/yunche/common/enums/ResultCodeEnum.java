package com.yunche.common.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnum {

    SUCCESS(200,"成功"),
    FAIL(500, "失败"),
    USER_UN_EXIST(400,"用户已经存在"),
    USER_LOGIN_OUT(401,"用户已经退出"),
    USER_AUTH(402,"用户无授权信息"),
    USER_LOGIN(403,"没有该用户信息，请注册");

    public int code;

    public  String desc;

    ResultCodeEnum (int code, String desc){
        this.code = code;
        this.desc =desc;
    }

    public static ResultCodeEnum getByCode(int codeVal){
        for (ResultCodeEnum resultCodeEnum :ResultCodeEnum.values()){
           if (resultCodeEnum.code == codeVal){
             return resultCodeEnum;
           }
        }
        return null;
    }
}
