package com.cp.shanghai.service.impl;

import com.cp.shanghai.dao.UserDao;
import com.cp.shanghai.model.UserModel;
import com.cp.shanghai.service.Login;
import com.cp.shanghai.util.TokenUtil;
import com.cp.shanghai.util.exception.BussinessException;
import com.cp.shanghai.util.exception.ExceptionMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName LoginImpl
 * @Description TODO
 * @createdate 2018/9/26 星期三 16:22
 */
@Service
public class LoginImpl implements Login {

    @Autowired
    private UserDao userDao;

    @Override
    public UserModel login(String account, String password) throws Exception {
        //解密

        //登录校验,更新token
        String token = TokenUtil.createToken();
        Integer updatetoken = userDao.updateToken(account, password, token);
        if (null == updatetoken || 0 > updatetoken)
            BussinessException.throwExcption(ExceptionMessageEnum.EM_LOGIN_FAIL);

        //获取用户信息
        UserModel userModel = userDao.queryUserInfo(token);
        if (null == userModel) BussinessException.throwExcption(ExceptionMessageEnum.EM_LOGIN_FAIL);
        return userModel;
    }
}
