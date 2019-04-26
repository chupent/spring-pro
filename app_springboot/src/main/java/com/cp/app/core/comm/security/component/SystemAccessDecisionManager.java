package com.cp.app.core.comm.security.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAccessDecisionManager
 * @Description TODO
 * 这个类的作用是接收SysFilterInvocationSecurityMetadataSource类返回的访问当前url所需要的权限列表（decide方法的第三个参数），
 * 再结合当前用户的信息（decide方法的第一个参数），决定用户是否可以访问这个url
 * @createdate 2019/2/21 星期四 10:46
 */
@Component
public class SystemAccessDecisionManager implements AccessDecisionManager {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        logger.info("用户访问url比对用户是否在用户角色的权限范围");
        //迭代器遍历目标url的权限列表
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute ca = iterator.next();
            String needRole = ca.getAttribute();
            if ("ROLE_LOGIN".equals(needRole)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("未登录");
                } else {
                    return;
                }
            }
            //遍历当前用户所拥有的所有角色权限
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authoritie : authorities) {
                if (authoritie.getAuthority().equals(needRole)) return;
            }
        }
        //循环完还没有找的说明用户没有权限
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
