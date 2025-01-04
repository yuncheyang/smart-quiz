package com.yunche.common.enums;

import lombok.Getter;

/*
删除状态枚举
 */
@Getter
public enum IsDeleteFlagEnum {


    DELETED(1,"已删除"),
    UNDELETED(0, "未删除");

    public int code;

    public  String desc;

    IsDeleteFlagEnum (int code, String desc){
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
