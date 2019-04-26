package com.cp.app.core.comm.security.handler;

import com.cp.app.core.comm.security.JwtsUtil;
import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.model.pojo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAuthenticationSuccessHandler
 * @Description TODO 登录成功结果集:token、输出等
 * @createdate 2019/2/21 星期四 14:37
 */

public class SystemAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功!");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String token = null;
        try {

            token = JwtsUtil.buildToken(authentication.getName(),30);

            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", token);
            response.setContentType("application/json;charset=utf-8");


            Map<String, Object> map = new HashMap<>();
            map.put("Authorization", token);
            map.put("authorities", authorities);
            ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String, Object>>(map);
            apiResponse.setResData(map);
            ResultDispose.toJsonResult(response.getOutputStream(),apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
