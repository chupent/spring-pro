package com.wx.cp.web.user;

import com.wx.cp.comm.ResultOut;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName web
 * @Description TODO
 * @createdate 2019/4/4 星期四 17:36
 */
@RestController
public class UserController {
    @GetMapping("/app/user/userinfo")
    public ResultOut<String> userinfo(){
        ResultOut<String> resultOut = new ResultOut<>();
        return resultOut;
    }
}
