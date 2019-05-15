package com.cp.app.core.service.user;

import com.cp.app.core.dao.jpa.SysUserRepository;
import com.cp.app.core.model.bean.SysUser;
import com.cp.app.core.model.pojo.ApiResponse;
import com.cp.app.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserService
 * @Description TODO
 * @createdate 2019/4/3 星期三 10:58
 */
@Service
public class UserService extends AbstractService {
    @Autowired
    private SysUserRepository repository;

    public ApiResponse<SysUser> getUserInfo(String username) {
        SysUser user =repository.findByAccount(username);
        return this.bindSucceedResult(user);
    }
}
