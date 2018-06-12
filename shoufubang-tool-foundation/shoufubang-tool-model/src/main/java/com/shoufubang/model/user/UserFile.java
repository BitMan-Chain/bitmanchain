package com.shoufubang.model.user;

import java.io.Serializable;
import java.util.Date;

import com.shoufubang.model.util.BaseModel;


/**
 * 
 * <pre>
 * 项目名称：shoufubang-model    
 * 类名称：UserFile    
 * 类描述：用户文件  实体    
 * 创建人：彭榉 pengju918@sina.com    
 * 创建时间：2016年8月1日 下午2:49:42    
 * 修改人：yiyingzhang     
 * 修改时间：2016年9月6日 上午11:27:42    
 * 修改时间：2016年12月2日 上午15:34:42    
 * 修改备注：       添加文件类型的种类:物流单据,收货验收单
 *  		 添加字段账单id
 * 修改备注：       添加
 * &#64;version
 * </pre>
 */
public class UserFile extends BaseModel implements Serializable{
	private static final long serialVersionUID = -898350883595376475L;
	private Integer id;    //主键
	/**
	 * 文件类型(字典表) 1:
	 */
	private Integer type; // 文件类型 1 账单 2 火车票 3 其他
	private String url; // 文件路径
	private String description; // 文件描述
	/**
	 * 操作人
	 */
	private Integer userId;
	/**
	 * 操作时间
	 */
	private Date createTime;
	
	private String createTimeStr;
	
	/**
	 * 增加的算力
	 */
	private int number;
	
	/**
	 * 临时文件路径
	 */
	private String tmpFilePath;
	
	/**
	 * 0:审核中
	 * 1:审核通过
	 */
	private int status = 0;
	
	private String statusView;
	private String typeView;
	
	private Date date;
	private Double price;
	private String destination;
	private String origin;
	private String level;
	private String place;
	private String tdate;
	private String tnumber;
	private String tprice;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getTmpFilePath() {
		return tmpFilePath;
	}

	public void setTmpFilePath(String tmpFilePath) {
		this.tmpFilePath = tmpFilePath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTdate() {
		return tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getTnumber() {
		return tnumber;
	}

	public void setTnumber(String tnumber) {
		this.tnumber = tnumber;
	}

	public String getTprice() {
		return tprice;
	}

	public void setTprice(String tprice) {
		this.tprice = tprice;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusView() {
		return this.statusView = status == 0 ?  "审核中" : "审核通过";
	}


	public String getTypeView() {
		return this.typeView = type == 1 ?  "纸质账单" : type == 2 ? "火车票" : "发票";
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
	
}
