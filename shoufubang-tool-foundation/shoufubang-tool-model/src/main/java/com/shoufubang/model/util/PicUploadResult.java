package com.shoufubang.model.util;

/**
 *
 * @ClassName: PicUploadResult 
 * @Description 
 * @author zhangyiying
 * @date 2016年8月8日 上午10:39:05 
 *  
 */
public class PicUploadResult {
	// 0.上传成功 1:上传失败
	private Integer error;

	// 图片宽
	private String width;

	// 图片高
	private String height;

	// 图片上传地址
	private String url;

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public PicUploadResult(Integer error, String width, String height, String url) {
		super();
		this.error = error;
		this.width = width;
		this.height = height;
		this.url = url;
	}

	public PicUploadResult() {
		super();
	}
}
