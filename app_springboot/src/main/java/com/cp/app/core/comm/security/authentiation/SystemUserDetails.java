package com.cp.app.core.comm.security.authentiation;

import com.cp.app.core.model.bean.SysRole;
import com.cp.app.core.model.bean.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserDetails
 * @Description TODO
 * @createdate 2019/2/21 星期四 09:46
 */
public class SystemUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<SysRole> roles;//包含着用户对应的所有Role，在使用时调用者给对象注入roles

    public SystemUserDetails() {

    }

    public SystemUserDetails(SysUser user) {
        this.username = user.getAccount();
        this.password = user.getPassword();
    }

    public SystemUserDetails(SysUser user, List<SysRole> roles) {
        this.username = user.getAccount();
        this.password = user.getPassword();
        this.roles = roles;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    @Override //返回用户所有角色的封装，一个Role对应一个GrantedAuthority
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override //判断账号是否已经过期，默认没有过期
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override //判断账号是否被锁定，默认没有锁定
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //判断信用凭证是否过期，默认没有过期
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //判断账号是否可用，默认可
    public boolean isEnabled() {
        return true;
    }
}
