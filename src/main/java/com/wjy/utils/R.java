package com.wjy.utils;

import lombok.Data;
@Data
public class R {

    private Object data;

    private Integer code;

    private String message;


    public static R success(String message){
        R r =new R();
        r.message =message;
        r.code=200;
        return r;
    }

    public static R success(String message,Object data){
        R r =success(message);
        r.data=data;
        return r;
    }

    public static R fail(String message,Integer code){
        R r =new R();
        r.message =message;
        r.code=code;
        return r;
    }

    public static R fail(String message){
        R r =new R();
        r.message =message;
        r.code=500;
        return r;
    }
}
