package com.cp.app.core.model.bean;

import com.cp.app.core.model.bean.attr.DateAttributeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName User
 * @Description TODO
 * @createdate 2019/2/21 星期四 09:35
 */
@Entity
@Table(name = "sys_user")
@EntityListeners(AuditingEntityListener.class)
public class SysUser {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String account;
    @JsonIgnore //忽略值
    private String password;

    @Column(name = "user_name")
    private String userName;

    @JsonIgnore //忽略值
    private String token;

    @Column(name = "header_url")
    private String headerUrl;


    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "update_date")
    @Convert(converter = DateAttributeConverter.class)
    private String updateDate;

    @Column(name = "create_date")
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createDate;

    public SysUser() {

    }
    public SysUser(Long userId, String account, String userName, String token, String headerUrl, String deviceId, String updateDate, Date createDate) {
        this.userId = userId;
        this.account = account;
        this.userName = userName;
        this.token = token;
        this.headerUrl = headerUrl;
        this.deviceId = deviceId;
        this.updateDate = updateDate;
        this.createDate = createDate;
    }
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHeaderUrl() {
        return headerUrl;
    }

    public void setHeaderUrl(String headerUrl) {
        this.headerUrl = headerUrl;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
