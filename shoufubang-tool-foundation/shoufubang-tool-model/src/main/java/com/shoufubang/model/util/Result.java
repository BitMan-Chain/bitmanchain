package com.shoufubang.model.util;



import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;




public class Result implements Serializable {
    /**
     * 缺少参数返回码
     */
    public static final String STATUS_CODE_400 = "400";
      
    
    public static final String STATUS_CODE_401 = "401";

    /**
     * 业务异常返回码
     */
    public static final String STATUS_CODE_500 = "500";

    /**
     * 注册成功，实名失败返回码
     */
    public static final String STATUS_CODE_201 = "201";
    /**
     * 短信验证码发送失败
     */
    public static final String STATUS_CODE_202 = "202";

    private static final long serialVersionUID = 1L;
    /**
     * 用户未登录(用于验证)
     */
    public static final String STATUS_CODE_499 = "499";

    /**
     * 暂无数据
     */
    public static final String STATUS_CODE_404 = "404";

    /**
     * 用于判断
     */
    public static final String STATUS_CODE_466 = "466";
    
    /**
     * 没有授信
     */
    public static final String STATUS_CODE_488 = "488";
    
    /**
     * 没有设置专用回款账户
     */
    public static final String STATUS_CODE_489 = "489";
    
    /**
     * 超过每日最大上传次数
     */
    public static final String STATUS_CODE_487 = "487";

    private Logger log = Logger.getLogger("ENCRYPT");

    private String code;
    private String message;
    private Object data = new HashMap<>();
    private String statusCode; 
    private String navTabId;
    private String rel;
    private String callbackType;
    private String forwardUrl;
    private String confirmmessage;
    
    private Object mapData = new HashMap<>();
    private List listData;

    
    public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	public Object getMapData() {
		return mapData;
	}

	public void setMapData(Object mapData) {
		this.mapData = mapData;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getNavTabId() {
		return navTabId;
	}

	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getCallbackType() {
		return callbackType;
	}

	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public String getConfirmmessage() {
		return confirmmessage;
	}

	public void setConfirmmessage(String confirmmessage) {
		this.confirmmessage = confirmmessage;
	}

	public Result() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String toString(String key) {
        String json = "";
        try {
            json = JsonUtil.objToJson(this);
            return DESUtil.encode(json, key);
        } catch (Exception e) {
            log.error("toString error,json:" + json, e);
            return "";
        }
    }

    /**
     * 通用异常
     *
     * @param message
     * @return
     */
    public String fail(String message) {
        this.code = "500";
        this.message = message;
//        this.data = "";
        //return this.toString();
        return JsonUtil.objToJson(this);
    }

    /**
     * key不存在
     *
     * @return
     */
    public String keyNotExistError() {
        this.code = "1";
        this.message = "";
//        this.data = "";
        return JsonUtil.objToJson(this);
    }

    /**
     * 解密失败
     *
     * @return
     */
    public String RSADecodeError() {
        this.code = "3";
        this.message = "decode error.";
//        this.data = "";
        return JsonUtil.objToJson(this);
    }

    /**
     * 未登陆异常
     *
     * @return
     */
    public String doNotLogin() {
        this.code = "2";
        this.message = "请登录.";
//        this.data = "";
        //return this.toString();
        return JsonUtil.objToJson(this);
    }

    public static void main(String[] args) {
        System.out.print(new Result().keyNotExistError());
    }

    public String imsiNotExistError() {
        return "";
        /*this.code = "4";
        this.message = "设备号不存在";
		this.data = "";
		return JsonUtil.objToJson(this);*/
    }

    public String success(Object obj) {
        this.code = "200";
        this.message = "success";
        this.data = obj;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }

    public String successNotEncrypt(Object obj) {
        this.code = "200";
        this.message = "success";
        this.data = obj;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }

    public String successEncrypt(Map<String, Object> map, String key) {
        return this.successEncrypt("success", map, key);
    }

    public String successEncrypt(String message, Map<String, Object> map, String key) {
        this.code = "200";
        this.message = message;
        this.data = map;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return this.toString(key);
    }

    public String fail(String code, String message, Map<String, String> map) {
        this.code = code;
        this.message = message;
        this.data = map;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }
   
    public String successUpdialog(String code,String message,String url,Map<String, String> map) {
        this.statusCode = code;
        this.message = message;
        this.navTabId = "navNewsLi";
        this.callbackType="forward";
        this.forwardUrl = url;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }
    public String successClose(String code,String message,String url,Map<String, String> map) {
    	this.statusCode = code;
    	this.message = message;
    	this.navTabId = "navNewsLi";
    	this.callbackType="closeCurrent";
    	this.forwardUrl = url;
    	if (this.data == null) {
    		this.data = new HashMap<>();
    	}
    	return JsonUtil.objToJson(this);
    }
    //================================================================
    public String success(String message, Map<String, Object> map) {
        this.code = "200";
        this.message = message;
        this.mapData = map;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }
    
    public String failData(String code, String message) {
        this.code = code;
        this.message = message;
        return JsonUtil.objToJson(this);
    }

	public String success(String message2, List list) {
		this.code = "200";
        this.message = message;
        this.listData = list;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
	}
	public String success(String message) {
        this.code = "200";
        this.message = message;
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return JsonUtil.objToJson(this);
    }
}
