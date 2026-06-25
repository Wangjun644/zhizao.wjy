package com.wjy.utils;

import lombok.Data;





@Data
public class R {
    //具体返回值
    private Object data;
    //参照http协议 状态吗 200 302 304 404 500消息
    //认为约定 code = 200 成功， 非 200 失败
    private Integer code;
    //消息 删除成功 新增失败
    private String message;

    //删除成功
    public static R success(String message){
        R r = new R();
        r.message = message;
        r.code = 200;
        return r;
    }

    //查询成功
    public static R success(String message,Object data){
        R r = success(message);
        r.data=data;
        return r;
    }

    public static R fail(String message,Integer code){
        R r =new R();
        r.message =message;
        r.code=code;
        return r;
    }

    //查询成功
    public static R fail(String message){
        R r =new R();
        r.message =message;
        r.code=500;
        return r;
    }
}
