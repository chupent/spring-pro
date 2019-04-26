package com.cp.shanghai.util.exception;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName ExceptionMessage
 * @Description TODO
 * @createdate 2018/9/27 星期四 14:03
 */
public enum ExceptionMessageEnum implements ExceptionMessage {
    EM_LOGIN_FAIL("登录失败，账户或密码错误!");

    private String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
