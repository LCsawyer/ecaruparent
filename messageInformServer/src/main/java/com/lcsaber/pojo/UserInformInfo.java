package com.lcsaber.pojo;

import java.sql.Timestamp;

/**
 * @Author: LiChao
 * @Date: 2019/6/22 10:53
 */
public class UserInformInfo {
    private Long id;
    private String informLevel;
    private String informDescription;
    private Long userId;
    private Timestamp informTime;
    private String informContent;

    public String getInformLevel() {
        return informLevel;
    }

    @Override
    public String toString() {
        return "UserInformInfo{" +
                "id=" + id +
                ", informLevel='" + informLevel + '\'' +
                ", informDescription='" + informDescription + '\'' +
                ", userId=" + userId +
                ", informTime=" + informTime +
                ", informContent='" + informContent + '\'' +
                '}';
    }

    public void setInformLevel(String informLevel) {
        this.informLevel = informLevel;
    }

    public String getInformDescription() {
        return informDescription;
    }

    public void setInformDescription(String informDescription) {
        this.informDescription = informDescription;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getInformTime() {
        return informTime;
    }

    public void setInformTime(Timestamp informTime) {
        this.informTime = informTime;
    }

    public String getInformContent() {
        return informContent;
    }

    public void setInformContent(String informContent) {
        this.informContent = informContent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
