package com.wx.cp.filter;

import api.OauthApi;
import bean.oauth.OauthAccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WeiXinAuthorizationFilter
 * @Description TODO 微信授权
 * @createdate 2019/4/4 星期四 16:03
 */
public class WeChatAuthorizationFilter extends OncePerRequestFilter {
    private Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String url = request.getContextPath()+request.getServletPath();
        LOGGER.info("访问地址:"+url);
        if(url.contains("apiAuthorizeCallBack")){
            filterChain.doFilter(request,response);
            return;
        }
        HttpSession session = request.getSession();
        OauthAccessToken token = (OauthAccessToken) session.getAttribute("OauthAccessToken");
        if (token==null||StringUtils.isEmpty(token.getOpenid())){//未授权，发起授权
            session.setAttribute("",url);
            OauthApi oauthApi =new  OauthApi();
            response.sendRedirect(oauthApi.getCode());
            return;
        }
        filterChain.doFilter(request,response);
    }
}
