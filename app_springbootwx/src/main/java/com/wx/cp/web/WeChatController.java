package com.wx.cp.web;


import com.wx.cp.comm.util.ResultDispose;
import com.wx.cp.model.ResultOut;
import wechat.Wechat;
import wechat.bean.oauth.OauthAccessToken;
import com.wx.cp.api.WeChatApi;
import com.wx.cp.comm.root.RootController;
import com.wx.cp.comm.net.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import wechat.kit.net.ResposeBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
        try {
            String code = request.getParameter("code");
            LOG.info("授权地址:"+code);
            ResposeBody<OauthAccessToken> resposeBody = Wechat.OAUTH_API.getAccessToken(code);
            if(null==resposeBody){
                ResultDispose.toJsonResult(response.getOutputStream(), ResultOut.getResult(403,"微信授权失败,请重新授权!",code));
                return;
            }
            OauthAccessToken accessToken = resposeBody.getT();
            if(null==accessToken){
                LOG.info("授权失败:"+resposeBody.getErrcode()+"   "+resposeBody.getErrmsg());
                ResultDispose.toJsonResult(response.getOutputStream(), ResultOut.getResult(403,"微信授权失败,请重新授权!",code));
                return;
            }
            this.setSessionAttribute("OauthAccessToken", accessToken);
            //重新请求授权前发出的请求
            RequestBody requestBody = (RequestBody) this.getSessionAttribute("requestBody");
            if(null!=requestBody){
                String url =  requestBody.getUrl()+"?"+requestBody.getParams();
                request.getRequestDispatcher(url).forward(request, response); //带文件上传的特殊处理
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
