package com.cp.app.core.comm.api;

import com.cp.app.core.comm.basics.BasicsApi;
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
public interface UserApi extends BasicsApi {
    ApiResponse<SysUser> getUserInfo(@RequestParam(required = true) String username);
}
