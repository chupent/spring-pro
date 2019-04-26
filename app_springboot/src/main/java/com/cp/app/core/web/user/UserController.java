package com.cp.app.core.web.user;

import com.cp.app.core.api.UserApi;
import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import com.cp.app.core.service.user.UserService;
import com.cp.app.core.web.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserController
 * @Description TODO
 * @createdate 2019/2/14 星期四 14:15
 */
@RestController
public class UserController extends AbstractController implements UserApi {

    @Autowired
    private UserService service;

    @Override
    public ApiResponse<SysUser> getUserInfo(String username) {
        return service.getUserInfo(username);
    }

}
