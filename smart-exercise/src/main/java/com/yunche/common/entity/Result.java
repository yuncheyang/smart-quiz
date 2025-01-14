package com.yunche.common.entity;


import com.yunche.common.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 结果包装实体类
 */
@Data
public class Result<T> {

    private Integer code;

    private Boolean success;

    private T message;

    private T data;


    public static  Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMessage(ResultCodeEnum.SUCCESS.desc);
        return result;
    }

    public static<T>  Result ok(T data){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.SUCCESS.code);
        result.setMessage(ResultCodeEnum.SUCCESS.desc);
        result.setData(data);
        return result;
    }

    public static  Result fail(){
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultCodeEnum.FAIL.code);
        result.setMessage(ResultCodeEnum.FAIL.desc);
        return result;
    }

    public static<T>  Result fail(T data){
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(ResultCodeEnum.FAIL.code);
        result.setMessage(data);
        result.setData(data);
        return result;
    }


    /**
     * @title 返回数据-自定义code
     * @param data
     * @return
     */
    public static <T> Result<T> rspData(int code , T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(ResultCodeEnum.USER_UN_EXIST.desc);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> rspFail(int code , T data) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(ResultCodeEnum.USER_UN_EXIST.desc);
        result.setData(data);
        return result;
    }


}
