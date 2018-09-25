package com.example.demo.common;

import java.io.Serializable;

/**
 * 统一返回结果类
 * Created by luoping on 2017/4/26
 */
public class BaseResult<T> implements Serializable {

    // 状态码
    public int code;

    // 状态码对应的信息
    public String message;

    public boolean success;

    // 数据结果集
    public T data;

    public BaseResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BaseResult() {}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
