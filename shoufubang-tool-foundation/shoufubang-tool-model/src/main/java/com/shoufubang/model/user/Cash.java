package com.shoufubang.model.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.shoufubang.model.util.BaseModel;

public class Cash extends BaseModel implements Serializable  {
	
	private static final long serialVersionUID = -3450064362986273896L;
	
    private Integer id;

    private String seriaNum;

    private Double cashMoney;

    private Integer userId;

    private Date cashBegintime;

    private Date cashEndtime;

    private int cashStaus;

    private Double cashAmoney;

    private Double cashWmoney;
    
    private String mobile;
    
    private String address;

    
    private List<Cash> cashList;
    
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

    public Double getCashMoney() {
        return cashMoney;
    }

    public void setCashMoney(Double cashMoney) {
        this.cashMoney = cashMoney;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCashBegintime() {
        return cashBegintime;
    }

    public void setCashBegintime(Date cashBegintime) {
        this.cashBegintime = cashBegintime;
    }

    public Date getCashEndtime() {
        return cashEndtime;
    }

    public void setCashEndtime(Date cashEndtime) {
        this.cashEndtime = cashEndtime;
    }

    public int getCashStaus() {
        return cashStaus;
    }

    public void setCashStaus(int cashStaus) {
        this.cashStaus = cashStaus;
    }

    public Double getCashAmoney() {
        return cashAmoney;
    }

    public void setCashAmoney(Double cashAmoney) {
        this.cashAmoney = cashAmoney;
    }

    public Double getCashWmoney() {
        return cashWmoney;
    }

    public void setCashWmoney(Double cashWmoney) {
        this.cashWmoney = cashWmoney;
    }

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<Cash> getCashList() {
		return cashList;
	}

	public void setCashList(List<Cash> cashList) {
		this.cashList = cashList;
	}
    
}