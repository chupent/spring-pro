package com.wx.cp.dao;

import com.wx.cp.model.bean.user.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserRe
 * @Description TODO 用户信息
 * @createdate 2019/5/14 星期二 14:43
 */
public interface UserRepository extends JpaRepository<UserBean,Long> {
    UserBean findByOpenId(String openid);
}
