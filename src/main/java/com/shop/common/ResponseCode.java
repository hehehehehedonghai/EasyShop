package com.shop.common;

/**
 * ResponseCode
 * 响应码 枚举
 * @author Yarn
 * @create 2017/11/10/11:12
 */
public enum ResponseCode {
    /**
     * 0 代表成功
     */
    SUCCESS(0,"SUCCESS"),

    /**
     * 1 代表错误
     */
    ERROR(1,"ERROR"),

    /**
     * 10 代表没有登录
     */
    NEED_LOGIN(10,"NEED_LOGIN"),

    /**
     * 2 代表参数非法
     */
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
