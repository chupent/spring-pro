package com.wx.cp.model.bean.user;


import com.wx.cp.comm.root.RootBean;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName AccoutBean
 * @Description TODO
 * @createdate 2019/5/14 星期二 17:01
 */
@Entity
@Table(name = "WX_ACCOUNT")
@EntityListeners(AuditingEntityListener.class)
public class AccoutBean extends RootBean {

    @Id
    @Column(name ="userid" )
    @GeneratedValue(generator = "paymentableGenerator" )
    @GenericGenerator(name = "paymentableGenerator",strategy = "uuid")
    private String userId;

    @Column(name ="account" )
    private String account;
    @Column(name ="password" )
    private String password;
    @Column(name ="realname" )
    private String realName;
    @Column(name ="mobile" )
    private String mobile;
    @Column(name ="isvalid" )
    private int isValid;

    @Column(name ="crearedate" )
    @CreatedDate
    private long creareDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getIsValid() {
        return isValid;
    }

    public void setIsValid(int isValid) {
        this.isValid = isValid;
    }

    public long getCreareDate() {
        return creareDate;
    }

    public void setCreareDate(long creareDate) {
        this.creareDate = creareDate;
    }
}
