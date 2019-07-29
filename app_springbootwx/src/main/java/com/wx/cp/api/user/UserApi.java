package com.wx.cp.api.user;

import com.wx.cp.comm.root.RootApi;
import com.wx.cp.model.ResultOut;
import com.wx.cp.model.bean.user.UserBean;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserApi
 * @Description TODO
 * @createdate 2019/5/15 星期三 11:16
 */
public interface UserApi extends RootApi {
    @GetMapping("/user/userinfo")
    ResultOut<UserBean> userinfo();
}
