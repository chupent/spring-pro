package com.cp.app.core.comm.security.component;

import com.cp.app.core.dao.jpa.SysResourceRepository;
import com.cp.app.core.dao.jpa.SysRoleRepository;
import com.cp.app.core.model.bean.SysResource;
import com.cp.app.core.model.bean.SysRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysFilterInvocationSecurityMetadataSource
 * @Description TODO 实现FilterInvocationSecurityMetadataSource这个接口供系统调用，放在Component包中。作用是在用户请求一个地址的时候，截获这个地址，告诉程序访问这个地址需要哪些权限角色
 * @createdate 2019/2/21 星期四 10:14
 */
@Component
public class SystemFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysResourceRepository sysResourceRepository;
    @Autowired
    private SysRoleRepository sysRoleRepository;


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        logger.info("调用getAttributes，作用接收用户请求的地址，返回访问该地址需要的所有权限");
        String url = ((FilterInvocation) object).getRequestUrl();
        logger.info("请求地址：", url);
        //过滤登录等不需要校验的地址
        if (url.indexOf("login") == 1) {
            return null;
        }
        String path = url.substring(0,url.indexOf("?"));

        //使用访问的地址到权限数据库中查找
        SysResource sysResource = sysResourceRepository.findByResUrl(path);
        if (null == sysResource) {
            return SecurityConfig.createList("ROLE_LOGIN"); //如果没有找到相应的匹配说明地址为公共地址不需要权限角色
        }

        List<SysRole> sysRoles = sysRoleRepository.findSysRoleOfResourceId(sysResource.getResId());
        int size = sysRoles.size();
        String valus[] = new String[size];
        for (int i = 0; i < size; i++) {
            valus[i] = sysRoles.get(i).getRoleName();
        }

        return SecurityConfig.createList(valus);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }
}
