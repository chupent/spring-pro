package com.cp.app.core.comm.security.handler;

import com.cp.app.core.comm.security.JwtsUtil;
import com.cp.app.core.comm.security.ResultDispose;
import com.cp.app.core.comm.security.authentiation.SystemUserDetails;
import com.cp.app.core.comm.security.authentiation.UsernamePasswordCodeAuthenticationToken;
import com.cp.app.core.model.bean.SysResource;
import com.cp.app.core.model.pojo.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemAuthenticationSuccessHandler
 * @Description TODO 登录成功结果集:token、输出等
 * @createdate 2019/2/21 星期四 14:37
 */

public class SystemAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功!");
        String token = null;
        try {
            UsernamePasswordCodeAuthenticationToken authenticationToken = (UsernamePasswordCodeAuthenticationToken) authentication;
            SystemUserDetails systemUserDetails = (SystemUserDetails) authenticationToken.getUserDetails();
            token = JwtsUtil.buildToken(authentication.getName(),30);

            // 登录成功后，返回token到header里面
            response.addHeader("Authorization", token);
            response.setContentType("application/json;charset=utf-8");

            Map<String, Object> map = new HashMap<>();
            map.put("Authorization", token);
            map.put("userInfo", systemUserDetails.getUser());

            List<SysResource> list = this.queryMenus(systemUserDetails.getSysResources(),"0");
            map.put("resources",  list);

            ApiResponse<Map<String, Object>> apiResponse = new ApiResponse<Map<String, Object>>(map);
            apiResponse.setResData(map);
            ResultDispose.toJsonResult(response.getOutputStream(),apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param roots 原始数据
     */
    public List<SysResource> queryMenus(List<SysResource> roots,String arg){
        //查找根节点
        List<SysResource> menuList = roots.stream().filter(p->arg.equals(p.getResPrant())).collect(Collectors.toList());
        if(menuList.size()==0){
            return null;
        }
        //递归设置子菜单
        menuList.forEach(p->{
            p.setChildResource(this.queryMenus(roots,p.getResId()+""));
        });
        return menuList;
    }
}
