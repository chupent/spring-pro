package com.cp.app.core.comm.security.filter;

import com.cp.app.core.comm.security.authentiation.UsernamePasswordCodeAuthenticationToken;
import com.cp.app.core.comm.security.handler.SystemAuthenticationFailureHandler;
import com.cp.app.core.comm.security.handler.SystemAuthenticationSuccessHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName JWTAuthorizationFilter
 * @Description TODO 登录过滤器<访问login接口时进入此过滤器>
 * 该类继承自UsernamePasswordAuthenticationFilter
 * attemptAuthentication ：接收并解析用户凭证。
 * 拦截登录请求，AuthenticationManager 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * @createdate 2019/2/14 星期四 14:04
 */
public class JWTAuthorizationFilter extends UsernamePasswordAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
    private AuthenticationManager authenticationManager;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        logger.info("创建登录过滤器:UsernamePasswordAuthenticationFilter");
        this.authenticationManager = authenticationManager;
        //实现successfulAuthentication方法后设置setAuthenticationSuccessHandler方法就无效了，前者优先后者。
        this.setAuthenticationFailureHandler(new SystemAuthenticationFailureHandler());//登录失败通知
        this.setAuthenticationSuccessHandler(new SystemAuthenticationSuccessHandler());//登录成功通知
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String code = request.getParameter("code");
        return authenticationManager.authenticate(new UsernamePasswordCodeAuthenticationToken(username, password, code));
    }
}
