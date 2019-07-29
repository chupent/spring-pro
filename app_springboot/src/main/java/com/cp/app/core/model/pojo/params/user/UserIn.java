package com.cp.app.core.model.pojo.params.user;

import com.cp.app.core.model.pojo.params.PageIn;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserParam
 * @Description TODO
 * @createdate 2019/7/25 星期四 13:58
 */
public class UserIn extends PageIn {
    private String username;
    private String account;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
