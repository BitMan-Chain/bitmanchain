package com.shoufubang.model.util.sms;

import java.util.Date;
/**
 * 
 * <pre>项目名称：shoufubang-tool-model    
 * 类名称：PhoneCodeLog    
 * 类描述：    短信log
 * 创建人：彭榉 pengju918@sina.com    
 * 创建时间：2017年1月9日 下午2:44:11    
 * 修改人：彭榉 pengju918@sina.com     
 * 修改时间：2017年1月9日 下午2:44:11    
 * 修改备注：       
 * @version </pre>
 */
public class PhoneCodeLog {
    private Integer id;

    private Integer userId;

    private String phone;

    private Integer codeuse;

    private String contents;

    private String validcode;

    private Integer codeType;

    private Integer status;

    private Integer type;

    private String createtime;

    private String endTime;
    
    
    

    public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getCodeuse() {
        return codeuse;
    }

    public void setCodeuse(Integer codeuse) {
        this.codeuse = codeuse;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents == null ? null : contents.trim();
    }

    public String getValidcode() {
        return validcode;
    }

    public void setValidcode(String validcode) {
        this.validcode = validcode == null ? null : validcode.trim();
    }

    public Integer getCodeType() {
        return codeType;
    }

    public void setCodeType(Integer codeType) {
        this.codeType = codeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

  
 
}