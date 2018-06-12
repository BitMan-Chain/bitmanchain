package com.shoufubang.model.user;

import java.util.Date;

public class AppStart {
    private Integer id;

    private String deviceName;

    private String imei;

    private Integer osType;

    private String osVersion;

    private Date createTime;

    private Integer pushChannel;

    private Integer accessId;

    private String token;

    private Integer pushSwitch;

    private String appVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Integer getOsType() {
        return osType;
    }

    public void setOsType(Integer osType) {
        this.osType = osType;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPushChannel() {
        return pushChannel;
    }

    public void setPushChannel(Integer pushChannel) {
        this.pushChannel = pushChannel;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getPushSwitch() {
        return pushSwitch;
    }

    public void setPushSwitch(Integer pushSwitch) {
        this.pushSwitch = pushSwitch;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}