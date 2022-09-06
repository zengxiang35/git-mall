package com.web.mall.pojo;

public class RespBean {
    private int code;
    private String message;
    private Object data;

    public RespBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public RespBean(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static RespBean ok(String message){return new RespBean(200,message);}
    public static RespBean ok(String message,Object data){return new RespBean(200,message,data);}

    public static RespBean error(String message){return new RespBean(200,message);}
    public static RespBean error(String message,Object data){return new RespBean(200,message,data);}

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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

    @Override
    public String toString() {
        return "RespBean{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
