package com.cp.app.core.comm.security.authentiation;

import com.cp.app.core.dao.jpa.user.SysResourceRepository;
import com.cp.app.core.dao.jpa.user.SysRoleRepository;
import com.cp.app.core.dao.jpa.user.SysUserRepository;
import com.cp.app.core.model.bean.SysResource;
import com.cp.app.core.model.bean.SysRole;
import com.cp.app.core.model.bean.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemUserDetailsService
 * @Description TODO 配置用户认证逻辑
 * @createdate 2019/2/14 星期四 14:38
 */
@Component
public class SystemUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;
    @Autowired
    private SysResourceRepository sysResourceRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("调用dao，获取用户认证");
        SysUser sysUser = userRepository.findByAccount(username);
        if (null == sysUser) throw new UsernameNotFoundException("用户名不存在");
        List<SysRole> sysRoles = sysRoleRepository.findSysRoleOfUserId(sysUser.getUserId());
        List<SysResource> sysResources = sysResourceRepository.findByUserId(sysUser.getUserId());
        return new SystemUserDetails(sysUser, sysRoles,sysResources);
    }
}
