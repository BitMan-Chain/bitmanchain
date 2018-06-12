package com.shoufubang.model.user;

public class CarRenewal {
    private Integer id;

    private String carno;

    private Integer citycode;
    
    private String carCode;

    private String  carDriveNO;
    
    private Integer userId;

    private String backJson;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCarno() {
        return carno;
    }

    public void setCarno(String carno) {
        this.carno = carno;
    }

    public Integer getCitycode() {
        return citycode;
    }

    public void setCitycode(Integer citycode) {
        this.citycode = citycode;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getBackJson() {
        return backJson;
    }

    public void setBackJson(String backJson) {
        this.backJson = backJson;
    }

	public String getCarCode() {
		return carCode;
	}

	public void setCarCode(String carCode) {
		this.carCode = carCode;
	}

	public String getCarDriveNO() {
		return carDriveNO;
	}

	public void setCarDriveNO(String carDriveNO) {
		this.carDriveNO = carDriveNO;
	}
    
    
}