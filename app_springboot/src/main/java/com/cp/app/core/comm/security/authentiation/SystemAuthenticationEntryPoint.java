package com.cp.app.core.comm.security.authentiation;


import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.model.pojo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAuthenticationEntryPoint
 * @Description TODO 未授权处理
 * @createdate 2019/2/21 星期四 16:23
 */
public class SystemAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOG = LoggerFactory.getLogger(SystemAuthenticationEntryPoint.class);
    private final String JSON_TYPE = "application/json";

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOG.info("授权失败");
        String contentType = request.getContentType();
        if(!JSON_TYPE.equals(contentType)){
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
        }
        ApiResponse apiResponse = new ApiResponse(HttpServletResponse.SC_UNAUTHORIZED, "登录超时，请重新登录。");
        ResultDispose.toJsonResult(response.getOutputStream(),apiResponse);
    }
}
