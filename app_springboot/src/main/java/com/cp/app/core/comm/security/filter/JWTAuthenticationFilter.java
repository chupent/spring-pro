package com.cp.app.core.comm.security.filter;

import com.cp.app.core.comm.security.JwtsUtil;
import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.comm.security.authentiation.SystemUserDetails;
import com.cp.app.core.comm.security.authentiation.SystemUserDetailsService;
import com.cp.app.core.model.pojo.ApiResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName JWTAuthenticationFilter
 * @Description TODO 用户认证过滤器<过滤非登录接口、忽略接口外的所有接口>
 * JWT认证过滤器
 * * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * * 如果校验通过，就认为这是一个取得授权的合法请求
 * @createdate 2019/2/14 星期四 14:04
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    private SystemUserDetailsService systemUserDetailsService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, SystemUserDetailsService userDetailsService) {
        super(authenticationManager);
        this.systemUserDetailsService = userDetailsService;
        logger.info("创建JWTAuthenticationFilter");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        logger.info("进入doFilterInternal方法");
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request,response);
        if(null!=authentication) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        String message = ApiResponse.FAIL_MSG;
        String username = null;
        try {
            username = JwtsUtil.getUserName(token);
            if (username != null) {
                SystemUserDetails sysUser = (SystemUserDetails) systemUserDetailsService.loadUserByUsername(username);
                return new UsernamePasswordAuthenticationToken(sysUser, token, sysUser.getAuthorities());
            }
        } catch (ExpiredJwtException e) {
            logger.info("Token已过期：" + e.getMessage());
            message = "无效的Token";
        } catch (Exception e) {
            logger.info("无效的Token：" + e.getMessage());
            message = "无效的Token";
        }
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        response.setCharacterEncoding("utf-8");
        ResultDispose.toJsonResult(response.getOutputStream(),new ApiResponse(403, message));
        return null;
    }
}
