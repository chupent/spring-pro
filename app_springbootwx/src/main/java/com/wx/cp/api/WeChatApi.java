package com.wx.cp.api;


import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WeChatApi
 * @Description TODO
 * @createdate 2019/4/6 星期六 04:18
 */
public interface WeChatApi extends RootApi{
    @GetMapping("/wechat/apiAuthorizeCallBack")
    String authorizeCallBack(HttpServletRequest request, HttpServletResponse response);
}
