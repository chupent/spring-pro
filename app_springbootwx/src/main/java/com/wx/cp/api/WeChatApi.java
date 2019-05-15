package com.wx.cp.api;


import com.wx.cp.comm.root.RootApi;
import org.springframework.web.bind.annotation.PostMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName WeChatApi
 * @Description TODO
 * @createdate 2019/4/6 星期六 04:18
 */
public interface WeChatApi extends RootApi {
    @PostMapping("/wechat/apiAuthorizeCallBack")
    void authorizeCallBack(HttpServletRequest request, HttpServletResponse response);
}
