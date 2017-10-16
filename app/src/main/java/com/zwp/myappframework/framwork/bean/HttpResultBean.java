package com.zwp.myappframework.framwork.bean;

import java.io.Serializable;

/**
 * 返回结果基类
 */
public class HttpResultBean<T> implements Serializable {

    //返回码
    private String resultcode;

    //返回的消息
    private String reason;

    //返回的数据
    private T result;

    private int error_code;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
