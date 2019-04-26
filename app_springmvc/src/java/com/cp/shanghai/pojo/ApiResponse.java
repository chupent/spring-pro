package com.cp.shanghai.pojo;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ApiResponse
 * @Description TODO Response Class
 * @createdate 2018/9/19 星期三 15:15
 */

public class ApiResponse {
    /**
     * response success code
     */
    public static final int SUCCESS_CODE = 200;
    /**
     * response fail code
     */
    public static final int FAIL_CODE = 400;
    /**
     * response result message
     */
    public static final String SUCCESS_MESSAGE = "The request is successful";


    private int code;
    private String message;
    private Object result;

    public ApiResponse(int code, String message, Object result) {
        super();
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public ApiResponse(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
