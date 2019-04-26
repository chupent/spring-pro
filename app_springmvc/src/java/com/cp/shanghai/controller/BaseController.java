package com.cp.shanghai.controller;

import com.cp.shanghai.pojo.ApiResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName TestController
 * @Description Controller基类
 * @createdate 2018/9/19 星期三 15:21
 */
public abstract class BaseController {

    private static Gson gson = new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 请求成功时输出JSON格式数据
     *
     * @param obj      响应数据
     * @param response
     */
    protected void returnJsonResponse(Object obj, HttpServletResponse response) {
        this.response(gson.toJson(new ApiResponse(ApiResponse.SUCCESS_CODE, ApiResponse.SUCCESS_MESSAGE, obj)), response);
    }

    /**
     * 请求失败时输出JSON格式
     *
     * @param message  提示消息
     * @param response
     */
    protected void returnFailJsonResponse(String message, HttpServletResponse response) {
        returnFailJsonResponse(ApiResponse.FAIL_CODE, message, response);
    }

    /**
     * 请求失败时输出JSON格式
     *
     * @param code     状态码
     * @param message  响应消息
     * @param response
     */
    protected void returnFailJsonResponse(int code, String message, HttpServletResponse response) {
        this.response(gson.toJson(new ApiResponse(code, message)), response);
    }

    private void response(String param, HttpServletResponse response) {
        response.setContentType("application/json;charset=UTF-8");
        try {
            ServletOutputStream sos = response.getOutputStream();
            sos.write(param.getBytes("UTF-8"));
            sos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param obj
     * @return
     */
    protected String returnSucceedJsonResponse(Object obj) {
        return gson.toJson(new ApiResponse(ApiResponse.SUCCESS_CODE, ApiResponse.SUCCESS_MESSAGE, obj));
    }

    /**
     * @param message
     * @return
     */
    protected String returnFailJsonResponse(String message) {
        return gson.toJson(new ApiResponse(ApiResponse.FAIL_CODE, message));
    }

    /**
     * @param code
     * @param message
     * @return
     */
    protected String returnFailJsonResponse(int code, String message) {
        return gson.toJson(new ApiResponse(code, message));
    }
}
