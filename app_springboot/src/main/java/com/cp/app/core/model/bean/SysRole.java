package com.cp.app.core.model.bean;

import javax.persistence.*;


/**
 * @author chupengtang
 * @version 1.0
 * @ClassName Role
 * @Description TODO
 * @createdate 2019/2/21 星期四 09:41
 */
@Entity
@Table(name = "sys_role")
public class SysRole {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;
    @Column(name = "role_name")
    private String roleName;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
