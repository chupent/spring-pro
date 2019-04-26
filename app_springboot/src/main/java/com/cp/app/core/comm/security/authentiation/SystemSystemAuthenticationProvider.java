package com.cp.app.core.comm.security.authentiation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemSystemAuthenticationProvider
 * @Description TODO 系统用户账号密码认证组件
 * @createdate 2019/2/21 星期四 15:02
 */
public class SystemSystemAuthenticationProvider implements AuthenticationProvider {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private SystemUserDetailsService systemUserDetailsService;
    private PasswordEncoder passwordEncoder;

    public SystemSystemAuthenticationProvider(SystemUserDetailsService systemUserDetailsService, PasswordEncoder passwordEncoder) {
        logger.info("创建系统用户账号密码认证组件:SystemSystemAuthenticationProvider");
        this.systemUserDetailsService = systemUserDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        logger.info("密码校验");

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        SystemUserDetails sysUser = (SystemUserDetails) systemUserDetailsService.loadUserByUsername(username);
        if (sysUser != null) {
            if (passwordEncoder.matches(password, sysUser.getPassword())) {
                Authentication auth = new UsernamePasswordCodeAuthenticationToken(username, password, sysUser.getAuthorities());
                return auth;
            } else {
                throw new BadCredentialsException("密码错误");
            }
        } else {
            throw new UsernameNotFoundException("用户不存在");
        }
    }

    /**
     * 是否可以提供输入类型的认证服务
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordCodeAuthenticationToken.class);
    }
}
