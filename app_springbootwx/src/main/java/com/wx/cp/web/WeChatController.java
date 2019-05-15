package com.wx.cp.web;


import wechat.Wechat;
import wechat.bean.oauth.OauthAccessToken;
import wechat.bean.WechatError;
import com.wx.cp.api.WeChatApi;
import com.wx.cp.comm.root.RootController;
import com.wx.cp.comm.util.ResultDispose;
import com.wx.cp.comm.net.RequestBody;
import com.wx.cp.model.ResultOut;
import wechat.kit.CallBackApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WeChatController
 * @Description TODO 微信授权回调处理
 * @createdate 2019/4/4 星期四 17:48
 */
@Controller
public class WeChatController extends RootController implements WeChatApi {
    private Logger LOG = LoggerFactory.getLogger(getClass());

    @Override
    public void authorizeCallBack(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String code = request.getParameter("code");
        LOG.info("授权地址:"+code);

        try {
            Wechat.OAUTH_API.getAccessToken(code, new CallBackApi<OauthAccessToken>() {
                @Override
                public void succeed(OauthAccessToken oauthAccessToken) {
                    LOG.info("授权成功:"+oauthAccessToken.getOpenid()+" ,  "+oauthAccessToken.getScope());

                    session.setAttribute("OauthAccessToken", oauthAccessToken); //记录授权

                    //重新请求授权前发出的请求
                    RequestBody requestBody = (RequestBody) request.getSession().getAttribute("requestBody");
                    if(null!=requestBody){
                        String url =  requestBody.getUrl()+"?"+requestBody.getParams();
                        try {
                            request.getRequestDispatcher(url).forward(request, response); //带文件上传的特殊处理
                        } catch (ServletException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                @Override
                public void fail(WechatError error) {
                    LOG.info("授权失败:"+error.getErrcode()+"   "+error.getErrmsg());
                    try {
                        ResultDispose.toJsonResult(
                                response.getOutputStream(),
                                ResultOut.getResult(403,"微信授权失败,请重新授权!",code));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            try {
                ResultDispose.toJsonResult(
                        response.getOutputStream(),
                        ResultOut.getResult(403,"微信授权失败,请重新授权!",code));
            } catch (IOException e1) {
                e.printStackTrace();
            }
        }
    }
}
