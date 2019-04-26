package com.cp.app.core.web;

import com.cp.app.core.comm.excption.SystemException;
import com.cp.app.core.model.pojo.ApiResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author : chupengtang
 * @version V1.0
 * @Title: Exception.java
 * @Package cn.st.shanghai.ics.sms.web
 * @date : 2018年12月21日 下午4:31:28
 * @Description: TODO 异常同一处理类
 */
@ControllerAdvice
@ResponseBody
public class ExceptionController {
    @ExceptionHandler({Exception.class})
    public ApiResponse exception(Exception ex) {
        if (ex instanceof NullPointerException) {
            ex.printStackTrace();
        } else if (ex instanceof ClassCastException) {
            ex.printStackTrace();
        } else if (ex instanceof ArrayIndexOutOfBoundsException) {
            ex.printStackTrace();
        } else if (ex instanceof SystemException) {
            //ex.printStackTrace();
            return resultFormat((SystemException) ex);
        } else {
            ex.printStackTrace();
        }
        if (ex.getMessage() != null) {
            return resultFormat(new SystemException(ex.getMessage()));
        }
        return resultFormat(new SystemException());
    }

    private <T extends Throwable> ApiResponse resultFormat(T ex) {
        return resultFormat(ApiResponse.FAIL_CODE, ex);
    }

    private <T extends Throwable> ApiResponse resultFormat(Integer code, T ex) {
        return new ApiResponse(code, ex.getMessage());
    }
}
