package com.cp.app.core.web;

import com.cp.app.core.comm.basics.BasicsController;
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
public class ExceptionController extends BasicsController {
    @ExceptionHandler({Exception.class,NullPointerException.class,ClassCastException.class,ArrayIndexOutOfBoundsException.class})
    public ApiResponse exception(Exception ex) {
        ex.printStackTrace();
        return new ApiResponse( ex.getMessage());
    }
}
