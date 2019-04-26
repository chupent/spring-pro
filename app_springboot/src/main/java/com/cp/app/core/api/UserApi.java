package com.cp.app.core.api;

import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import org.springframework.web.bind.annotation.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserApi
 * @Description TODO
 * @createdate 2019/4/1 星期一 15:30
 */
public interface UserApi extends BaseApi {
    @GetMapping("/user/getUserInfo")
    ApiResponse<SysUser> getUserInfo(@RequestParam(required = true) String username);
}
