package com.shoufubang.model.util;

public class ResponseBody {

    private String request_id;
    private Ret[] ret;
    private String success;

    public String getRequest_id() {
        return request_id;
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public Ret[] getRet() {
        return ret;
    }

    public void setRet(Ret[] ret) {
        this.ret = ret;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}


//Ocr识别文字位置
//Ocr identifies text locations
class Rect{
    private String left;
    private String top;
    private String width;
    private String height;

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
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
}
