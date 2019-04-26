package com.cp.app.core.model.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chupengtang
 * @version 1.0
 * @ClassName SysResource
 * @Description TODO
 * @createdate 2019/2/21 星期四 11:43
 */
@Entity
@Table(name = "sys_resource")
public class SysResource {
    @Id
    @Column(name = "res_id")
    private Long resId;
    @Column(name = "res_name")
    private String resName;
    @Column(name = "res_url")
    private String resUrl;

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
}
