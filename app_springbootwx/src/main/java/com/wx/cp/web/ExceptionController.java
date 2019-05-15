package com.wx.cp.web;

import com.wx.cp.model.ResultOut;
import org.springframework.util.StringUtils;
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
    public ResultOut exception(Exception ex) {
        String message = ex.getMessage();
        if (ex instanceof NullPointerException) {
            ex.printStackTrace();
        } else if (ex instanceof ClassCastException) {
            ex.printStackTrace();
        } else if (ex instanceof ArrayIndexOutOfBoundsException) {
            ex.printStackTrace();
        } else {
            ex.printStackTrace();
        }
        if (!StringUtils.isEmpty(message)) {
            return  ResultOut.isNo(message);
        }
        return ResultOut.isNo("请求失败，程序出现异常!");
    }
}
