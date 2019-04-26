package com.cp.app.core.comm.excption;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemException
 * @Description TODO
 * @createdate 2019/2/21 星期四 17:26
 */
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    static final String DEFULT_MESSAGE = "系统错误，请联系管理员";

    public SystemException() {
        super(DEFULT_MESSAGE);
    }

    public SystemException(String message) {
        super(message);
    }
}
