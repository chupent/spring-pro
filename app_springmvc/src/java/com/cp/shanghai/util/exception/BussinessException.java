package com.cp.shanghai.util.exception;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName BussinessException
 * @Description TODO 业务出现的异常
 * @createdate 2018/9/26 星期三 16:53
 */
public class BussinessException extends Exception {


    private static BussinessException bussinessException;

    public BussinessException() {

    }

    public BussinessException(ExceptionMessage message) {
        super(message.getMessage());
    }

    public static void throwExcption(ExceptionMessage message) throws BussinessException {
        throw new BussinessException(message);
    }
}
