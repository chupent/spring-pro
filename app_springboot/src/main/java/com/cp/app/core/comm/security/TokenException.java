package com.cp.app.core.comm.security;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName TokenException
 * @Description TODO
 * @createdate 2019/2/21 星期四 16:42
 */
public class TokenException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 7265578940463101138L;

    public TokenException() {
        super();
    }

    public TokenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
