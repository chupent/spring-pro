package com.wx.cp.web.user;
import com.wx.cp.api.user.UserApi;
import com.wx.cp.model.ResultOut;
import com.wx.cp.model.bean.user.UserBean;
import com.wx.cp.comm.root.RootController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName web
 * @Description TODO
 * @createdate 2019/4/4 星期四 17:36
 */
@RestController
public class UserController extends RootController implements UserApi {

    @Autowired
    @Qualifier("userService")
    private UserApi userService;

    @ModelAttribute
    public void initUser(HttpSession session){
        //此方法会在每次请求前调用（这个类的处理方法）
    }

    @GetMapping("/user/userinfo")
    public  ResultOut<UserBean> userinfo() {
        return userService.userinfo();
    }
}
