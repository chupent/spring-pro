package com.cp.app.core.comm.security.authentiation;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

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

    public UsernamePasswordCodeAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
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
}
