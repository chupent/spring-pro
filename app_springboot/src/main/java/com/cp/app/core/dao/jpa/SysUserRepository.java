package com.cp.app.core.dao.jpa;

import com.cp.app.core.model.bean.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysUserRepository
 * @Description TODO
 * @createdate 2019/2/21 星期四 09:59
 */
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {
    SysUser findByAccount(String account);
}
