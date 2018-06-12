package com.shoufubang.model.user;


import java.util.HashMap;
import java.util.Map;

/**
 * 封装service的返回结果
 * Created by 陈蓓 on 2015/11/24.
 */
public class ServiceResult
{
    private Boolean isSuccess = false;
    private String error;
    private String message;
    private Map data = new HashMap();

    public ServiceResult(String error)
    {
        this.error = error;
    }

    public ServiceResult(String error, Boolean isSuccess)
    {
        this.error = error;
        this.isSuccess = isSuccess;
    }

    public ServiceResult(Boolean isSuccess, Map data)
    {
        this.isSuccess = isSuccess;
        if (null != data)
            this.data.putAll(data);
    }

    public Boolean getIsSuccess()
    {
        return isSuccess;
    }

    public Boolean isSuccess()
    {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess)
    {
        this.isSuccess = isSuccess;
    }

    public Map getData()
    {
        return data;
    }

    public Map data()
    {
        return data;
    }

    public String getStr(String key)
    {
        return (String) data.get(key);
    }

    public void setData(Map data)
    {
        this.data = data;
    }

    public String getError()
    {
        return error;
    }

    public String error()
    {
        return error;
    }

    public void setError(String error)
    {
        this.error = error;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
