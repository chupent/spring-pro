package com.cp.app.core.comm.security.authentiation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UsernamePasswordCodeAuthenticationToken
 * @Description TODO Token用户凭证
 * @createdate 2019/2/21 星期四 15:08
 */
public class UsernamePasswordCodeAuthenticationToken extends UsernamePasswordAuthenticationToken {

    /**
     * 验证码
     */
    private String code;
    private UserDetails userDetails;

    public UsernamePasswordCodeAuthenticationToken(Object principal, Object credentials, UserDetails userDetails) {
        super(principal, credentials);
        this.userDetails = userDetails;
    }
    public UsernamePasswordCodeAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public UsernamePasswordCodeAuthenticationToken(Object principal, Object credentials, String code) {
        super(principal, credentials);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
