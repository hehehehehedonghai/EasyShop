package com.shop.common;

/**
 *  Yarn
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    PARAMETER_ERROR(2,"PARAMETER_ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN");

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
