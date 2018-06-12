package com.shoufubang.model.user;

import java.io.Serializable;
import java.util.Date;

import com.shoufubang.model.util.BaseModel;

import jnr.ffi.annotations.In;

public class AccountLog extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = -3450064362946273896L;
	
    private Integer id;

    private String seriaNum;

    private String address;

    private Integer userId;

    private Integer bUserId;
    
    private Double number;

    private Date dateTime;

    private int type;
    
    private Integer fileId;

    
    public Integer getbUserId() {
		return bUserId;
	}

	public void setbUserId(Integer bUserId) {
		this.bUserId = bUserId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeriaNum() {
        return seriaNum;
    }

    public void setSeriaNum(String seriaNum) {
        this.seriaNum = seriaNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

	public Integer getFileId() {
		return fileId;
	}

	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
    
}