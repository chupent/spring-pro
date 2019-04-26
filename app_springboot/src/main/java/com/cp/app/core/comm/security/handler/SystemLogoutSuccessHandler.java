package com.cp.app.core.comm.security.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemLogoutSuccessHandler
 * @Description TODO 退出登录处理器
 * @createdate 2019/2/21 星期四 16:26
 */
public class SystemLogoutSuccessHandler implements LogoutSuccessHandler {
    private Logger LOG = LoggerFactory.getLogger(getClass());
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        LOG.info("退出登录");
    }
}
