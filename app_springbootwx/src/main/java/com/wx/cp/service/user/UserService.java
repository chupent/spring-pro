package com.wx.cp.service.user;

import com.wx.cp.api.user.UserApi;
import com.wx.cp.dao.UserRepository;
import com.wx.cp.model.ResultOut;
import com.wx.cp.model.bean.user.UserBean;
import com.wx.cp.comm.root.RootService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wechat.bean.oauth.OauthAccessToken;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserServiceImpl
 * @Description TODO
 * @createdate 2019/5/15 星期三 11:15
 */
@Service
public class UserService extends RootService implements UserApi {

    @Autowired
    private UserRepository repository;

    @Override
    public ResultOut<UserBean> userinfo() {
        OauthAccessToken token = (OauthAccessToken) this.getSessionAttribute("OauthAccessToken");
        if(null==token){
            return ResultOut.isNo();
        }
        String openid = token.getOpenid(); //获取openid
        return ResultOut.isOK(repository.findByOpenId(openid));
    }
}
