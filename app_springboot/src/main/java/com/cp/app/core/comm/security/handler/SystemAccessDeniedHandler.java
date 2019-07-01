package com.cp.app.core.comm.security.handler;

import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.model.pojo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAccessDeniedHandler
 * @Description TODO
 * 类功能：自定义403相应内容
 * @createdate 2019/2/21 星期四 10:58
 */
public class SystemAccessDeniedHandler implements AccessDeniedHandler {
    private Logger LOG = LoggerFactory.getLogger(getClass());
    private final String JSON_TYPE = "application/json";
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            LOG.warn("User: " + auth.getName() + " attempted to access the protected URL: " + request.getRequestURI());
        }
        String contentType = request.getContentType();
        if(!JSON_TYPE.equals(contentType)){
            response.setContentType("application/json;charset=utf-8");
            response.setCharacterEncoding("utf-8");
        }
        ApiResponse<String> apiResponse = new ApiResponse<String>(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
        ResultDispose.toJsonResult(response.getOutputStream(),apiResponse);
    }
}
