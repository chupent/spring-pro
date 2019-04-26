package com.cp.app.core.comm.security.handler;

import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.model.pojo.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAuthenticationFailureHandler
 * @Description TODO 登录校验失败处理
 * @createdate 2019/2/21 星期四 14:24
 */
public class SystemAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger LOG = LoggerFactory.getLogger(getClass());


    private final String JSON_TYPE = "application/json";
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        LOG.info("登录校验失败处理");
        String contentType = request.getContentType();
        String message = "登录失败!";
        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
            message = "用户名或密码输入错误，登录失败!";
        } else if (e instanceof DisabledException) {
            message = "账户被禁用，登录失败，请联系管理员!";
        }
        if(!JSON_TYPE.equals(contentType)){
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
        }
        ApiResponse apiResponse = new ApiResponse(HttpServletResponse.SC_FORBIDDEN, message);
        ResultDispose.toJsonResult(response.getOutputStream(),apiResponse);
    }






}
