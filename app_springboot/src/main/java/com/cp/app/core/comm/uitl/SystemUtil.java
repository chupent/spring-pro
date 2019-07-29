package com.cp.app.core.comm.uitl;

import com.cp.app.core.comm.security.JwtsUtil;
import com.cp.app.core.comm.security.authentiation.SystemUserDetails;
import com.cp.app.core.model.bean.SysUser;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SystemUtil
 * @Description TODO
 * @createdate 2019/7/24 星期三 11:39
 */
public class SystemUtil {
    /**
     * 休眠
     * @param t
     */
    public static void sleep(long t){
        try {
            Thread.sleep(t);
        } catch (Exception e) {
        }
    }

    /**
     * 获取当前用户
     * @return
     */
    public static SysUser currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth !=null){
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                SystemUserDetails userDetails = (SystemUserDetails) principal;
                if(null==userDetails){
                    return null;
                }else {
                    return userDetails.getUser();
                }
            }
        }
        return null;
    }

    /**
     * 获取当前登录用户的token
     * @return
     */
    public static String currentUserToken() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth !=null){
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                return (String) auth.getCredentials();
            }
        }
        return null;
    }

    /**
     * 将token 转换为 username
     * @param token
     * @return
     */
    public static String tokenToUsername(String token) {
        return JwtsUtil.getUserName(token);
    }
}
