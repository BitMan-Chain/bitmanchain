package com.shoufubang.model.user;

public class Account {
    private Integer id;

    private Integer userId;

    private Double account;

    private Double frozen;

    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getAccount() {
        return account;
    }

    public void setAccount(Double account) {
        this.account = account;
    }
 
    public Double getFrozen() {
		return frozen;
	}

	public void setFrozen(Double frozen) {
		this.frozen = frozen;
	}

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}