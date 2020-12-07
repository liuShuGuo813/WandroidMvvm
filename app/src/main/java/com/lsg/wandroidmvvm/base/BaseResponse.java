package com.lsg.wandroidmvvm.base;

import java.io.Serializable;

/**
 * Created by lsg on 2020/10/19
 */
public class BaseResponse<T> implements Serializable {


    private T data;
    private int errorCode;
    private String errorMsg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg == null ? "" : errorMsg;
    }

}
