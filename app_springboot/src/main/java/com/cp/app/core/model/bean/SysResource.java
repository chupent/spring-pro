package com.cp.app.core.model.bean;

import javax.persistence.*;
import java.util.List;

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
    @Column(name = "res_is_hef")
    private int resIsHef;
    @Column(name = "res_url")
    private String resUrl;
    @Column(name = "res_icon")
    private String resIcon;
    @Column(name = "res_prant")
    private String resPrant;

    @Transient
    private List<SysResource> childResource;

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

    public int getResIsHef() {
        return resIsHef;
    }

    public void setResIsHef(int resIsHef) {
        this.resIsHef = resIsHef;
    }

    public String getResIcon() {
        return resIcon;
    }

    public void setResIcon(String resIcon) {
        this.resIcon = resIcon;
    }

    public String getResPrant() {
        return resPrant;
    }

    public void setResPrant(String resPrant) {
        this.resPrant = resPrant;
    }

    public List<SysResource> getChildResource() {
        return childResource;
    }

    public void setChildResource(List<SysResource> childResource) {
        this.childResource = childResource;
    }
}
