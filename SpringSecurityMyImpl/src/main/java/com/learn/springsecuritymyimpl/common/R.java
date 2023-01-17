package com.learn.springsecuritymyimpl.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回类
 *
 * @author
 */
@Data
public class R<T> {

    private int code = 200;

    private String msg;

    private T data;

    public R() {

    }

    public R(T data) {
        if (data == null) {
            this.data = (T) new HashMap<>();
        } else {
            if (data instanceof Query) {
                Map<String, Object> map = new HashMap<>();
                map.put("list", ((Query) data).getRecords());
                map.put("total", ((Query) data).getTotal());
                this.data = (T) map;
            } else {
                this.data = data;
            }
        }
    }

    public R(Throwable e) {
        super();
        this.code = 500;
        if (e != null) {
            this.msg = e.getMessage();
        } else {
            this.msg = "发生异常";
        }
        this.data = (T) new HashMap<>();
    }

    public void setRCode(int code) {
        this.code = code;
        if (code == ResultCode.SUCCESS.getCode()) {
            this.msg = "请求成功";
        }
        if (code == ResultCode.DATA_EXIST.getCode()) {
            this.msg = "数据已存在";
        }
        if (code == ResultCode.API_DATA_ERROR.getCode()) {
            this.msg = "请求数据异常";
        }
        if (code == ResultCode.DATA_IS_NULL.getCode()) {
            this.msg = "数据为空";
        }
        if (code == ResultCode.AUTHENTICATION_FAIL.getCode()) {
            this.msg = "认证失败";
        }
        if (code == ResultCode.TOKEN_EXPIRED.getCode()) {
            this.msg = "token过期";
        }
        if (code == ResultCode.UNAUTHORIZED.getCode()) {
            this.msg = "权限不足";
        }
        if (code == ResultCode.SERVER_ERROR.getCode()) {
            this.msg = "服务器错误";
        }
        if (code == ResultCode.SSO_ERROR.getCode()) {
            this.msg = ResultCode.SSO_ERROR.getMsg();
        }
        if (code == ResultCode.LICENSE_ERROR.getCode()) {
            this.msg = ResultCode.LICENSE_ERROR.getMsg();
        }
    }
}
