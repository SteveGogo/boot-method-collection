package com.learn.springsecuritymyimpl.common;


/**
 * @Description: 统一返回工具
 */
public class Result {

    public static R Ok() {
        R res = new R();
        res.setMsg("");
        res.setRCode(200);
        return res;
    }

    public static R Ok(String msg) {
        R res = new R();
        res.setCode(200);
        res.setMsg(msg);
        return res;
    }


    public static R Ok(Object data) {
        R res = new R();
        res.setCode(200);
        res.setData(data);
        return res;
    }

    public static R Ok(String msg, Object data) {
        R res = new R();
        res.setCode(200);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static R Ok(ResultCode code, Object data) {
        R res = new R();
        res.setCode(code.getCode());
        res.setMsg(code.getMsg());
        res.setData(data);
        return res;
    }


    public static R Error() {
        R res = new R();
        res.setCode(-1);
        res.setMsg("发生异常");
        return res;
    }

    public static R Error(Integer code) {
        R res = new R();
        res.setCode(code);
        return res;
    }

    public static R Error(Object data) {
        R res = new R();
        res.setCode(-1);
        res.setData(data);
        return res;
    }

    public static R Error(String msg) {
        R res = new R();
        res.setCode(-1);
        res.setMsg(msg);
        return res;
    }


    public static R Error(String msg, Object data) {
        R res = new R();
        res.setCode(-1);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static R Error(String msg, int code) {
        R res = new R();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    public static R Error(int code, String msg) {
        R res = new R();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }

    public static R Error(String msg, int code, Object data) {
        R res = new R();
        res.setMsg(msg);
        res.setCode(code);
        res.setData(data);
        return res;
    }

    public static R Error210(String msg, Object data) {
        R res = new R();
        res.setCode(210);
        res.setMsg(msg);
        res.setData(data);
        return res;
    }

    public static R Error(ResultCode s) {
        R res = new R();
        res.setCode(s.getCode());
        res.setMsg(s.getMsg());
        return res;
    }


    public static R Error(ResultCode s, Object data) {
        R res = new R();
        res.setCode(s.getCode());
        res.setMsg(s.getMsg());
        res.setData(data);
        return res;
    }

}
