package com.cp.shanghai.service;

import com.cp.shanghai.model.UserModel;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Login
 * @Description TODO
 * @createdate 2018/9/26 星期三 16:22
 */
public interface Login {
    UserModel login(String account, String password) throws Exception;
}
