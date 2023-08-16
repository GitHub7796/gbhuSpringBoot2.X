package com.gbhu.xdkt.utils;

public class JsonData {

    //状态码
    private String code;
    //业务数据
    private Object data;
    //信息
    private String msg;


    public JsonData() {
    }

    public JsonData(String code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    //失败，返回错误信息
    public static JsonData Fail(String msg) {
        return new JsonData("-1", null, msg);
    }

    //失败，自定义状态码，返回错误信息
    public static JsonData Fail(String code, String msg) {
        return new JsonData(code, null, msg);
    }

    //成功，返回数据
    public static JsonData Sucess(Object data) {
        return new JsonData("1", data, null);
    }

    //成功，不返回数据
    public static JsonData Sucess() {
        return new JsonData("1", null, null);
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
