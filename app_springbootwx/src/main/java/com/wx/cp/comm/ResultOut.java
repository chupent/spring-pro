package com.wx.cp.comm;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ResultOut
 * @Description TODO
 * @createdate 2019/4/4 星期四 16:27
 */
public class ResultOut<T> {

    public static final int DEFAULT_OK = 200;
    public static final int DEFAULT_NO = 400;
    public static final String DEFAULT_MSG_OK = "请求成功！";
    public static final String DEFAULT_MSG_NO = "请求失败！";

    private int status;
    private String message;
    private T data;

    /**
     * 请求成功构造
     * @param data
     */
    public ResultOut(T data) {
        this.data = data;
        this.status = DEFAULT_OK;
        this.message = DEFAULT_MSG_OK;
    }
    /**
     * 请求成功构造
     * @param message
     * @param data
     */
    public ResultOut(T data,String message) {
        this.status = DEFAULT_OK;
        this.message = message;
        this.data = data;
    }
    /**
     * 请求失败构造
     * @param message
     */
    public ResultOut(String message) {
        this.message = message;
        this.status = DEFAULT_NO;
        this.message = DEFAULT_MSG_NO;
    }


    public ResultOut() {

    }
    public ResultOut(int status, String message) {
        this.status = status;
        this.message = message;
    }
    public ResultOut(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
