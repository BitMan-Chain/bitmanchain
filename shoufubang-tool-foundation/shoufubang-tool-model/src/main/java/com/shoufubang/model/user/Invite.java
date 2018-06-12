package com.shoufubang.model.user;

import java.util.Date;

public class Invite {
    private Integer id;

    private String invitefrom;

    private String binvitecode;

    private Date registtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvitefrom() {
        return invitefrom;
    }

    public void setInvitefrom(String invitefrom) {
        this.invitefrom = invitefrom == null ? null : invitefrom.trim();
    }

    public String getBinvitecode() {
        return binvitecode;
    }

    public void setBinvitecode(String binvitecode) {
        this.binvitecode = binvitecode == null ? null : binvitecode.trim();
    }

    public Date getRegisttime() {
        return registtime;
    }

    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }
}