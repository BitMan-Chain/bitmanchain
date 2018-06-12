package com.shoufubang.model.user;

import java.io.Serializable;
import java.util.Date;

import com.shoufubang.model.util.BaseModel;

public class StressLog extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3450064362986273896L;
	
    private Integer id;

    private Integer userid;
    
    private Integer bUserId;

    private Integer bmStress;

    private Integer type;

    private Date stressDatetime;

    private String remarks;

    public Integer getId() {
        return id;
    }


	public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getBmStress() {
        return bmStress;
    }

    public void setBmStress(Integer bmStress) {
        this.bmStress = bmStress;
    }

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getStressDatetime() {
        return stressDatetime;
    }

    public void setStressDatetime(Date stressDatetime) {
        this.stressDatetime = stressDatetime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public Integer getbUserId() {
		return bUserId;
	}

	public void setbUserId(Integer bUserId) {
		this.bUserId = bUserId;
	}
}