package com.shoufubang.model.user;

import java.util.Date;

import com.shoufubang.model.util.BaseModel;

public class User extends BaseModel{
    private Integer id;

    private String mobile;

    private String password;

    private String name;
    
    private String idcard;

    private Date regTime;

    private String regIp;

    private String inviteCode;

    private String salt;
    
    private Integer bmStress;
    
    private Date stresstime;
    
    private Integer authMobile;
    
    private Integer authBankcard;
    
    private Integer autoCarRenwal;
    
    private Integer autoAirTravel;
    
    private Integer autoEducation;
    
    private int addStress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public Integer getBmStress() {
		return bmStress;
	}

	public void setBmStress(Integer bmStress) {
		this.bmStress = bmStress;
	}

	public Date getStresstime() {
		return stresstime;
	}

	public void setStresstime(Date stresstime) {
		this.stresstime = stresstime;
	}

	public Integer getAuthMobile() {
		return authMobile;
	}

	public void setAuthMobile(Integer authMobile) {
		this.authMobile = authMobile;
	}

	public Integer getAuthBankcard() {
		return authBankcard;
	}

	public void setAuthBankcard(Integer authBankcard) {
		this.authBankcard = authBankcard;
	}

	public Integer getAutoCarRenwal() {
		return autoCarRenwal;
	}

	public void setAutoCarRenwal(Integer autoCarRenwal) {
		this.autoCarRenwal = autoCarRenwal;
	}

	public Integer getAutoAirTravel() {
		return autoAirTravel;
	}

	public void setAutoAirTravel(Integer autoAirTravel) {
		this.autoAirTravel = autoAirTravel;
	}

	public int getAddStress() {
		return addStress;
	}

	public void setAddStress(int addStress) {
		this.addStress = addStress;
	}

	public Integer getAutoEducation() {
		return autoEducation;
	}

	public void setAutoEducation(Integer autoEducation) {
		this.autoEducation = autoEducation;
	}

	
}