package com.cp.app.core.web.user;

import com.cp.app.core.comm.api.UserApi;
import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import com.cp.app.core.comm.basics.BasicsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserController
 * @Description TODO
 * @createdate 2019/2/14 星期四 14:15
 */
@RestController
public class UserController extends BasicsController implements UserApi {

    @Autowired
    private UserApi userService;

    @GetMapping("/api/user/getUserInfo")
    public ApiResponse<SysUser> getUserInfo(String username) {
        return userService.getUserInfo(username);
    }

}
