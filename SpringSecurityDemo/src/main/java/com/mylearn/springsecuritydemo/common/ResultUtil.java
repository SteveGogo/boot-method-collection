package com.mylearn.springsecuritydemo.common;

public class ResultUtil {

    public static Result failure(String requiredLoginError) {
        Result result=new Result();
        result.setCode(400);
        result.setMsg(requiredLoginError);
        return result;
    }

    public static Result success(String token) {
        Result result=new Result();
        result.setCode(200);
        result.setMsg(token);
        return result;
    }
}
