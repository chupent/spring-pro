package com.wx.cp.configuration.filter;

import com.wx.cp.comm.net.RequestWrapper;
import com.wx.cp.comm.net.ResponseWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import wechat.Wechat;
import wechat.bean.oauth.OauthAccessToken;

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

        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        String url = request.getContextPath()+request.getServletPath();
        if(url.contains("apiAuthorizeCallBack")){
            filterChain.doFilter(requestWrapper,responseWrapper);
            return;
        }

        HttpSession session = request.getSession();
        OauthAccessToken token = (OauthAccessToken) session.getAttribute("OauthAccessToken");
        if (token==null||StringUtils.isEmpty(token.getOpenid())){//未授权，发起授权
            requestWrapper.saveRequestBody();
//            response.sendRedirect(Wechat.OAUTH_API.getCode());//重定向微信授权服务器
            request.getRequestDispatcher(Wechat.OAUTH_API.getCode()).forward(requestWrapper, responseWrapper);//转发微信授权服务器
            return;
        }
        filterChain.doFilter(requestWrapper,responseWrapper);
    }
}
