package com.cp.app.core.model.bean;

import javax.persistence.*;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysLogBean
 * @Description TODO
 * @createdate 2019/7/22 星期一 17:50
 */
@Entity
@Table(name = "sys_log")
public class SysLog {
    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logid;
    @Column(name = "call_api")
    private String callApi;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "param")
    private String param;
    @Column(name = "create_date")
    private long createDate;

    public long getLogid() {
        return logid;
    }

    public void setLogid(long logid) {
        this.logid = logid;
    }

    public String getCallApi() {
        return callApi;
    }

    public void setCallApi(String callApi) {
        this.callApi = callApi;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }
}
