package com.lcsaber.util;

/**
 * @Author: LiChao
 * @Date: 2019/6/17 15:44
 */
public class ResponseBean {
    // http 状态码
    private int code;

    //是否成功
    private boolean isSuccess;

    // 返回信息
    private String msg;

    // 返回的数据
    private Object data;

    public ResponseBean(int code,boolean isSuccess, String msg, Object data) {
        this.code = code;
        this.isSuccess = isSuccess;
        this.msg = msg;
        this.data = data;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{\"code\":\"" + code +
                "\",\"msg\":\"" + msg +
                "\",\"data\":\"" + data +
                "\"}";
    }
}
