package com.ljc.market.bean;

import javax.persistence.Table;

/**
 * ajax提交统一返回结果类
 */
public class Result<T> {

    private int code;//错误码

    private String msg;//错误信息

    private T data;//定义返回的数据

    private Result(){}//构造函数私有化


    //定义传codemsg的私有化构造函数，不允许外部创建对象
    private Result(CodeMsg codeMsg){
        if (codeMsg!=null){
            this.code=codeMsg.getCode();
            this.msg=codeMsg.getMsg();
        }
    }

    //定义传指定数据对象和codemsg的私有化构造函数，不允许任意创造对象
    private Result(T data,CodeMsg codeMsg){
        if (codeMsg!=null){
            this.code=codeMsg.getCode();
            this.msg=codeMsg.getMsg();
        }
        this.data=data;
    }

    //定义统一的成功返回函数
    public static <T>Result<T> success(T data){
        return new Result<T>(data,CodeMsg.SUCCESS);
    }

    //统一错误返回方法，所有错误都调用此方法
    public static <T>Result<T> error(CodeMsg codeMsg){
        return new Result<T>(codeMsg);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
