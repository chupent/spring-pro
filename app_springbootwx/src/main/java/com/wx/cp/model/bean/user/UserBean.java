package com.wx.cp.model.bean.user;


import com.wx.cp.comm.root.RootBean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName UserBean
 * @Description TODO
 * @createdate 2019/5/14 星期二 14:08
 */
@Entity
@Table(name = "WX_USER")
@EntityListeners(AuditingEntityListener.class)
public class UserBean extends RootBean {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "openid")
    private String openId;
    @Column(name = "userid")
    private String userId;
    @Column(name = "username")
    private String userName;
    @Column(name = "nickname")
    private String nickName;
    @Column(name = "mobile")
    private String mobile;

    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private int age;
    @Column(name = "header")
    private String header;
    @Column(name = "token")
    private String token;


    @Column(name = "createtime")
    @CreatedDate
    private long createTime;
    @Column(name = "lastlogintime")
    private long lastLoginTime;

    public UserBean() {}

    public UserBean(String openId, String userId, String userName, String nickName, String mobile, String sex, int age, String header, String token) {
        this.openId = openId;
        this.userId = userId;
        this.userName = userName;
        this.nickName = nickName;
        this.mobile = mobile;
        this.sex = sex;
        this.age = age;
        this.header = header;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", header='" + header + '\'' +
                ", token='" + token + '\'' +
                ", createTime=" + createTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
