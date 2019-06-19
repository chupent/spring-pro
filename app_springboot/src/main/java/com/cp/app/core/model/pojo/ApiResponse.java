package com.cp.app.core.model.pojo;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ApiResponse
 * @Description TODO 响应PoJo
 * @createdate 2019/2/14 星期四 14:17
 */
public class ApiResponse<T> {
    public static final int OK_CODE = 200;
    public static final int FAIL_CODE = 400;
    public static final String OK_MSG="请求成功！";
    public static final String FAIL_MSG="请求失败！";
    private int resCode;
    private String resMessage;
    private T resData;


    public ApiResponse(int code, String message) {
        this.resCode = code;
        this.resMessage = message;
    }
    public ApiResponse(String message) {
        this.resCode = this.FAIL_CODE;
        this.resMessage = message;
    }
    public ApiResponse(T data) {
        this.resCode = this.OK_CODE;
        this.resMessage = this.OK_MSG;
        this.resData = data;
    }


    public int getResCode() {
        return resCode;
    }

    public void setResCode(int resCode) {
        this.resCode = resCode;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public T getResData() {
        return resData;
    }

    public void setResData(T resData) {
        this.resData = resData;
    }
}
