package com.wx.cp.web;

import api.OauthApi;
import bean.oauth.OauthAccessToken;
import bean.WechatError;
import com.wx.cp.api.WeChatApi;
import kit.CallBackApi;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WeChatController
 * @Description TODO 微信授权回调处理
 * @createdate 2019/4/4 星期四 17:48
 */
@Controller
public class WeChatController implements WeChatApi {
    private org.slf4j.Logger LOG = LoggerFactory.getLogger(getClass());
    private String pageName;
    @Override
    public String authorizeCallBack(HttpServletRequest request, HttpServletResponse response) {
        LOG.info("微信调用授权成功！");
        HttpSession session = request.getSession();
        pageName = "main";
        try {
            String code = request.getParameter("code");
            LOG.info("授权地址:"+code);
            OauthApi oauthApi = new OauthApi();
            CallBackApi<OauthAccessToken> callBackApi = new CallBackApi<OauthAccessToken>() {
                @Override
                public void succeed(OauthAccessToken oauthAccessToken) {
                    LOG.info("授权成功:"+oauthAccessToken.getOpenid()+"   "+oauthAccessToken.getScope());
                    session.setAttribute("OauthAccessToken", oauthAccessToken);
                }

                @Override
                public void fail(WechatError error) {
                    LOG.info("授权失败:"+error.getErrcode()+"   "+error.getErrmsg());
                    request.setAttribute("code",code);
                    request.setAttribute("errcode",error.getErrcode());
                    request.setAttribute("errmsg",error.getErrmsg());
                    pageName = "auth";
                }
            };
            oauthApi.getAccessToken(code, callBackApi);
        } catch (Exception e) {

        }
        return pageName;
    }
}
